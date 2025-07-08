up:
	@docker-compose up -d

down:
	@docker-compose down

init-db:
	@docker cp init.sql postgres_db:/init.sql
	@docker exec -i postgres_db psql -U admin -d mydatabase -f /init.sql

install:
	mvn clean install
	rm -rf ../apache-tomcat-11.0.9/webapps/my-library
	rm -f ../apache-tomcat-11.0.9/webapps/my-library.war
	cp -r ./target/my-library ../apache-tomcat-11.0.9/webapps/
	cp ./target/my-library.war ../apache-tomcat-11.0.9/webapps/
	-../apache-tomcat-11.0.9/bin/shutdown.sh
	../apache-tomcat-11.0.9/bin/catalina.sh run

