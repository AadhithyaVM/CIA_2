pipeline {
    agent any // Or specify Windows/Linux agent labels if required

    stages {
        stage('Clean & Test') {
            steps {
                // Execute Maven from the repository root[cite: 5]
                sh 'mvn clean test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests=false'
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