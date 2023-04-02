def map = [
        Bob  : 42,
        Alice: 54,
        Max  : 33
]

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
        stage('Test and Export') {
            when { branch "Develop" }
            steps {
                script {
                    def directories = ['backend', 'cli']
                    for (directory in directories) {
                        stage ("Export $directory jar") {
                            stages {
                                stage('Build') {
                                    steps {
                                        dir("./$directory") {
                                            echo 'Building'
                                            sh 'mvn clean validate'
                                        }
                                    }
                                }
                                stage('Test') {
                                    steps {
                                        dir("./$directory") {
                                            echo 'Building'
                                            sh 'mvn test'
                                        }
                                    }
                                }
                                stage('Deploy') {
                                    steps {
                                        dir("./$directory") {
                                            sh 'mvn deploy -U -e'
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}