from tba_api import *

tba = TBAParser('0z9XekAuzFQKEZNJyNqF0O3J8WN9mk4ptuzP9L12tIc70CongAJdmnBGRVDzVnOK')
full_data = tba.get_all_matches_year(2018)
full_data.to_pickle('data/match_data.pkl')