def directories = [
        "backend",
        "cli"
]

pipeline {
    agent any

    environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub-cred')
		containerWork = false
		endToEndAvailable = false
	}

    stages {
        stage('config workspace') {
            steps {
                echo 'config workspace'

                //sh 'rm $HOME/.m2/settings.xml'
                sh 'cp ./backend/assets/settings.xml $HOME/.m2/settings.xml'
                sh 'cat  $HOME/.m2/settings.xml'
                sh 'chmod -R 777 ./'
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
                                sh './build.sh'
                            }
                        }
                    }
                }
                sh 'docker images'
            }
        }
        stage('Start containers') {
            when { expression { "${containerWork}" == 'true' } }
            steps {
                sh './build-all.sh'
            }
        }
        stage('Test end to end') {
            when { expression { "${endToEndAvailable}" == 'true' } }
            steps {

                sh './endToEnd.sh'
            }
        }
        stage('Export images on DockerHub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh './exportImages.sh'
            }
        }
    }
}