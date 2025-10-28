FROM node:20.19.0 as build
WORKDIR /app

COPY . .

ARG VITE_API_URL
ENV VITE_API_URL=$VITE_API_URL


RUN npm install
RUN npm run build

CMD ["npm","run", "dev"]