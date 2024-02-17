from django.shortcuts import render, redirect


def chat_page(request, *args, **kwargs):
    context = {}
    return render(request, 'speaking_session/chat_page.html', context)

