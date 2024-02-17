from django.urls import path
from . import views

urlpatterns = [
    path('echo-chat-test/', views.lobby)
]