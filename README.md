# Velkommen til Gruppe 5s Mario Projekt

Dette repository indeholder gruppe 5's opgavebesvarelse til opgaven "Svømmeklubben Delfinen" på EK's datamatikeruddannelse. I opgaven skulle vi udvikle et IT-system til et pizzaria.

Opgaven er afleveret 27. marts 2026

##### Udviklere:
Carl Emil Zeidler   
Nicklas Lærke Lyager  
Tor Jørgensen Schønbech  
Sulaman Shah  

#Følg guiden herunder for at bruge programmet.

## Clone projektet med SSH

1. Find den mappe på din computer, hvor du vil have projektet.
2. Højreklik og vælg **New Terminal** i folderen (eller tilsvarende i Windows).
3. Kør følgende kommando:

```bash
git clone git@github.com:mario-projekt-gruppe-7/mario-projekt-gruppe-7.git
```

## Krav for at køre

- **Java JDK version 21**
- **Maven**
- en terminal/console som understøtter **ANSI escape codes** for styling (det gør de fleste terminaler).

Check dine installationer:

```bash
java -version
javac -version
mvn -version
```

## Kør projekt med Maven

### compile projektet
```bash
mvn compile
```
### Kør programmet
```bash
mvn exec:java -Dexec.mainClass="Main"
```

# Forklaringer
### for at gemme medlemmer til fil, så skal du ændre i main filen fra at bruge InMemoryMemberRepository til FileMemberRepository. Se kommentaren i main.java. Dette kan dog kun gemme members. 