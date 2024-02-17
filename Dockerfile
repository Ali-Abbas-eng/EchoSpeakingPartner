# Start with a base image containing Python runtime
FROM python:3.9.13-slim-buster

# Set the working directory in the container to /app
WORKDIR /app

# Install Node.js
RUN apt-get update && apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs

# Install gcc and other dependencies
RUN apt-get update && apt-get install -y gcc

# Install PortAudio
RUN apt-get install -y portaudio19-dev

# Update pip
RUN /usr/local/bin/python -m pip install --upgrade pip

# Copy the server directory contents into the container at /app/server
COPY ./server /app/server

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r /app/server/requirements.txt

# Copy the phoneclient directory contents into the container at /app/phoneclient
COPY ./phoneclient /app/phoneclient

# Install any needed packages specified in package.json
RUN cd /app/phoneclient && npm install

# Make port 8000 available to the world outside this container
EXPOSE 8000

# Run app.py when the container launches
CMD ["python", "/app/server/manage.py", "runserver"]
