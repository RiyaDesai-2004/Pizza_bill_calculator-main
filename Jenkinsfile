pipeline {
    agent any

    // GitHub webhook triggers ko enable karta hai
    triggers {
        githubPush()
    }

    environment {
        IMAGE_NAME = "pizza-app"
        CONTAINER_NAME = "pizza-container"
    }

    stages {
        stage('Checkout') {
            steps {
                // Workspace ko clean karke fresh code leta hai
                cleanWs() 
                checkout scm
                echo "🚀 Building Branch: ${env.BRANCH_NAME}"
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
                    // Tool name wahi rakhein jo Global Tool Config mein hai
                    def scannerHome = tool 'sonar-scanner'
                    
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=pizza-bill-calculator \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=."
                    }
                    
                    // Quality Gate Automation
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted: Quality Gate status is ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Docker Build & Run') {
            steps {
                echo '🐳 Docker operations start...'
                sh 'docker build --no-cache -t ${IMAGE_NAME} .'
                sh 'docker run --name ${CONTAINER_NAME} ${IMAGE_NAME} || true'
            }
            post {
                always {
                    echo '🧹 Cleaning up container...'
                    sh 'docker rm -f ${CONTAINER_NAME} || true'
                }
            }
        }
    }

    post {
        success {
            echo '🎉 Automation Success: Sab kuch sahi se chal gaya!'
        }
        failure {
            echo '❌ Automation Failed: Logs check karein.'
        }
    }
}
