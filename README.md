"# spring-java-bank-api" 

# Bank Application

A bank Api backed application developed using Spring Boot.  
This project demonstrates basic banking financial  operations such as bank account management, transactions, fund transfers and scheduled fund transfers. 


- **Create Account**: Allows users to create a new bank account.
- **Get Account Details**: Fetches details of a specific account by its ID.
- **Deposit Funds**: Deposits a specified amount into an account.
- **Withdraw Funds**: Withdraws a specified amount from an account.
- **Transfer Funds**: Transfers funds between two accounts.
- **Get All Accounts**: Retrieves a list of all accounts.
- **Delete Account**: Deletes an account by its ID.
- **Get Account Transactions**: Fetches a list of transactions associated with an account.
- **Schedule a Transfer**: Schedule Transfer Funds between 2 accounts in a future date.
- **Get future Scheduled Transfer By Scheduled Transfers ID**: Fetch a future Scheduled Transfer by its ID.
- **Delete Scheduled Transfer**: Deletes a future Scheduled Transfer by its ID.
- **Get List of Scheduled Transfers By Account ID**: Fetches a list of future Scheduled Transfers associated with an account.

## Technologies Used

- **Java**: The primary programming language.
- **Spring Boot**: Framework for building the REST API and managing dependencies.
- **JPA/Hibernate**: For database interactions and ORM.
- **MySQL Database**: Database for development and testing. Use this command ( CREATE DATABASE banking_api; ) to create the database.
- **Maven**: Build and dependency management tool.

## Project Structure

- **Controller**: Contains REST controllers to handle HTTP requests and responses.
- **DTO**: Data Transfer Objects used to transfer data between layers.
- **Entity**: JPA entities representing the database tables.
- **Exception**: Custom exception classes for error handling.
- **Mapper**: Utility classes for mapping between entities and DTOs (Converting entities to DTOs & vice versa).
- **Repository**: Interfaces for CRUD operations on entities.
- **Service**: Business logic and service layer.
- **Config**: Config Classes.
- **Test**: Unit Tests for controller layer and service layer.


## Getting Started

### Prerequisites

- JDK 22 or later
- Maven

## API Endpoints

The application will start on `http://localhost:8080` or any other unused port, if 8080 is not free.

- **Create Account**: `POST /api/accounts`
- **Get Account by ID**: `GET /api/accounts/{id}`
- **Deposit Funds**: `PUT /api/accounts/{id}/deposit`
- **Withdraw Funds**: `PUT /api/accounts/{id}/withdraw`
- **Transfer Funds**: `POST /api/accounts/transfer`
- **Get All Accounts**: `GET /api/accounts`
- **Delete Account**: `DELETE /api/accounts/{id}`
- **Get Account Transactions**: `GET /api/accounts/{id}/transactions`
- **Schedule a Transfer**: `POST /api/accounts/scheduleTransferfund`
- **Get Scheduled Transfer By Scheduled Transfer ID**: `GET /api/accounts/scheduleTransferfund`
- **Delete Scheduled Transfer**: `DELETE /api/accounts/scheduleTransferfund.{id}`
- **Get List of Scheduled Transfers By Account ID**: `GET /api/accounts/{id}/scheduleTransfer`



## Example Requests

**Create Account**:
```http
POST /api/accounts
Content-Type: application/json

{
  "accountHolderName": "Billy Lester",
  "balance": 1000.00
}
```

**Deposit Funds**:
```http
PUT /api/accounts/1/deposit
Content-Type: application/json

{
  "amount": 1000.00
}
```

**Transfer Funds**:
```http
POST /api/accounts/transfer
Content-Type: application/json

{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 8800.00
}
```

Schedule Transfer Funds**:
```http
POST /api/accounts/scheduleTransferfund
Content-Type: application/json


{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 95000.00,
  "transferDate":"2025-03-11T12:20:15"

}
=====
Sample Response From Server:

{
    "id": 9,
    "fromAccountId": 1,
    "toAccountId": 2,
    "amount": 95000.0,
    "transferDate": "2025-03-11T12:20:15",
    "transferId": "f7dfb666-d23e-4f4c-add1-af0af170d2c0",
    "timestamp": "2025-02-02T18:23:35"
}


```


## Acknowledgments

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [JPA/Hibernate Documentation](https://hibernate.org/)

