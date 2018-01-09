# Hotel managemnet

[Opisać cel projektu]

## Features

* [Opsiać funkcje]
*

## Setup
The steps below will get you up and running with a local development environment. We assume you have the following installed:

* JDK 1.8
* PostgreSQL
* Maven

Create database
````sql
CREATE DATABASE "hotel"
````

Set environment variable
```bash
export 'JDBC_DATABASE_URL=jdbc:postgresql://localhost/hotel?user=[username]&password=[password]'
```

### Development
Run appliaction
```bash
mvn spring-boot:run
```
### Production

Configuration
-----
| Key               | Description                     |
|-------------------|---------------------------------|
| JDBC_DATABASE_URL | database connection information |

Contributing
-------------

Licence
-------