# DSL Project [![Build Status](https://travis-ci.com/jpeng06/CPSC410_DSL.svg?branch=master)](https://travis-ci.com/jpeng06/CPSC410_DSL)
Domain Specific Language Project, CPSC 410

## Environment 
Java, Maven

## Grammer (To be updated)
 
PROGRAM 	 ::=  NAME “:” BLOCK
NAME		 ::= “Component” | “Grade”
BLOCK 	     ::= “{” DATA “}”
DATA 		 ::= “[” MARK | WEIGHT “]”
MARK		 ::=  KEY “:” number
WEIGHT 	     ::=  KEY “:” string “,” KEY “:” number
KEY		     ::= “name” | “weight” | “mark”

## Example

component: Assignment, weight: 20;
component: Quiz      , weight: 10;
component: Final     , weight: 70;
grade:     Assignment, mark: 72;


## Convention 

1. No upper case letters for syntax
2. No decimal points in input (for now)


2018 © All Rights Reserved  