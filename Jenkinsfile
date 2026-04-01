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

        stage('Build Docker Image') {
            steps {
                echo '🐳 Docker image build ho rahi hai...'
                sh 'docker build -t ${IMAGE_NAME} .'
                echo '✅ Docker image ready: ${IMAGE_NAME}'
            }
        }

        stage('Run & Output') {
            steps {
                echo '🚀 Container run ho raha hai...'
                sh '''
docker run -i --name ${CONTAINER_NAME} ${IMAGE_NAME} <<EOF || true
TestUser
veg
1
2
no
EOF
                '''
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
            echo '🎉 Pipeline successfully complete hua!'
        }
        failure {
            echo '❌ Pipeline fail hua — logs check karo!'
        }
    }
}
