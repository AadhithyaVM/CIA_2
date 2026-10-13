pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('test'){
            steps{
                bat'mvn test'
            }
        }
    }

    post {
        always {
            // Publish JUnit test reports from the child module path[cite: 5]
            junit 'course-registration-app/target/surefire-reports/TEST-*.xml'
        }
        success {
            // Archive packaged artifacts from the child module path[cite: 5]
            archiveArtifacts artifacts: 'course-registration-app/target/*.jar', fingerprint: true
        }
    }
}