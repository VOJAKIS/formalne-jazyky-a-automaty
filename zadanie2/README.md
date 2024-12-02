# Dokumentácia zadania č. 2

## Obsah
- [Dokumentácia zadania č. 2](#dokumentácia-zadania-č-2)
	- [Obsah](#obsah)
	- [Základné informácie](#základné-informácie)
	- [Spustenie programu](#spustenie-programu)
		- [Build a spustenie aplikácie](#build-a-spustenie-aplikácie)
	- [Spustenie testov](#spustenie-testov)
	- [Stručný rozbor riešenia](#stručný-rozbor-riešenia)
	- [Príklad:](#príklad)
		- [Akceptované výrazy](#akceptované-výrazy)
		- [Neakceptované výrazy](#neakceptované-výrazy)

<br>

## Základné informácie
| | |
|-|-|
Meno a priezvisko | Bc. Adam Valašťan
Zadanie |	[zadanie č. 2](https://kurzy.kpi.fei.tuke.sk/fj/labs/05.html)
Programovací jazyk | Java21
Maven | Maven 3.9.3

<br>

## Spustenie programu
### Build a spustenie aplikácie
```sh
mvn clean package
java -jar target/zadanie2-0.1.jar
```

<br>

## Spustenie testov
```sh
mvn test
```

<br>
<br>

## Stručný rozbor riešenia
1. Riešenie som vypracoval tak, že som si prepísal gramatiku, ktorá bola odprezentovaná na hodine. Všimnime si gramatiku nižšie:
	```js
	E -> T { "+" | "-" T }
	T -> F { "*" | "/" T }
	F -> D | "("E")"
	D -> [+|-] { "0" - "9" }
	```

2. Pomocou tejto gramatiky som zostrojil dané metódy, pričom som sa nechal inšpirovať predlohou z [webovej stránky zadania](https://kurzy.kpi.fei.tuke.sk/fj/labs/05.html). Metódy sa moc nelíšia od predošlého zadania, keďže aj pri tomto zadaní používame rekurziu na výpočet výrazov.

3. Pokiaľ je vstupný výraz validný, tak sa vypočíta jeho konečná hodnota.
	1. Ak sa vo výraze nachádzajú premenné, program si pre dané premenné vypýta hodnoty.

4. Pokiaľ nie je vstupný výraz validný, tak program vyhodí `CalculatorException` s dôvodom chyby.

<br>

## Príklad:
### Akceptované výrazy
| Vstupný výraz | Výsledok |
|-|-|
1+2 | 3
2-1 | 1
2*1 | 2
8/2 | 4
1+2*3 | 7
(1+2)*3 | 9
1+x | 2 (dodatočné dosadenie x=1)
1+x+y | 6 (dodatočné dosadenie x=2, y=3)

### Neakceptované výrazy
| Neakceptované výrazy |
|-|
1++2
1**2
1+2*-+