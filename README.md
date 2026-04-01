

# 📦 Pizza Bill Calculator

## 📌 Project Overview

- The Pizza Bill Calculator is a Java-based command-line application that manages pizza orders and generates customer bills. It supports veg and non-veg pizza selection, quantity management, bill generation, and customer data storage using file handling.
The project is fully containerized with Docker and automated using a Jenkins CI/CD Pipeline that handles checkout, compilation, Docker image building, and running the application end-to-end.
---

## 🚀 Features
- 🍕 Choose from Veg or Non-Veg pizza menu
- 🔢 Select pizza number and quantity
- ➕ Add multiple pizzas to a single order
- 💸 Auto-calculate total bill
- 🧾 Save customer order details to customers.txt
- 🐳 Dockerized for consistent execution
- 🔧 Jenkins Pipeline for full CI/CD automation
---

## 🛠️ Tech Stack
| Technology | Purpose |
|---|---|
| Java 18 | Core application language |
| Docker | Containerization |
| Jenkins | CI/CD Pipeline automation |
| GitHub | Source code repository |
| Ubuntu VM | Jenkins host environment |
--- 



## 📂 Project Structure 
```

pizza_bill_calculator-main/
├── Driver.java       # Pizza, Customer, Driver classes
├── customers.txt     # Customer bill records
├── input.txt         # Predefined input for Jenkins/Docker
├── Dockerfile        # Docker image config
├── Jenkinsfile       # Jenkins pipeline
└── README.md         # Project documentation

```
---

## ⚙️ Jenkins Pipeline (CI/CD)

The Jenkins pipeline automates the full workflow:
```
📥 Checkout → ⚙️ Compile → 🐳 Docker Build → 🚀 Run & Output
```
## Screenshot

![image alt](https://github.com/RiyaDesai-2004/Pizza_bill_calculator-main/blob/2447cebebffc3379baf9b3c10e18d6c758b6ae8a/Pipeline.jpg)

---


## ⚡ CI/CD Flow

| Stage | Description |
|---|---|
| 📥 Checkout | Pulls latest code from GitHub |
| ⚙️ Compile | Compiles `Driver.java` using `javac` |
| 🐳 Docker Build | Builds fresh Docker image with `--no-cache` |
| 🚀 Run & Output | Runs container with predefined input, shows bill output |
--- 

## 🖥️ Setup Used
```
Windows Machine
  └── VM (Ubuntu) — Jenkins (WAR file) + Docker installed
        └── Jenkins runs pipeline
              └── Docker builds & runs pizza-app container

Jenkins Access
        http://<your-VM-IP>:8080
```
--- 

