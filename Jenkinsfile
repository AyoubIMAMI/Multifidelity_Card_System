pipeline {
    agent any
    tools {
        maven 'maven-3.9.0' 
    }
    stages {
        stage('config workspace') {
            steps {
                echo 'config workspace'
                sh 'ls -l'
                sh 'cd ./backend'
                sh 'ls -l'
                //sh 'chmod -c -R ./backend'
            }
        }
        stage('Build') {
            steps {
                dir("./backend") {
                    echo 'Building.. Iraana II'
                    sh 'ls -l'
                    sh 'mvn clean package'
                }
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