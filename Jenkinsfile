pipeline 
{ 
    agent any
 tools {
        maven 'Maven310'
    }

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
{ echo 'BUild and Tests completed successfully' } 
} 
}
