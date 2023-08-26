import pyrebase
import json


configurations = json.load(open(r'./secret/firebase_credentials.json'))
firebase = pyrebase.initialize_app(config=configurations)
authentication = firebase.auth()
database = firebase.database()

