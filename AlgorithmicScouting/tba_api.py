import pandas as pd
import time
from requests import get
import re
import numpy as np
class TBAParser:
    URL_PRE = 'https://www.thebluealliance.com/api/v3/'
    auth_key = ''
    def __init__(self, auth_key):
        self.auth_key = auth_key
    def fetch(self, url):
        return pd.io.json.json_normalize(get(self.URL_PRE + url, headers={'X-TBA-Auth-Key': self.auth_key}).json())
    def get_events_year(self, year):
        return self.fetch('events/{}'.format(year))
    def get_event_teams(self, key, year = None):
        return self.fetch('event/{}/teams'.format(str(year) + key))
    def get_event_matches(self, key, year = None):
        return self.fetch('event/{}/matches'.format(str(year) + key))
    def get_all_matches_year(self, year):
        event_matches = [self.get_event_matches(key, year) for key in self.get_events_year(year)['event_code']]
        return pd.concat(event_matches)