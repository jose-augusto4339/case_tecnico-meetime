#!/bin/bash

# Build do projeto

mvn -f /case-tecnico/pom.xml clean install

# Inicialização da aplicação

java -jar /case-tecnico/target/case-tecnico-0.0.1-SNAPSHOT.jar --spring.config.location=file:/application.properties
