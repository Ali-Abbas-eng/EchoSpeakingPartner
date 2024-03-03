#!/bin/bash

# Function to generate a secret key
generate_secret_key() {
  python -c 'import secrets; print(secrets.token_urlsafe(32))'
}

# Function to generate a random username and password
generate_username_password() {
  python -c 'import secrets; print(secrets.token_urlsafe(16))'
}

# Check if .env.dev file exists
if [ ! -f .env.dev ]; then
  touch .env.dev
fi

# Generate a random database URL, username, and password
if ! grep -q "DATABASE_URL" .env.dev; then
  database_url=$(generate_username_password)
  echo "DATABASE_URL=$database_url" >> .env.dev
fi

if ! grep -q "DATABASE_USERNAME" .env.dev; then
  database_username=$(generate_username_password)
  echo "DATABASE_USERNAME=$database_username" >> .env.dev
fi

if ! grep -q "DATABASE_PASSWORD" .env.dev; then
  database_password=$(generate_username_password)
  echo "DATABASE_PASSWORD=$database_password" >> .env.dev
fi

# Generate a random secret key
if ! grep -q "SECRET_KEY" .env.dev; then
  secret_key=$(generate_secret_key)
  echo "SECRET_KEY=$secret_key" >> .env.dev
fi

# Set DEBUG to true
if ! grep -q "DEBUG" .env.dev; then
  echo "DEBUG=true" >> .env.dev
fi

# Prompt for AWS and OpenAI keys
if ! grep -q "AWS_ACCESS_KEY_ID" .env.dev; then
  read -p "Enter your AWS_ACCESS_KEY_ID: " aws_access_key_id
  echo "AWS_ACCESS_KEY_ID=$aws_access_key_id" >> .env.dev
fi

if ! grep -q "AWS_SECRET_ACCESS_KEY" .env.dev; then
  read -p "Enter your AWS_SECRET_ACCESS_KEY: " aws_secret_access_key
  echo "AWS_SECRET_ACCESS_KEY=$aws_secret_access_key" >> .env.dev
fi

if ! grep -q "AWS_REGION_NAME" .env.dev; then
  read -p "Enter your AWS_REGION_NAME: " aws_region_name
  echo "AWS_REGION_NAME=$aws_region_name" >> .env.dev
fi

if ! grep -q "AWS_VOICE_ID" .env.dev; then
  read -p "Enter your AWS_VOICE_ID: " aws_voice_id
  echo "AWS_VOICE_ID=$aws_voice_id" >> .env.dev
fi

if ! grep -q "AWS_POLY_OUTPUT_FORMAT" .env.dev; then
  read -p "Enter your AWS_POLY_OUTPUT_FORMAT: " aws_poly_output_format
  echo "AWS_POLY_OUTPUT_FORMAT=$aws_poly_output_format" >> .env.dev
fi

if ! grep -q "ASSEMBLYAI_API_KEY" .env.dev; then
  read -p "Enter your ASSEMBLYAI_API_KEY: " assemblyai_api_key
  echo "ASSEMBLYAI_API_KEY=$assemblyai_api_key" >> .env.dev
fi

if ! grep -q "OPENAI_API_KEY" .env.dev; then
  read -p "Enter your OPENAI_API_KEY: " openai_api_key
  echo "OPENAI_API_KEY=$openai_api_key" >> .env.dev
fi

echo ".env.dev file updated successfully."

# Create a PostgreSQL database using Docker
if ! grep -q "DATABASE_USERNAME" .env.dev && ! grep -q "DATABASE_PASSWORD" .env.dev; then
  echo "Creating PostgreSQL database..."
  docker run --name some-postgres -e POSTGRES_USER=$database_username -e POSTGRES_PASSWORD=$database_password -d postgres
  echo "PostgreSQL database created successfully."
fi

# Export the variables so they are available in the current shell
export $(grep -v '^#' .env.dev | xargs)

# Pause the script
read -p "Press enter to continue"
