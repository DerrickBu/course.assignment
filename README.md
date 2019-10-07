# A library of address book in Java

### Introduction
This project implements an address book library.

An address book contains several *Contact*s, which is a library we also
implement in this assignment.

### Details
I use *gradle* to import dependencies which we need to use in the library.

For every public API exposed, we have a unit test. *TestAddressBook* is unit test for 
*AddressBook* library, and *TestContact* is a unit test for *Contact* library.

There's also a *Main* class as a simple test simulating normal calls for adressbook API.
It simply add, remove, sort and do other operations to contacts in address book.
There are results showing in System.out for you to check the correctness. 

### How to run
Firstly, make sure you are under directory 'course.assignment'.
Then, you could simply run:

```$xslt
./gradlew run
```

To run the unit tests, simply run:

```$xslt
./gradlew test
```

