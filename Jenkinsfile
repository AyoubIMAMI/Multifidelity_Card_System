pipeline {
    agent any
    tools {
        maven 'maven-3.9.0' 
    }
    stages {
        stage('config workspace') {
            steps {
                echo 'config workspace'
                sh '''
                    env | grep -e PATH -e JAVA_HOME
                    which java
                    java -version
                '''
                //env.JAVA_HOME="${tool 'jdk1.8.0_111'}"
                //env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
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