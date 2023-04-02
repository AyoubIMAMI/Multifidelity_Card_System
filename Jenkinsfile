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
                        stage ("Build $directory") {
                            echo "$directory"
                            dir("./$directory") {
                                echo 'Building...'
                                sh 'mvn clean validate'
                            }
                        }
                        stage ("Test $directory") {
                            echo "$directory"
                            dir("./$directory") {
                                echo 'Testing...'
                                sh 'mvn test'
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