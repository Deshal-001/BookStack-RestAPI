FROM postgres:latest
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB bookstoredb
COPY ./bookstoredb.sql /docker-entrypoint-initdb.d/
