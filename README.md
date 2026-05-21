# Library System

A simple console-based Library Management System implemented in Java. It supports user management (administrators and customers), book catalog management, cart and orders, borrowing transactions, ratings, notifications, and discounts.

## Features
- User accounts (Administrator, Customer) with signup, login, update, delete
- Book catalog with add/update/remove operations
- Shopping cart and order placement with payment options (cash, credit card)
- Borrowing transactions (borrow/return)
- Order history and transaction history
- Basic validation for usernames, passwords, emails, and phone numbers

## Project Structure
```
src/
  Main.java                # Application entry point
  Account/                 # Account classes and exceptions
  Administrator/           # Administrator functionality
  Book/                    # Book model
  Borrower/                # Borrowing logic
  Cart/                    # Cart and CartItem
  Customer/                # Customer-related classes and exceptions
  Discount/                # Discount strategies
  Notification/            # Notification model
  Order/                   # Order & OrderItem
  Rating/                  # Rating model
  Transaction/             # Transaction and TransactionManager
```

## Requirements
- Java 11 or later

## Build & Run
From the repository root (where `src/` is located), run the following commands in PowerShell:

```powershell
# Compile all Java sources into the 'out' directory
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)

# Run the application
java -cp out Main
```

Alternatively, on Unix-like shells:

```bash
mkdir -p out
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out Main
```

If you prefer Maven (recommended for a professional project), use the provided `pom.xml`:

```powershell
mvn -DskipTests package
java -cp target/classes Main
```

## Notes & Known Issues
- The application uses a serialized file `Details.ser` in the working directory to persist users and books. If this file does not exist the program will start with default sample users and books.
- The console input uses `Scanner` and mixes `nextInt()` and `nextLine()`; this can cause newline-consumption issues. If you see skipped prompts, press Enter or modify input handling to call `nextLine()` after `nextInt()` to consume the newline.
- Some switch blocks in `Main.java` don't include `break` statements intentionally or unintentionally. This may cause fall-through behavior; review the related logic if you observe unexpected flows.

## Suggestions to Professionalize
- Add a build tool such as Maven or Gradle with a proper project layout and dependencies.
- Add unit tests (JUnit) for core logic (Account validation, Cart, Order processing).
- Replace console-based persistence with a lightweight database (e.g., SQLite or H2) or JSON storage.
- Improve CLI input handling and add a simple menu-driven UI framework.
- Add logging (SLF4J + Logback) instead of System.out for better debugging and production readiness.

## License
This project is provided as-is. Add a LICENSE file to declare the desired license.

## Contact
For questions or contributions, open an issue or submit a pull request.
