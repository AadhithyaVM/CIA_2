pipeline 
{ 
    agent any


stages 
{ 
    stage('Checkout') 
    { steps 
    { checkout scm 
    } 
    }

stage('Build') 
{ steps 
{ 
    bat 'mvn clean package' 
    } 
    }

stage('Test') 
{ steps 
{ bat 'mvn test' 
} 
} 
}

post 
{ always 
{ junit testResults: '/target/surefire-reports/TEST-*.xml', allowEmptyResults: true }

success 
{ archiveArtifacts artifacts: '/target/*.jar', fingerprint: true } 
} 
}