
# coding: utf-8

# In[79]:

from time import sleep
import numpy as np
import os
import cv2
import matplotlib.pyplot as plt
camera_1 = cv2.VideoCapture(0)
camera_2 = cv2.VideoCapture(1)
camera_3 = cv2.VideoCapture(2)
camera_4 = cv2.VideoCapture(3)

# In[80]:


def transform_image(img, size = 900, offset = 100, boundary = 80, square = 350):
    rows,cols,ch = img.shape

    pts1 = np.float32([[0,rows],[cols,rows],[0,0],[cols,0]])
    pts2 = np.float32([[size/2 - offset,square],[size/2 + offset,square],[boundary,0],[size - boundary,0]])

    M = cv2.getPerspectiveTransform(pts1,pts2)

    dst = cv2.warpPerspective(img, M, (size,size))

    return dst
def make_stitch(img1, img2, img3, img4):
    img1 = transform_image(img1)
    img2 = np.rot90(transform_image(img2))
    img3 = np.rot90(transform_image(img3), 2)
    img4 = np.rot90(transform_image(img4), 3)
    img5 = img1+img2+img3+img4
    return img5
org = cv2.resize(cv2.imread('1.png', cv2.IMREAD_UNCHANGED), (200,200))
while True:
    i1 = cv2.resize(camera_1.read()[1], (200,200))
    i2 = cv2.resize(camera_2.read()[1], (200,200))
    i3 = cv2.resize(camera_3.read()[1], (200,200))
    i4 = cv2.resize(camera_4.read()[1], (200,200))
    img = make_stitch(i1, i2, i3, i4)
    cv2.imshow('stitch', img)
    cv2.waitKey(1)

