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
                echo '✅ Compile successful!'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Tool name jo aapne Jenkins 'Tools' mein rakha hai
                    def scannerHome = tool 'sonar-scanner'
                    
                    // Server name jo aapne 'System' configurations mein rakha hai
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=pizza-bill-calculator \
                        -Dsonar.projectName='Pizza Bill Calculator' \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=."
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo '🐳 Docker image build ho rahi hai...'
                sh 'docker build --no-cache -t ${IMAGE_NAME} .'
                echo '✅ Docker image ready: ${IMAGE_NAME}'
            }
        }

        stage('Run & Output') {
            steps {
                echo '🚀 Container run ho raha hai...'
                sh 'docker run --name ${CONTAINER_NAME} ${IMAGE_NAME} || true'
            }
            post {
                always {
                    echo 'Cleaning up container...'
                    sh 'docker rm -f ${CONTAINER_NAME} || true'
                }
            }
        }
    }

    post {
        success {
            echo '🎉 Pipeline successfully complete hua!'
        }
        failure {
            echo '❌ Pipeline fail hua — logs check karo!'
        }
    }
}
