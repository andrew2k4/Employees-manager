# Build Stage
FROM node:18.13.0 as build

WORKDIR /app

# Copy package.json and package-lock.json files
COPY package.json ./
COPY package-lock.json ./

# Install dependencies
RUN npm install

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Copy the rest of the application files
COPY . .

# Build the Angular application
RUN npm run build 

# Production Stage
FROM nginx:latest

COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built Angular files from the build stage
COPY --from=build /app/dist/service-booking-client/browser /usr/share/nginx/html

# Expose port 80 to serve the application
EXPOSE 80
