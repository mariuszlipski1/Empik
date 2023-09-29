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
   
3. API

 Opis
 GET /{login}

Pobiera informacje o użytkowniku GitHub o podanym loginie.

Przykład:

````
curl http://localhost:8080/api/users/octocat
````
