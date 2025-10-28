CLIENT_DIR=frontend
SERVER_DIR=backend

.PHONY: all transpile transpile-client  transpile-server  docker-build-dev up-dev stop-dev 

all: stop-dev up-dev
# ...
transpile-client:
	cd $(CLIENT_DIR) && \
	 [ -s "$$NVM_DIR/nvm.sh" ] && . "$$NVM_DIR/nvm.sh" && nvm install && nvm use || true && \
	npm install && \
	npm run build

transpile-server:
	cd $(SERVER_DIR) && \
	 [ -s "$$NVM_DIR/nvm.sh" ] && . "$$NVM_DIR/nvm.sh" && nvm install && nvm use || true && \
	npm install && \
	npm run build

transpile: transpile-client transpile-server 
# ...
docker-build-dev: transpile
	docker-compose -f docker-compose.dev.yml build

up-dev: docker-build-dev
	docker-compose -f docker-compose.dev.yml up -d

stop-dev:
	docker-compose -f docker-compose.dev.yml down

# docker-compose -f docker-compose.dev.yml logs server
