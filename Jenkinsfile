pipeline {
    agent any
    tools {
        maven 'maven-3.9.0' 
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building.. Iraana II'
                sh '/backend'
                sh 'ls -l'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}