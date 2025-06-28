up:
	@docker-compose up -d

down:
	@docker-compose down

init-db:
	@docker cp init.sql postgres_db:/init.sql
	@docker exec -i postgres_db psql -U admin -d mydatabase -f /init.sql
