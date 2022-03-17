# Electronic-Contact-Book-System

The Electronic Contact Book System can read existing contact information. Each record has information about a person. There may be 5 fields:

1. name in the form of a string of forename(s) and surname, all on one line; and the name cannot include numeric or punctuation characters

2. birthday in form of dd-mm-yyyy (leading zero in day or month may be omitted,i.e., both 05-04-2012 and 5-4-2013 are valid)

3. phone in the form of a sequence of digits. You must ignore any leading zeroes.

4. address in the form of a string that may span more than one line

5. e-mail which must be a valid e-mail address, eg, a string with alphabetic, numeric and punctuation characters and an "at" (@) symbol inside and with no gaps, such as: abcdefg.123@gmail. com

The following is the UML design diagram:
![image](https://user-images.githubusercontent.com/93305654/158732299-57498f4c-3e89-4240-9fb1-1737f23a21db.png)

The system mainly includes four functions: Add, Delete, query and Save. Different operations can be performed on the system through instructions.
