# Используем базовый образ с ArtemisMQ
FROM vromero/activemq-artemis:latest

# Копируем конфигурационные файлы ArtemisMQ в контейнер
COPY broker.xml /var/lib/artemis/etc/
COPY broker-users.xml /var/lib/artemis/etc/

# Открываем порты, необходимые для работы ArtemisMQ
EXPOSE 8161 61616