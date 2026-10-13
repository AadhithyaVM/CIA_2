pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                Checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('test'){
            steps{
                sh'mvn test'
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