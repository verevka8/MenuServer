## MenuServer
Spring сервер для создания сессий и упралением ими. Использует redis и event bus паттерн для создания распределенной архитектуры. <br>
[Основной репозиторий](https://github.com/verevka8/menu_app_general)
## Инструкция по запуску

1. Необходимо запустить файл ```docker-compose.yml``` командой ```docker-compose up -d```, находясь в директории с этим файлом.
2. Приложение доступно по адресу: ```localhost:80```, dashboard Rabbitmq по адресу: ```localhost:61613```
<br><br>
*В файле ```MenuServer/src/main/resources/application.properties``` находится конфиг для spring-приложения*

