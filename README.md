# RomanNumbers
This is a Roman Numbers function to test if a given string number is a vaild roman number

The supported letters are:

I = 1, V = 5, X = 10, L = 50, C = 100

(D and M are not for simplicity)

# Build
The code is written in Kotlin and build via IntlliJ embedded kotlinc

# Tests
The code includes Junit tests and Expect.kt as the asseration framework.
The code includes roman.txt with roman letters from 1 - 399 for using in the positive testing.

# Running the tests
Using IntlliJ IDE open the RomanNumbers project.  
File -> Open -> RomanNumber
Run the RomanTest


# Func rules
The function follows these specific rules:

 1.	Numerals can be concatenated to form a larger numeral (“XX” + “II” = 22) 
      
      a.  lesser number will follow the bigger numbers, e.g. “XXII” and not “IIXX” 
      
 2. If the numeral is I, X, or C - you can’t have more than three in a row (“II” + “II” = “IV”, “IIII” is forbidden) 
 
 3. If the numeral is V or L - you can’t have more than one (“V” + “V” = “X”, “VV” is forbidden) 
 
 4. If a lesser numeral, I or X, is put before a bigger numeral - it means subtraction of the lesser from the bigger (“IV” means 4, XC mean 90).  
 
      a. Only a single lesser numeral, X or I, can precede a bigger numeral at a time E.g. “IV”, “XIV” or “XCIX”
