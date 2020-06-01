# kot-rpg
![img](https://travis-ci.org/yanBrandao/kot-rpg.svg?branch=develop)
[![Coverage Status](https://coveralls.io/repos/github/yanBrandao/kot-rpg/badge.svg?branch=master)](https://coveralls.io/github/yanBrandao/kot-rpg?branch=master)
[![Hits-of-Code](https://hitsofcode.com/github/yanBrandao/kot-rpg)](https://hitsofcode.com/view/github/yanBrandao/kot-rpg)

KOT-RPG is a REST API developed in Spring-boot Java using Clean Architecture as code style. The main goal of this API is to store a player information based in a RPG game. 
This is a living project, so if you want to use or change something, just send me an email.

## ER



![er-diagram-v2](./assets/er_diagram_v3.png)


## Running the project

### Requirements

There's a `docker-compose.yml` file, to start some external services, like `Postgres` database and `Wiremock` for offline request.
To run compose file, just do `docker-compose up` in root project folder.

To run KOT-RPG project, I recommend following this steps below:

 - Make an account in Blizzard API: https://develop.battle.net
 - After that, create a new client for your application in: https://develop.battle.net/access/clients
 - It will generate a ClientID and ClientSecret, now you need to setup some environments variables to run the application.
 - KOT-RPG use `PostgresSQL` to persiste game information.
 - I recommend use docker version with this command: `docker run --name kot_database -p your_port:5432 -e POSTGRES_PASSWORD=your_password -d postgres`
 - With all setup you can set the environments variables below:
```ruby
 DB_HOST=localhost
 DB_USER=postgres;
 DB_DATABASE=postgres;
 DB_PASS=your_password
 DB_PORT=your_port;
 
 BLIZZ_USER=your_blizzard_ClientID;
 BLIZZ_PASS=your_blizzard_ClientSecret;
```

 - Now you can run application with ./gradlew clean bootRun