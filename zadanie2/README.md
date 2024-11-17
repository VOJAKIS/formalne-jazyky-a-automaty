# Dokumentácia - jednoduchý riadkovo-orientovaný kalkulátor

## Obsah
- [Dokumentácia - jednoduchý riadkovo-orientovaný kalkulátor](#dokumentácia---jednoduchý-riadkovo-orientovaný-kalkulátor)
	- [Obsah](#obsah)
	- [Základné informácie](#základné-informácie)
	- [Spustenie programu](#spustenie-programu)
	- [Spustenie testov](#spustenie-testov)
	- [Stručný rozbor riešenia](#stručný-rozbor-riešenia)
	- [Príklad:](#príklad)
		- [Akceptované reťazce](#akceptované-reťazce)
		- [Neakceptované reťazce](#neakceptované-reťazce)

<br>

## Základné informácie
| | |
|-|-|
Meno a priezvisko | Bc. Adam Valašťan
Zadanie |	[zadanie č. 2](https://kurzy.kpi.fei.tuke.sk/fj/labs/05.html)
Programovací jazyk | Java21

<br>

## Spustenie programu
0. Skompilujeme projekt pomocou príkazu
```sh
mvn clean package
```
1. Spustíme skompilovaný súbor
```sh
java -jar target/zadanie2-0.1.jar
```

<br>

## Spustenie testov
```sh
mvn test
```

<br>

## Stručný rozbor riešenia
Na riešenie problému som zatiaľ neprišiel.
<!-- 1. Riešenie som vypracoval tak, že som nakreslil prechodový diagram podľa regulárneho výrazu.
2. Pomocou prechodového diagramu som zostrojil DKA.
3. Pomocou DKA som vytvoril funkcie, podľa názvu stavov (q0, q1, ...), ktoré "konzumujú" vstupný reťazec.
4. Pokiaľ je vstupný reťazec validný, tak funkcia vráti prázdny znak ("") a vypíše sa "A".
5. Pokiaľ nie je vstupný reťazec validný, tak funkcia vráti null a vypíše sa "N". -->

<br>

## Príklad:

### Akceptované reťazce
|vstup|výstup|
|-|-|
1+2*3 | 7
(1+2)*3 | 9
x+y | zadaj x=, zadaj y= (predpokladáme x=2, y=5), výsledok 7

### Neakceptované reťazce
|vstup|výstup|
|-|-|
1+2*-+|chyba