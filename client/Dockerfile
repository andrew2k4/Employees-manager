FROM node:latest as angular-built
WORKDIR /usr/src/app
COPY package.json package.json
RUN npm install -g @angular/cli
RUN npm install 
COPY . .
RUN ng build --prod

FROM nginx:alpine
LABEL author="John Papa"
COPY --from=angular-built /usr/src/app/dist /usr/share/nginx/html
EXPOSE 80 443
CMD [ "nginx", "-g", "daemon off;" ]