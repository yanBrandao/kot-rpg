FROM gradle:6.0.1-jdk11

ENV USERNAME=""
ENV PASSWORD=""
ENV DB_PASSWORD=""

WORKDIR /app

COPY . .

ENTRYPOINT [ "gradle", "bootRun", "-PUSERNAME=${USERNAME}", "-PPASSWORD=${PASSWORD}", "-PDB_PASSWORD=${DB_PASSWORD}" ]