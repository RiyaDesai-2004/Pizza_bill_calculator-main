pipeline {
    agent any

    environment {
        IMAGE_NAME = "pizza-app"
        CONTAINER_NAME = "pizza-container"
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📥 Code checkout ho raha hai...'
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo '⚙️ Java code compile ho raha hai...'
                sh 'javac Driver.java'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonar-scanner'
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=pizza-bill-calculator \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=."
                    }
                    
                    // Automation: PR trigger hone par Quality Gate ka wait karega
                    // Agar SonarQube fail hua toh pipeline yahi ruk jayegi
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            // Automation: Yeh stage tabhi chalega jab Quality Gate 'OK' ho
            steps {
                echo '🐳 Docker image build ho rahi hai...'
                sh 'docker build --no-cache -t ${IMAGE_NAME} .'
            }
        }

        stage('Run & Output') {
            steps {
                sh 'docker run --name ${CONTAINER_NAME} ${IMAGE_NAME} || true'
            }
            post {
                always {
                    sh 'docker rm -f ${CONTAINER_NAME} || true'
                }
            }
        }
    }

    post {
        success {
            echo '🎉 Automation Success: PR is verified and ready to merge!'
        }
        failure {
            echo '❌ Automation Failed: Check SonarQube issues or Pipeline logs.'
        }
    }
}
