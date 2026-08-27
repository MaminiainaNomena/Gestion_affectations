#!/bin/bash
# Automate project compilation and deployment to Tomcat on ubuntu
# TODO : should find a better way
mvn package && sudo rm -rf /opt/tomcat/webapps/gestion-affectations* && sudo cp target/gestion-affectations.war /opt/tomcat/webapps/