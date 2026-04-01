# Java-Tasks-
Java Tasks This repository contains Java tasks demonstrating various concepts.


## Tasks

### Task 1: Bank Account Management System

* **Description:** Demonstrates Object-Oriented Programming (OOP) principles including Encapsulation, Inheritance, Polymorphism, and Interface using a Bank Account Management System. The system supports basic banking operations.
* **Files:** `t1/Main.java`
* **Output:**

```text
Deposited: 1000.0.
Withdrawn: 2000.0.
Balance: 4000.0
----------------
Deposited: 2000.0.
Minimum balance of 500 must be maintained.
Balance: 3000.0
```

### Task 2: Student Grade Calculator

* **Description:** Calculates student grades based on entered marks using operators and conditional statements.
* **Files:** `t2/studentgradeCalculator.java`
* **Output:**

```text
Enter Marks for 5 subjects:
Enter marks for subject 1: 85
Enter marks for subject 2: 90
Enter marks for subject 3: 78
Enter marks for subject 4: 88
Enter marks for subject 5: 92

Total Marks = 433.0
Average = 86.6
Grade: A
```


### Task 3: Account Counter (Static & Packages)

* **Description:** Demonstrates `static` variables (shared counter), package encapsulation (`com.bank.app`), and access modifiers (`private`, `protected`, `public`).
* **Files:** `t3/com/bank/app/Main.java`, `t3/com/bank/app/Account.java`
* **Output:**

```text
Account Holder: Kowshik
Balance: 5000.0
Account Holder: Rahul
Balance: 8000.0
Total Accounts Created: 2
```

### Task 4: Array and String Manipulations

* **Description:** Demonstrates core programming logic including reversing an array, finding the second largest element, and checking if a string is a palindrome using the two-pointer technique.
* **Files:** `t4/reversearray.java`, `t4/findseclargestelement.java`, `t4/palindromecheck.java`
* **Output:**

```text
5 4 3 2 1 
Second largest element is: 70
The string is not a palindrome
```

### Task 5: Collections and Generics

* **Description:** Demonstrates the Java Collections Framework and Generics. Includes removing duplicates with `HashSet`, storing custom objects in an `ArrayList`, creating a custom generic `Box<T>` class, and calculating element frequency using a `HashMap`.
* **Files:** `t5/SetExample.java`, `t5/ListExamplec.java`, `t5/GenericExample.java`, `t5/FrequencyCount.java`
* **Output:**

```text
[10, 20, 30, 40]

Student List:
1 Kowshik
2 Suraj
3 Rahul
4 Rajesh

100
kowshik
4.17

{1=2, 2=3, 3=1, 4=1}
```

### Task 6: Custom Exception Handling

* **Description:** Demonstrates how to create and handle a custom exception (`InvalidAgeException`) using `try`, `catch`, `finally`, `throw`, and `throws` keywords.
* **Files:** `t6/CustomExceptionDemo.java`
* **Output:**

```text
Enter age: 16
Exception: Age must be 18 or above
Finally block executed
```

### Task 7: Advanced Concepts (Annotations, Enums, File I/O, Serialization)

* **Description:** Demonstrates advanced Java features including defining and reading custom runtime annotations (`@AppInfo`), using enumerations (`Status`), basic File I/O operations (writing/reading text files), and Object Serialization/Deserialization.
* **Files:** `t7/Demo.java`
* **Output:**

```text
=== Enum ===
Created : Employee{name='Kowshik', id=101, status=ACTIVE}
Is active? true

=== Annotation ===
Author : Kowshik
Version: 2.0

=== File I/O ===
Written to employee.txt
Read back: Employee{name='Kowshik', id=101, status=ACTIVE}

=== Serialization ===
Serialized to employee.ser

=== Deserialization ===
Deserialized: Employee{name='Kowshik', id=101, status=ACTIVE}
Status enum : ACTIVE | ordinal=0
```
### Task 8: Functional Programming and Streams

* **Description:** Demonstrates modern Java functional programming concepts including Core Functional Interfaces (`Supplier`, `Predicate`, `Function`, `Consumer`) and advanced Stream API operations (`filter`, `map`, `reduce`, `sorted`, `collect`, and `DoubleSummaryStatistics`).
* **Files:** `t8/FunctionalDemo.java`
* **Output:**

```text
┌─ In-stock Electronics ──────────────────────────────┐
  Laptop       | Electronics | $999.99 | stock: 5
  Monitor      | Electronics | $499.99 | stock: 2

┌─ Affordable products (< $500) ──────────────────────┐
  Headphones ($149.99)
  Desk ($349.00)
  Chair ($199.50)
  Notebook ($4.99)
  Pen ($1.49)
  Monitor ($499.99)
  Lamp ($39.99)

┌─ Total in-stock inventory value ────────────────────┐
  $9670.31

┌─ In-stock electronics after 10% discount (sorted) ──┐
  Monitor      → $449.99
  Laptop       → $899.99

┌─ Products grouped by category ──────────────────────┐
  [Electronics]
  Laptop       | Electronics | $999.99 | stock: 5
  Headphones   | Electronics | $149.99 | stock: 0
  Monitor      | Electronics | $499.99 | stock: 2
  [Furniture]
  Desk         | Furniture   | $349.00 | stock: 3
  Chair        | Furniture   | $199.50 | stock: 8
  Lamp         | Furniture   | $ 39.99 | stock: 12
  [Stationery]
  Notebook     | Stationery  | $  4.99 | stock: 50
  Pen          | Stationery  | $  1.49 | stock: 200

┌─ Out-of-stock items ─────────────────────────────────┐
  Headphones ($149.99)

┌─ Price statistics ───────────────────────────────────┐
  Count : 8
  Min   : $1.49
  Max   : $999.99
  Avg   : $280.62
  Sum   : $2244.94
  ```

