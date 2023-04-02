def directories = [
        "backend",
        "cli"
]

pipeline {
    agent any

    stages {
    //
        stage('config workspace') {
            steps {
                echo 'config workspace'

                //sh 'rm $HOME/.m2/settings.xml'
                sh 'cp ./backend/assets/settings.xml $HOME/.m2/settings.xml'
                sh 'cat  $HOME/.m2/settings.xml'
                //sh 'docker images'
            }
        }
        stage('Export backend and cli') {
            steps {
                script {
                    if(env.BRANCH_NAME != 'main'){
                        directories.each { directory ->
                            stage ("Test $directory") {
                                echo "$directory"
                                dir("./$directory") {
                                    echo 'Testing...'
                                    sh 'mvn test'
                                }
                            }
                            stage ("Building $directory") {
                                echo "$directory"
                                dir("./$directory") {
                                    echo 'Testing...'
                                    sh 'mvn clean package'
                                }
                            }
                            stage ("Deploy $directory") {
                                echo "$directory"
                                dir("./$directory") {
                                    echo 'Deploying...'
                                    sh 'mvn deploy'
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Create dockers images') {
            steps {
                script {
                    directories.each { directory ->
                        stage ("Create $directory image") {
                            echo "$directory"
                            dir("./$directory") {
                                echo 'Testing...'
                                sh 'sudo ./build.sh'
                            }
                        }
                    }
                }
                sh 'docker images'
            }
        }
        stage('Start containers') {
            steps {
                sh './build-all.sh'
            }
        }
        stage('Test end to end') {
            steps {
                sh './endToEnd.sh'
            }
        }
        stage('Export images on DockerHub') {
            steps {
                sh './endToEnd.sh'
            }
        }
    }
}