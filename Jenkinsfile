pipeline {
    agent any

    // Yeh trigger GitHub webhook ke signal ko sunta hai
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
                // Yeh automatically woh branch uthayega jisme change hua hai
                checkout scm
                echo "🚀 Building Branch: ${env.BRANCH_NAME}"
            }
        }

        stage('Compile') {
            steps {
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
                    
                    // Quality Gate: Agar SonarQube fail toh build STOP
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
                sh 'docker build --no-cache -t ${IMAGE_NAME} .'
                sh 'docker run --name ${CONTAINER_NAME} ${IMAGE_NAME} || true'
            }
            post {
                always {
                    sh 'docker rm -f ${CONTAINER_NAME} || true'
                }
            }
        }
    }
}
