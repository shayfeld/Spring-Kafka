# File Transfer

A project that transfers information between a source folder and a destination folder.




## Tech Stack

**Kafka:** distributed event store and stream-processing platform

**Spring Boot:** producer and consumer for Kafka

**Docker:** run kafka server and spring boot application


## Authors

- [@Shayfeld](https://github.com/shayfeld)

## Run Locally

Clone the project

```bash
  git clone https://github.com/shayfeld/Spring-Kafka.git
```

Go to the project directory

```bash
  cd Spring-Kafka
```

Install Kafka and run it

```bash
  Docker-compose rm -svf
  Docker compose up
```

Install dependency

```bash
  mvn compile
```

Build the application

```bash
  docker build Dockerfile -t docker-spring .
```

## Screenshots

Input folder

![App Screenshot](https://i.ibb.co/t433qxV/input.png)

Output folder

![App Screenshot](https://i.ibb.co/4Z2zpFD/output.png)
