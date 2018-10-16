# DSL Project [![Build Status](https://travis-ci.com/jpeng06/CPSC410_DSL.svg?branch=master)](https://travis-ci.com/jpeng06/CPSC410_DSL)
Domain Specific Language Project, CPSC 410

## Environment 
Java, Maven

## Grammar (To be updated)
 
PROGRAM		:= STATEMENT* 
STATEMENT		:= CREATEDEC | COMPUTEDEC 
CREATEDEC		:=  “CREATE” { COURSE | GROUP } 
COMPUTEDEC		:=  “COMPUTE” { AVG | GOAL } 
COURSE 		:= NAME “(” COMPONENTDEC* “)” 
GROUP	 		:= NAME “(” GROUPDEC* “)” 
AVG	 		:= “AVG” NAME “( )” 
GOAL	 		:= “GOAL " NAME “(” GOALDEC “)” 
COMPONENTDEC	:= NAMEDEC “,” WEIGHTDEC ["," MARKDEC]*  “;”  
GROUPDEC		:= NAMEDEC “;”  
GOALDEC		:= MARKDEC “;” MARKDEC “;” 
NAMEDEC		:= “Name” ”:” NAME 
WEIGHTDEC		:= “Weight” “:” WEIGHT 
MARKDEC		:= “Mark” “:” MARK 

## Example

CREATE COURSE cpsc410(
Name: Assignment1, weight: 8.6;
Name: Assignment2, weight: 12;
Name: Quiz, weight: 10;
Name: Final, weight: 70;
)

CREATE GROUP term1(
	Name: Cpsc410;
)

// get average for cpsc410 
COMPUTE AVG cpsc410()
	
// what need to be achieved for each individual component 
// to bring overall average to 90
COMPUTE GOAL cpsc410 (
mark: 90;
)



## Convention 

1. No upper case letters for syntax
2. No decimal points in input (for now)

## Doc Reference
https://docs.google.com/document/d/1-q6lc-2CBtWovp9s4yWDPjHYeltzoRHxdkEKu7AXpBk/edit?usp=sharing



2018 © All Rights Reserved  
