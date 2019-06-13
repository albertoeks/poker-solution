# poker-solution

## About
This project is a poker game solution.

## Requirements

- Java 8
- Junit 4 (/test)

## Usage
```
$ git clone <project>
$ cd <project>/out/artifacts/poker_solution_jar
```
##### Can be use a text file or enter the data manually

######1. To use text file:

```
$ cat poker-hands.txt | java -jar poker-solution.jar

Output:
Player 1: [263] hands
Player 2: [237] hands
```

######2. To use data manually, type one or a set of hands:

```
$ java -jar poker-solution.jar
$ 7S KH 7D 3C 3S 8C 8S 2D 4H 5C
$

Output:
Player 1: [1] hands
Player 2: [0] hands
```



