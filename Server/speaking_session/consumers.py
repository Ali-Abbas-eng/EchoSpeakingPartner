import json
from channels.generic.websocket import AsyncWebsocketConsumer
from gptengine import ai_request_handlers
from django.conf import settings


class SpeakingSessionConsumer(AsyncWebsocketConsumer):
    def __init__(self):
        super().__init__()
        self.ai_processor = ai_request_handlers.BaseConversationalist(settings.MEDIA_ROOT)

    async def connect(self):
        await self.accept()
        await self.send(text_data=json.dumps({"type": "connection_established",
                                              "message": "You are now connected"}))

    async def receive(self, text_data=None, bytes_data=None):
        if bytes_data:
            response = self.ai_processor(bytes_data, message_id=1)
            await self.send(text_data=json.dumps(response))
