import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import os
import gc
import glob
import random

import keras as k
from keras import backend as K
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten
from keras.layers import Conv2D, MaxPooling2D
from keras.layers.normalization import BatchNormalization
from keras.layers.advanced_activations import LeakyReLU
from keras.activations import tanh
from keras.preprocessing.image import ImageDataGenerator
from keras.callbacks import EarlyStopping, ModelCheckpoint
from keras.models import model_from_json
import cv2
from tqdm import tqdm

percent = 0.2

x_train = []
y_train = []
y_valid = []
x_valid = []
for filename in glob.glob('data/red/*'):
    print(filename)
    img = cv2.imread(filename, cv2.IMREAD_UNCHANGED)
    img = cv2.resize(img, (48, 48))
    if(random.random() > percent):
        x_train.append(img[:,:,:3])
        y_train.append(np.array([1, 0]))
    else:
        x_valid.append(img[:,:,:3])
        y_valid.append(np.array([1, 0]))
for filename in glob.glob('data/blue/*'):
    print(filename)
    img = cv2.imread(filename, cv2.IMREAD_UNCHANGED)
    img = cv2.resize(img, (48, 48))
    if(random.random() > percent):
        x_train.append(img[:,:,:3])
        y_train.append(np.array([1, 0]))
    else:
        x_valid.append(img[:,:,:3])
        y_valid.append(np.array([1, 0]))

for filename in glob.glob('data/negs/*'):
    print(filename)
    img = cv2.imread(filename, cv2.IMREAD_UNCHANGED)
    img = cv2.resize(img, (48, 48))
    if(random.random() > percent):
        x_train.append(img[:,:,:3])
        y_train.append(np.array([0, 1]))
    else:
        x_valid.append(img[:,:,:3])
        y_valid.append(np.array([ 0, 1]))

y_train = np.array(y_train, np.uint8)
x_train = np.array(x_train, np.float16) / 255.0
y_valid = np.array(y_valid, np.uint8)
x_valid = np.array(x_valid, np.float16) / 255.0

filepath= "best.hdf5"
checkpoint = ModelCheckpoint(filepath, monitor='val_loss', verbose=1, save_best_only=True)
earlystop = EarlyStopping(monitor='val_loss', min_delta=0.0002, patience=5, verbose=0, mode='auto')

callbacks_list = [checkpoint, earlystop]

datagen = ImageDataGenerator(
    featurewise_center=True,
    featurewise_std_normalization=True,
    rotation_range=30,
    width_shift_range=0.3,
    height_shift_range=0.3,
    horizontal_flip=True,
    vertical_flip = False,
    fill_mode =  "reflect")
datagen.fit(x_train)


model = Sequential()
model.add(BatchNormalization(input_shape=(48, 48, 3)))
model.add(Conv2D(4, kernel_size=(5, 5),padding='same'))
model.add(LeakyReLU())
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Conv2D(4, kernel_size=(3, 3),padding='same'))
model.add(LeakyReLU())
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Conv2D(4, kernel_size=(3, 3),padding='same'))
model.add(LeakyReLU())
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(8))
model.add(LeakyReLU())
model.add(Dense(2, activation='softmax'))

model.compile(loss='categorical_crossentropy', # We NEED binary here, since categorical_crossentropy l1 norms the output before calculating loss.
              optimizer='adam',
              metrics=['accuracy'])

model_json = model.to_json()
with open("model.json", "w") as json_file:
    json_file.write(model_json)

model.fit_generator(datagen.flow(x_train, y_train, batch_size = 128), 175, epochs = 30, validation_data = datagen.flow(x_valid, y_valid, batch_size = 32), validation_steps = 10, callbacks = callbacks_list, workers=1)
