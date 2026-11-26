# ParaBank Selenium Automation Framework

## Project Overview

A UI test automation framework for the ParaBank demo banking application. It uses Selenium WebDriver and TestNG, with Maven for dependency and test execution management.

## Project Objectives

- Automate key ParaBank user journeys through the browser.
- Keep UI locators and page actions organized with the Page Object Model.
- Reuse browser setup, waits, and test data utilities.
- Produce test results and failure screenshots for troubleshooting.

## Technologies Used

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- DataFaker
- Allure TestNG integration

## Framework Structure

```text
ParaBank-Automation-Testing/
├── src/
│   ├── main/resources/
│   │   └── config.properties       # Create locally; ignored by Git
│   └── test/java/
│       ├── base/                   # Shared page and test setup
│       ├── pages/                  # Page Object classes
│       ├── tests/                  # TestNG test classes
│       └── utils/                  # Configuration, waits, driver, and test data
├── .gitignore
├── pom.xml
└── testng.xml
```

## Test Scenarios

- **Login:** Empty-field validation is enabled. Tests using valid or invalid account credentials, logout, and forgotten login information are currently disabled.
- **Registration:** Successful registration, empty required fields, and mismatched passwords. The existing-username test is disabled.
- **Open Account:** Open savings and checking accounts, and verify a new account appears in Accounts Overview.
- **Transfer:** Valid amount, empty amount, non-numeric amount, and same-account transfer. The same-account test currently prints the result title without asserting it.
- **Bill Payment:** Valid payment, empty required fields, mismatched account numbers, and non-numeric amount.

## Prerequisites

- Java 21 or later
- Maven 3.x
- Google Chrome or Mozilla Firefox
- Internet access to reach the ParaBank demo application

## Installation

Clone the repository and open the project folder:

```bash
git clone https://github.com/AmrAbdalla74/parabank-selenium-automation-framework.git
cd parabank-selenium-automation-framework
```

Create the local configuration file at `src/main/resources/config.properties`. This file is ignored by Git because it can contain account credentials.

Minimum configuration for the enabled tests:

```properties
timeout=20
base.url=https://parabank.parasoft.com/parabank/index.htm
```

If you enable tests that use account credentials or customer information, add the corresponding properties locally. Do not commit real credentials.

## How to Run Tests

Run the TestNG suite with Maven:

```bash
mvn clean test
```

Chrome is the default browser. To run with Firefox:

```bash
mvn clean test -Dbrowser=FIREFOX
```

## Test Reports

- Maven Surefire reports are written to `target/surefire-reports/`.
- Allure result files are written to `target/allure-results/`.
- Failure screenshots are saved under `target/screenshots/`.

The project captures Allure results but does not currently configure generation of an Allure HTML report.

## Author

**Amr Abdalla**  
[GitHub: AmrAbdalla74](https://github.com/AmrAbdalla74)
