import json
from channels.generic.websocket import WebsocketConsumer
from asgiref.sync import async_to_sync


class SpeakingSessionConsumer(WebsocketConsumer):
    def connect(self):
        self.room_group_name = 'speaking_session'
        async_to_sync(self.channel_layer.group_add)(
            self.room_group_name,
            self.channel_name
        )

        self.accept()

    def receive(self, text_data=None, bytes_data=None):
        audio = bytes_data
        text = None