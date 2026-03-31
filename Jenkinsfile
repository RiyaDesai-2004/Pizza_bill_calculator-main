pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'javac Driver.java'
            }
        }

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
