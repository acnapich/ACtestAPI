pipeline {
    agent any
    stages {
        stage('Clean') { 
            steps {
                sh 'mvn clean' 
            }
        }    
        stage('Build') { 
            steps {
                sh 'mvn package' 
            }
        }
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
        }
    }
}