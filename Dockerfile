FROM postgres
ENV POSTGRES_PASSWORD dockerpassword
ENV POSTGRES_DB universidad
#COPY init.sql /docker-entrypoint-initdb.d/