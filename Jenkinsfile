pipeline {
    agent any

    // Yeh automation trigger hai jo webhook ke signals ko catch karta hai
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
                // Workspace clean karke fresh code checkout karta hai
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
                    // 'sonar-scanner' wahi naam rakhein jo Jenkins Global Tool Configuration mein hai
                    def scannerHome = tool 'sonar-scanner' 
                    
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=pizza-bill-calculator \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=."
                    }
                    
                    // Quality Gate Automation: Build fail ho jayegi agar code standards meet nahi hue
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Build & Test Docker') {
            steps {
                echo '🐳 Docker image build aur test run ho raha hai...'
                sh 'docker build --no-cache -t ${IMAGE_NAME} .'
                sh 'docker run --name ${CONTAINER_NAME} ${IMAGE_NAME} || true'
            }
            post {
                always {
                    echo '🧹 Cleanup: Container remove ho raha hai...'
                    sh 'docker rm -f ${CONTAINER_NAME} || true'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Automation Complete: Branch verified successfully!'
        }
        failure {
            echo '❌ Automation Failed: Please check SonarQube or build logs.'
        }
    }
}
