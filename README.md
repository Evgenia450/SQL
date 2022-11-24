# запуск контейнера
docker-compose up
# запуск jar файла
java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/db -P:jdbc.user=user -P:jdbc.password=password
# подключение к базе
docker-compose exec mysql mysql -u user -p db