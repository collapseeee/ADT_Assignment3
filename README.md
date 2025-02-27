# Infix to Postfix Conversion

## Overview
This program converts infix expressions to postfix notation using a stack implemented single linked list based approach. It reads expressions from a file, validates their correctness, and prints the postfix equivalent if valid.

**This program is developed for Programming Assignment 3 of SE102 – ADT & Problem Solving.**

## Requirements
- Java 8 or higher

## Installation Instructions
1. Download the source files:
    - `InfixToPostfixMain.java`
    - `Convertor.java`
    - `Stack.java`
    - `Node.java`
2. Compile all Java files.
3. Run the program with a file containing infix expressions as input:

```bash
javac *.java  
java InfixToPostfixMain input.txt  
```  

## Usage
1. Prepare a csv file (`input.csv`) with infix expressions, each on a new line.
2. Run the program, providing the filename as a command-line argument.
3. The program will validate each expression and print the corresponding postfix expression.

## Features
- **Stack-based conversion**: Uses a `Stack` class to manage operators and parentheses.
- **Expression validation**: Ensures input is a well-formed infix expression.
- **Error handling**: Detects and notifies users of invalid input.

## Example
**Input (`input.csv`)**
```
A + B * C  
( A + B ) * C  
A + ( B * C )  
```
**Output**
```
Expression 1:  
Infix Expression: A+B*C  
Is a valid infix expression.  
Postfix Expression: ABC*+  

Expression 2:  
Infix Expression: (A+B)*C  
Is a valid infix expression.  
Postfix Expression: AB+C*  

Expression 3:  
Infix Expression: A+(B*C)  
Is a valid infix expression.  
Postfix Expression: ABC*+  
```  

---

## Credits
**Author:** Nattikorn Sae-sue, 672115014  
Email: nattikorn_s@cmu.ac.th

**Course:** SE102 – ADT & Problem Solving
**Assignment:** Programming Assignment 3

