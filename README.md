 # Restful-booker_API_Autotests
 
Репозиторий, в котором представлены автотесты API-сервиса [Restful-booker](http://restful-booker.herokuapp.com/apidoc/index.html).

---

## Таблица покрытия тест-кейсов автотестами

Представлена в данном документе [Google Sheets](https://docs.google.com/spreadsheets/d/170GZMh5hU1OWfxSq_aWryQfTzKz5EE3miWVevS6iH3E/edit?usp=sharing).

---

## Инструкция по запуску

1. Клонировать репозиторий;
2. В директории проекта вызвать команду ```./mvnw.cmd clean install```;
3. Для запуска автотестов в директории вызвать команду ```./mvnw.cmd test``` (если не желаете сохранять результаты прошлых попыток запуска автотестов, то вписать команду ```./mvnw clean test```).

Чтобы просмотреть отчёт Allure, в директории проекта необходимо вызвать команду ```./mvnw allure:serve```.

---

## Системные требования

1. Java 11
2. Maven