# SimbirSoftTest
Тестовое задание SimbirSoft

Чтобы запустить Selenium Grid на локальной машине, нужно скачать selenium-server-<version>.jar и в консоли выполнить команду:

java -jar selenium-server-<version>.jar standalone

Далее выполняем следующие команды:
Для запуска тестов: 

gradlew test -PsuiteFile=src\test\resources\testng\account_tests.xml

для просмотра отчета:

gradlew allureServe

для очистки данных (чтобы информация о предыдущих запусках не попадала в отчет):

gradlew clean
