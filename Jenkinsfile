pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'javac Driver.java'
            }
        }

        // --- SonarQube Stage Start ---
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Tool name jo aapne Jenkins Global Tool Configuration mein rakha tha
                    def scannerHome = tool 'sonarqube' 
                    
                    // Server name jo aapne System Configuration mein rakha tha
                    withSonarQubeEnv('sonarqube-server') {
                        sh "${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=pizza-bill-calculator \
                        -Dsonar.projectName='Pizza Bill Calculator' \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=."
                    }
                }
            }
        }
        // --- SonarQube Stage End ---

        stage('Check Class File') {
            steps {
                echo 'Checking compiled output...'
                sh 'ls -la *.class'
            }
        }
    }

    post {
        success {
            echo 'Pipeline SUCCESS! ✅'
        }
        failure {
            echo 'Pipeline FAILED! ❌'
        }
    }
}
