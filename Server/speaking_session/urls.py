from django.urls import path, include
from speaking_session import views
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('', views.chat_page, name='chat_page'),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
