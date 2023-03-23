pipeline {
    agent any
    tools {
        maven 'maven-3.6.3'
        jdk 'jdk-17.0.6'
    }
    stages {
        stage('config workspace') {
            steps {
                echo 'config workspace'
                sh 'rm -rf $HOME/.m2/repository'
                sh 'cp ./backend/assets/settings.xml $HOME/.m2/settings.xml'
                sh '''
                    java -version
                    javac -version
                    mvn -v
                    echo $JAVA_HOME
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