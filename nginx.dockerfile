FROM nginx:latest
LABEL maintainer="Alisson"
COPY /nginx/app.conf /etc/nginx/nginx.conf
COPY /src/main/resources/static /var/www/basic
EXPOSE 80 443
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]