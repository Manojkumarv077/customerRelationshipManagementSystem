# 🧠 Customer Relationship Management (CRM) System

A Spring Boot-based CRM system for managing customer data, tracking sales, recording interactions, and generating business insights.

---

## 🚀 Features

- 👤 **Customer Profile Management** – Add, update, delete customers
- 💸 **Sales Tracking** – Record product sales and associate them with customers
- 📞 **Interaction Logs** – Log emails, calls, meetings per customer
- 📊 **Reporting Module** – View total sales per customer, sales by month, and most active customers
- 🧪 **JUnit & Mockito Testing** – Unit tests for all service layers
- 📋 **Exception Handling** – Global error responses
- 🧾 **Logging** – SLF4J-based logs for important actions and errors

---

## 🛠️ Technologies

| Layer       | Stack                       |
|-------------|-----------------------------|
| Backend     | Spring Boot, JPA, Hibernate |
| Database    | MySQL                       |
| Testing     | JUnit 5, Mockito, MockMVC   |
| Tools       | Postman, Maven, SLF4J       |

---

## 🧑‍💻 API Endpoints

### 🔹 Customer
- `POST /api/customers` – Create customer
- `GET /api/customers` – Get all customers
- `GET /api/customers/{id}` – Get customer by ID
- `PUT /api/customers/{id}` – Update customer
- `DELETE /api/customers/{id}` – Delete customer

### 🔹 Sales
- `POST /api/sales` – Record a sale
- `GET /api/sales/customer/{customerId}` – Sales by customer

### 🔹 Interactions
- `POST /api/interactions` – Add interaction
- `GET /api/interactions/customer/{customerId}` – Logs for customer

### 🔹 Reports
- `GET /api/reports/sales/monthly` – Sales by month
- `GET /api/reports/sales/customer` – Sales per customer
- `GET /api/reports/customers/active` – Most active customers

---

## 🧪 Run Tests

```bash
./mvnw test
