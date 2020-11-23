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

### Sovelluksen toiminnot

Sovelluksen käynnistymisen jälkeen sovellus listaa käytettävissä olevan toiminnot:

```
komennot:
q
add book
add link
search
```

Tämän jälkeen käyttäjää pyydetään valitsemaan jokin yllä listatuista toiminnoista:

```
Anna komento:

```

#### Kirjan lisääminen

Syötä komento `add book`:

```
Anna komento:
add book
```
ja paina `Enter`.

Tämän jälkeen anna yksi kerrallaan:
- kirjan nimi
- kirjailijan nimi
- kirjan ISBN
- kirjan sivumäärä
- kirjan julkaisuvuosi

Esimerkiksi:

```
Anna kirjan nimi.
Testikirja
Anna kirjailijan nimi.
Testi Kirjailija
Anna ISBN.
123-456
Anna kirjain sivumäärä
500
Anna kirjain julkaisuvuosi
2020
```

Tämän jälkeen käyttäjältä varmistetaan vielä, onko annetut tiedot oikein:

```
LISÄTÄÄN KIRJA: 
NIMI: Testikirja
KIRJAILIJA: Testi Kirjailija
ISBN: 123-456
SIVUMÄÄRÄ: 500
JULKAISUVUOSI: 2020
ONKO OK? [y/n]
```

Jos syötteet ovat oikein, syötä `y` ja paina `Enter`. Muussa tapauksessa syötä `n` ja paina `Enter`.

Jos kirjan lisääminen tapahtui onnistuneesti, käyttöliittymä ilmoittaa seuraavasti:

```
Kirja lisätty onnistuneesti.
```
#### Linkin lisääminen
Syötä komento `add book`:

```
Anna komento:
add link
```
ja paina `Enter`.

Tämän jälkeen anna yksi kerrallaan:
- Linkin nimi
- Linkin osoite (url)

Esimerkiksi:

```
Anna Linkin nimi.
Ohjelmistotuotanto 2020
Anna URL.
https://ohjelmistotuotanto-hy.github.io/
```
Tämän jälkeen käyttäjältä varmistetaan vielä, onko annetut tiedot oikein:

```
LISÄTÄÄN URL: 
NIMI: Ohjelmistotuotanto 2020
URL: https://ohjelmistotuotanto-hy.github.io/
ONKO OK? [y/n]
```

Jos syötteet ovat oikein, syötä `y` ja paina `Enter`. Muussa tapauksessa syötä `n` ja paina `Enter`. 

Jos linkin lisääminen tapahtui onnistuneesti, käyttöliittymä ilmoittaa seuraavasti:

```
Linkki lisätty onnistuneesti.
```

#### Sovelluksen sulkeminen
Syötä komento `q`:

```
Anna komento:
q
```
ja paina `Enter`.

## Definition of Done

* User story on määritelty ja sen merkitys analysoitu tarkasti
* Toiminnallisuus on toteutettu kooditasolla
* Toiminnallisuus on testattu kaikilla tasoilla automaattisesti ja kattavasti, minkä lisäksi on suoritettu tarvittava määrä tutkivaa testausta
* Toiminnallisuus on integroitu onnistuneesti osaksi muuta sovellusta
* Sovelluksen toiminta on dokumentoitu
