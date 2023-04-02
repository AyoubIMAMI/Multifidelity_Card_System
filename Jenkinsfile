def directories = [
        "backend",
        "cli"
]

pipeline {
    agent any

    stages {
        stage('Export backend and cli') {
            steps {
                script {
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
                        if(env.BRANCH_NAME == 'devops'){
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
    }
}