#!/bin/bash

# Function to generate a secret key
generate_secret_key() {
  python -c 'import secrets; print(secrets.token_urlsafe(32))'
}

# Check if .env.dev file exists
if [ ! -f .env.dev ]; then
  echo "Creating .env.dev file..."

  # Prompt for database URL
  read -p "Enter your database URL: " database_url
  echo "DATABASE_URL=$database_url" >> .env.dev

  # Generate a random secret key
  secret_key=$(generate_secret_key)
  echo "SECRET_KEY=$secret_key" >> .env.dev

  # Set DEBUG to true
  echo "DEBUG=true" >> .env.dev

  echo ".env.dev file created successfully."
else
  echo ".env.dev file already exists. No changes were made."
fi
