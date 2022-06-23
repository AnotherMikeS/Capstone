FROM node:16-alpine as builder
WORKDIR /home/app
COPY . .
RUN npm ci && npm cache clean --force
RUN npm run build

# NEW CODE
# 1. Nginx & Alpine
FROM nginx:1.21-alpine
# 2. replace nginx's default config with our nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf
# 3. copy build output from builder to the default static file location
COPY --from=builder /home/app/build /usr/share/nginx/html
# NEW CODE
# 1. Copy the initialization script to the Nginx init scripts directory.
COPY 85-set-environment-vars.sh /docker-entrypoint.d
# 2. Make it executable.
RUN chmod +x /docker-entrypoint.d/85-set-environment-vars.sh

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
