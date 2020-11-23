# Scrumbags
Ohjelmistotuotanto miniprojekti

Lukuvinkkikirjasto<br>

[Backlogit ja muut dokumentit](https://docs.google.com/spreadsheets/d/1cjemOe1V6ia_IiacJ4TIb77QLeIoD4wPJo6hCqYEEDg/edit?usp=sharing).
  
[![codecov](https://codecov.io/gh/Latelaukki/Scrumbags/branch/main/graph/badge.svg)](https://codecov.io/gh/Latelaukki/Scrumbags)

![Java CI with Gradle](https://github.com/Latelaukki/Scrumbags/workflows/Java%20CI%20with%20Gradle/badge.svg)

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

## Definition of Done

* User story on määritelty ja sen merkitys analysoitu tarkasti
* Toiminnallisuus on toteutettu kooditasolla
* Toiminnallisuus on testattu kaikilla tasoilla automaattisesti ja kattavasti, minkä lisäksi on suoritettu tarvittava määrä tutkivaa testausta
* Toiminnallisuus on integroitu onnistuneesti osaksi muuta sovellusta
* Sovelluksen toiminta on dokumentoitu
