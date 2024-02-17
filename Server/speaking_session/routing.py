from django.urls import path, include, re_path
from speaking_session.consumers import SpeakingSessionConsumer


websocket_urlpatterns = [
    re_path(r'ws/socket-server/', SpeakingSessionConsumer.as_asgi()),
]
