# Boilerplate Monorepo with Mongo Nest & Material UI

## Instructions for start:

### Option 1: Using as template in the same repository

- Click on Use "this template" button.
  ![alt text](./readme-images/image.png)

- Create a new reposity based on this. Choose the owner.
  ![alt text](./readme-images/image-1.png)

- Clone this repository for your local using:

```bash

git clone [your project https or ssh link]

```

![alt text](./readme-images/image-2.png)

- Open your project and adapt the README.md

### Option 1: Using as template in your repository

- Go into your profile and create a new repository.
- On Configuration, choose the template:
  ![alt text](./readme-images/image-3.png)

- Clone this new repository for your local using:

```bash

git clone [your project https or ssh link]

```

![alt text](./readme-images/image-2.png)

- Open your project and adapt the README.md

## Install nvm:

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.3/install.sh | bash
```

## Create your env vars

- Copy from env example

```bash
cp .env.example .env
```

- Create your own values for `MONGO_INITDB_ROOT_USERNAME`, `MONGO_INITDB_ROOT_PASSWORD` and `MONGO_INITDB_DATABASE`;
- For `MONGO_URL` apply your own values on this way:

```bash
MONGO_URL=mongodb://MONGO_INITDB_ROOT_USERNAME:MONGO_INITDB_ROOT_PASSWORD@mongo:27017/MONGO_INITDB_DATABASE?authSource=admin
```

-

## Run the application

```bash
make
```

## If you need stop the containers

```bash
make stop-dev
```

## Links

### Frontend

http://localhost:8006/

### Backend

http://localhost:3006/

### Swagger

http://localhost:3006/api
