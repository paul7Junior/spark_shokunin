FROM node:lts-alpine

WORKDIR /app
COPY spark_shokunin_vue_app/package*.json /app/package.json
RUN npm install

RUN npm install -g @vue/cli
EXPOSE 8080

CMD ["npm", "run", "serve"]
