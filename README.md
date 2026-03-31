
# 📦 Pizza Bill Calculator

## 📌 Project Overview

The Pizza Bill Calculator is a simple Java-based command-line application designed to manage pizza orders and generate customer bills efficiently. It allows users to add orders, calculate total costs, and store customer details using file handling. The project also demonstrates basic object-oriented programming concepts, along with Docker containerization and Jenkins-based CI/CD automation for build and deployment.
---

## 🚀 Features
- 🍕 Add new pizza orders  
- 💸 Calculate individual bills  
- 🧾 Display customer details  
- 📁 Read/write customer data from/to a `.txt` file  

---

## 🛠️ Tech Stack
- Language: Java  
- Platform: Console-based  
- File I/O: Reads from `customers.txt`  
- Containerization: Docker  
- CI/CD: Jenkins Pipeline  

---

## 📂 Project Structure
>>>>>>> d385f058fc09980cdafaf1d7c9cd0476cb850e88
```bash
pizza_bill_calculator-main/
├── Driver.java         # Main class to run the pizza billing system
├── customers.txt       # Stores customer names and orders
<<<<<<< HEAD
└── README.md           # Project documentation
```

## ▶️ How to Run
Clone the repo

```bash
git clone https://github.com/RiyaDesai-2004/pizza_bill_calculator.git
cd pizza_bill_calculator-main

```
Compile and Run
```bash
javac Driver.java
java Driver
```
=======
├── Dockerfile          # Docker configuration
├── Jenkinsfile         # Jenkins pipeline definition
└── README.md           # Project documentation
```

## ▶️ How to Run (Without Docker)

### 1. Clone the Repository

- git clone https://github.com/RiyaDesai-2004/pizza-bill-calculator.git
- cd pizza_bill_calculator-main

### 2. Compile and Run
- javac Driver.java 
- java Driver

## 🐳 Run with Docker

### 1. Build Docker Image
```bash
docker build -t pizza-bill-calculator .
```

### 2. Run Container
```bash
docker run -it pizza-bill-calculator  
```

### 3. Persist Customer Data
```bash
docker run -it -v ${PWD}/data:/app/data pizza-bill-calculator
```

## 🔧 Jenkins Pipeline

Automates building, testing, and deploying the project.

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/RiyaDesai-2004/pizza-bill-calculator.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t pizza-bill-calculator .'
                }
            }
        }

        stage('Test Run') {
            steps {
                script {
                    sh 'docker run --rm -it pizza-bill-calculator'
                }
            }
        }

        stage('Push to Registry') {
            when {
                branch 'main'
            }
            steps {
                script {
                    sh 'docker tag pizza-bill-calculator your-dockerhub-username/pizza-bill-calculator:latest'
                    sh 'docker push your-dockerhub-username/pizza-bill-calculator:latest'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check logs.'
        }
    }
}
```

### ⚡ CI/CD Flow
- 🔄 Checkout → Pulls code from GitHub
- 🏗️ Build Docker Image → Builds the project image
- 🧪 Test Run → Runs the container for validation
- 📦 Push to Registry → Publishes the image to Docker Hub (or any registry)
>>>>>>> d385f058fc09980cdafaf1d7c9cd0476cb850e88
