# Scrumbags
Ohjelmistotuotanto miniprojekti

Lukuvinkkikirjasto<br>

[Backlogit ja muut dokumentit](https://docs.google.com/spreadsheets/d/1cjemOe1V6ia_IiacJ4TIb77QLeIoD4wPJo6hCqYEEDg/edit?usp=sharing).
  
[![codecov](https://codecov.io/gh/Latelaukki/Scrumbags/branch/main/graph/badge.svg)](https://codecov.io/gh/Latelaukki/Scrumbags)

## Käyttöohjeet

* Lataa projekti koneellesi ja mene kansioon Scrumbags
* Luo shadow jar-tiedosto komennolla

```
./gradlew shadowJar
```

* Jar-tiedosto *Scrumbags-all.jar* luodaan kansioon Scrumbags/build/libs/. Jos et löydä sitä niin suorita komento *tree*.
* Mene kansioon jossa tiedosto sijaitsee ja aja se komennolla

```
java -jar Scrumbags-all.jar
```

* Jacoco-testikattavuusraportti luodaan projektin juurikansiossa suorittamalla komento. Huom. suositeltavaa on suorittaa prokjektille ensin komennot ./gradlew clean sekä ./gradlew build

```
./gradlew test jacocotestReport
```

* Tämän jälkeen jacoco-testikattavuusraporttia voi tarkastella juurikansiossa esimerkiksi komennolla

```
firefox build/reports/jacoco/test/html/index.html
```
