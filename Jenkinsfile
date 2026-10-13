pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                bat 'java -version'
                bat '"C:\\Maven\\apache-maven-3.10.0\\bin\\mvn.cmd" -version'
            }
        }

        stage('Build') {
            steps {
                bat '"C:\\Maven\\apache-maven-3.10.0\\bin\\mvn.cmd" clean package'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Maven\\apache-maven-3.10.0\\bin\\mvn.cmd" test'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: '**/target/surefire-reports/*.xml'
        }
    }
}
