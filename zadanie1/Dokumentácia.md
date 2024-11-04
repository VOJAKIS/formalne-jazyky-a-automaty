# Dokumentácia zadania č. 1

## Obsah
- [Dokumentácia zadania č. 1](#dokumentácia-zadania-č-1)
	- [Obsah](#obsah)
	- [Základné informácie](#základné-informácie)
	- [Spustenie programu](#spustenie-programu)
		- [Zadanie vstupu manuálne](#zadanie-vstupu-manuálne)
		- [Zadanie vstupu zo súboru](#zadanie-vstupu-zo-súboru)
		- [Build a spustenie aplikácie](#build-a-spustenie-aplikácie)
	- [Spustenie testov](#spustenie-testov)
	- [Stručný rozbor riešenia](#stručný-rozbor-riešenia)
	- [Príklad:](#príklad)
		- [Neakceptované reťazce](#neakceptované-reťazce)
		- [Akceptované reťazce](#akceptované-reťazce)

<br>

## Základné informácie
| | |
|-|-|
Meno a priezvisko | Bc. Adam Valašťan
Zadanie |	zadanie č. 1
Regulárny výraz | [a]b{a\|b}
Programovací jazyk | Java21

<br>

## Spustenie programu
### Zadanie vstupu manuálne
```sh
java -jar zadanie1-0.1.jar
```
### Zadanie vstupu zo súboru
```sh
java -jar zadanie1-0.1.jar file.txt
```
### Build a spustenie aplikácie
```sh
mvn clean package
java -jar target/zadanie1-0.1.jar
```

<br>

## Spustenie testov
```sh
mvn test
```

<br>

## Stručný rozbor riešenia
1. Riešenie som vypracoval tak, že som nakreslil prechodový diagram podľa regulárneho výrazu.
2. Pomocou prechodového diagramu som zostrojil DKA.
3. Pomocou DKA som vytvoril funkcie, podľa názvu stavov (q0, q1, ...), ktoré "konzumujú" vstupný reťazec.
4. Pokiaľ je vstupný reťazec validný, tak funkcia vráti prázdny znak ("") a vypíše sa "A".
5. Pokiaľ nie je vstupný reťazec validný, tak funkcia vráti null a vypíše sa "N".

<br>

## Príklad:
**Regulárny výraz**: 		[a]b{a|b}

### Neakceptované reťazce
|vstup|výstup|
|-|-|
a | N
aa | N

### Akceptované reťazce
|vstup|výstup|
|-|-|
b | A
bb | A
ab | A
aba | A
abba | A