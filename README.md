# Empik Backend Homework

Krótki opis aplikacji, co robi i do czego służy.

## Funkcje

- Pobieranie informacji o użytkowniku z GitHuba
- Zapisywanie i aktualizacja licznika zapytań do API
- Konfigurowalne timeouty dla połączeń z zewnętrznymi serwisami

## Technologie

- Java
- Spring Boot
- Reactive Web (WebFlux)
- Lombok
- JPA
- Docker
- PostgreSQL
- maven


## Instalacja i Uruchamianie

1. Sklonuj repozytorium:
   ```sh
   git clone https://gitlab.com/mariusz.lipski/empik.git

2. cd [nazwa_katalogu]
   mvn clean install

3. Uruchamianie aplikacji z wykorzystaniem Tomcata:

Jeśli masz zainstalowany Tomcat, skopiuj plik empik-0.0.1-SNAPSHOT.war do katalogu webapps Tomcata:
```sh
cp target/empik-0.0.1-SNAPSHOT.war /path/to/tomcat/webapps/
```
Następnie uruchom Tomcat.

4. Uruchamianie aplikacji przy użyciu Dockera:

Jeśli nie masz Tomcata, ale masz zainstalowany Docker, możesz stworzyć obraz Docker z Tomcatem i naszą aplikacją.

    Utwórz plik o nazwie Dockerfile w głównym katalogu projektu z następującą zawartością:

    dockerfile
```sh
FROM tomcat:9-jdk11
COPY target/empik-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/empik.war
````
Zbuduj obraz:

```sh
docker build -t empik-app .
````
Uruchom kontener:

```sh
    docker run -p 8080:8080 empik-app
```

Teraz twoja aplikacja powinna być dostępna pod adresem http://localhost:8080/empik.
   
5API

 Opis
 GET /{login}

Pobiera informacje o użytkowniku GitHub o podanym loginie.

Przykład:

````
curl http://localhost:8080/api/users/octocat
````
