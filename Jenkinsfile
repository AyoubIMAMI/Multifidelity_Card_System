pipeline {
    agent any

    stages {
            stage('config workspace') {
                steps {
                    echo 'config workspace'
                    //sh 'rm $HOME/.m2/settings.xml'
                    sh 'cp ./backend/assets/settings.xml $HOME/.m2/settings.xml'
                    sh 'cat  $HOME/.m2/settings.xml'
                }
            }
            stage('Export backend jar') {
                when { branch "Develop" }
                stages {
                    stage('Build') {
                        steps {
                            dir("./backend") {
                                echo 'Building'
                                sh 'mvn clean validate'
                            }
                        }
                    }
                    stage('Test') {
                        steps {
                            dir("./backend") {
                                echo 'Building'
                                sh 'mvn test'
                            }
                        }
                    }
                    stage('Deploy') {
                        when { branch "devops" }
                        steps {
                            dir("./backend") {
                                sh 'mvn deploy -U -e'
                            }
                            //sh 'curl -u admin:zEBf7mD2aCHA8XG4 -O http://vmpx08.polytech.unice.fr:8002/artifactory/libs-snapshot-local/fr/polytech/isa-devops-22-23-team-h-23/1.0-SNAPSHOT/isa-devops-22-23-team-h-23-1.0-20230330.071841-1.jar'
                            //sh 'ls -l'
                        }
                    }
                }
            }


        }
}