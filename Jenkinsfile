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
                    // Tool name jo aapne 'sonar-scanner' rakha hai
                    def scannerHome = tool 'sonar-scanner'
                    
                    // Server name jo Jenkins System settings mein hai
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=pizza-bill-calculator \
                        -Dsonar.projectName='Pizza Bill Calculator' \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=."
                    }

                    // Quality Gate Check: Agar SonarQube mein issues mile toh build fail ho jayegi
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        // Yeh stages tabhi chalenge agar SonarQube pass ho jaye
        stage('Build Docker Image') {
            steps {
                echo '🐳 Docker image build ho rahi hai...'
                sh 'docker build --no-cache -t ${IMAGE_NAME} .'
            }
        }

        stage('Run & Output') {
            steps {
                echo '🚀 Container run ho raha hai...'
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
            echo '🎉 PR Analysis Successful! Status sent to GitHub.'
        }
        failure {
            echo '❌ Pipeline failed! Please check SonarQube report.'
        }
    }
}
