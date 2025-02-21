# Terminal-Based Banking App using Java

## Summary
- The following is a basic banking app on the terminal, built by using asynchronous 
  development (Client-Server) and is the first of three.
  
## Status of Completion
- Completion loading...

## Structure
```
common/
├── src/
├── main/java/co/za/share
│   ├── Client/
│       ├── main
│           ├── Client.java
│       ├── SignUp
│           ├── SignUp.java
│           └── ValidateSignUp.java
│       └── User
│           ├── UserCredentials.java
│           └── UserDetailsToSend.java
│   └── Server
│       ├── Account
│           ├── Acount.java
│           └── ValidateTransactions.java
│       ├── main
│           ├── ClientHandler.java
│           ├── Manu.java
│           └── Server.java
│       └── UserDetails
│           ├── UniqueIdentifierCreator.java
│           └── UserCredentials.java
├── test/...
├── pom.xml
├── .gitIgnore
└── README.md
```

## How-To-Use Guide
1. git clone https://github.com/siyathembathabom/unsure-doppleganger-the-first.git
2. Navigate to Server.java and run. Thereafter navigate to Client.java, run and follow the prompts.
*Note, to transfer, another Client needs to be connected. I am working on creating a database to handle this issue.*
