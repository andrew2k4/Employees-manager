# Build Angular app
FROM node:18.13.0 AS build

WORKDIR /app

COPY client/package*.json ./
RUN npm install
RUN npm install -g @angular/cli

COPY client/ ./
RUN npm run build

# Serve with NGINX
FROM nginx:latest

RUN mkdir -p /var/log/nginx

COPY gateway/nginx.conf /etc/nginx/nginx.conf

COPY --from=build /app/dist/service-booking-client/browser /usr/share/nginx/html



