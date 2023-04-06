FROM python:3.9-slim-buster

# Setting up directory
WORKDIR /app
COPY ./src /app

# Installing requirements
RUN pip install --no-cache-dir -r requirements.txt

# Exposing server on port 9191
EXPOSE 8004

# Starting app server
CMD ["python", "main.py"]