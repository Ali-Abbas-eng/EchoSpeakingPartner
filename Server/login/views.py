import requests
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .models import authentication


class LoginAPIView(APIView):
    def post(self, request):
        email = request.data.get('email')
        password = request.data.get('password')
        try:
            user = authentication.sign_in_with_email_and_password(email=email, password=password)
        except requests.exceptions.HTTPError:
            return Response({'message': 'Invalid Credentials', 'status': status.HTTP_401_UNAUTHORIZED})

        session_id = user['idToken']
        request.session['uid'] = str(session_id)
        return Response({"message": "Login successful"}, status=status.HTTP_200_OK)


class LogoutAPIView(APIView):
    def post(self, request):
        try:
            del request.session['uid']
        except KeyError:
            pass
        except requests.exceptions.HTTPError:
            pass
        return Response({"message": "Logged out"}, status=status.HTTP_200_OK)
