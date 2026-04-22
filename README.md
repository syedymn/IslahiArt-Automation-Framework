![Build Status](https://github.com/syedymn/IslahiArt-Automation-Framework/actions/workflows/maven.yml/badge.svg)
# IslahiArt E-Commerce Automation Framework

A robust, data-driven Hybrid Automation Framework built to test the IslahiArt E-Commerce platform. Designed for scalability, parallel execution, and detailed reporting.

## 🚀 Tech Stack
*   **Language:** Java 17
*   **Core Library:** Selenium WebDriver 4
*   **Test Runner:** TestNG
*   **Build Tool:** Maven
*   **Design Pattern:** Page Object Model (POM)
*   **Reporting:** ExtentReports 5 & Allure
*   **Browser Management:** WebDriverManager

## 🏗 Key Features
*   **Hybrid Architecture:** Modular design separating Test Logic, Page Objects, and Test Data.
*   **Parallel Execution:** Configured via `testng.xml` with ThreadLocal for thread safety.
*   **Dynamic Waits:** No `Thread.sleep` used; relies on Explicit and Fluent Waits.
*   **Data-Driven:** Tests run multiple scenarios using TestNG `@DataProvider`.
*   **Configuration:** Global properties managed via `config.properties`.
*   **Visual Reporting:** Generates HTML dashboards with automatic screenshots on failure.

## 📂 Project Structure
```text
src/main/java
   ├── com.islahiart.base    # WebDriver Initialization (ThreadLocal)
   ├── com.islahiart.pages   # Page Classes (Locators & Actions)
   ├── com.islahiart.util    # Utilities (Screenshots, ExcelReader)
   └── com.islahiart.config  # Global Properties

src/test/java
   ├── com.islahiart.tests   # Actual Test Cases
   └── com.islahiart.listeners # Reporting Listeners
