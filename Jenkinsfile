def directories = [
        "backend",
        "cli"
]

pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }

    environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub-cred')
		containerWork = true
		endToEndAvailable = true
        skipSteps = false
	}

    stages {
        stage('config workspace') {
            steps {
                echo 'config workspace'

                // Check if the commit is from maven-release-plugin
                script {
                    def commitMessage = sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
                    if (commitMessage.contains('[maven-release-plugin]') || commitMessage.contains('[Jenkins]')) {
                        echo 'Exiting building'
                        // Sortir de la pipeline et ne pas executer les aures stage avec SUCCESS
                        skipSteps = true
                        return
                    }
                    try {
                        sh 'docker stop bank'
                        sh 'docker stop db'
                        sh 'docker stop server'
                        sh 'docker stop cli'
                        sh 'docker rm bank db server cli'
                    } catch (Exception e) {
                        echo "no container to close"
                    }
                }
               
                // Copying settings.xml into .m2 folder
                sh 'cp ./backend/assets/settings.xml $HOME/.m2/settings.xml'
                sh 'cat  $HOME/.m2/settings.xml'

                sh 'chmod -R 777 ./'
                //sh 'docker images'
            }
        }
        stage('Export backend and cli') {
            when { 
                expression { "${skipSteps}" == 'false' }
            }
            steps {
                script {
                    if(env.BRANCH_NAME != 'main'){
                        directories.each { directory ->
                            stage ("Test $directory") {
                                if(env.BRANCH_NAME == 'Develop'){
                                    echo "$directory"
                                    dir("./$directory") {
                                        echo 'Testing...'
                                        sh 'mvn test'
                                    }
                                }
                            }
                            stage ("Building $directory") {
                                echo "$directory"
                                dir("./$directory") {
                                    echo 'Building...'
                                    sh 'mvn clean package'
                                }
                            }
                            stage ("Deploy $directory") {
                                if(env.BRANCH_NAME == 'Develop'){
                                    echo "$directory"
                                    dir("./$directory") {
                                        echo 'Deploying...'
                                        sh 'mvn deploy'
                                    }
                                }                                
                            }
                        }
                    }else{
                        directories.each { directory ->
                            stage ("Prepare and Perform release $directory") {
                                echo "$directory"

                                dir("./$directory") {
                                    // Check for unpushed modifications
                                    def unpushedChanges = sh(returnStatus: true, script: 'git diff --exit-code && git diff --cached --exit-code')
                                    if (unpushedChanges != 0) {
                                        withCredentials([gitUsernamePassword(credentialsId: 'KilianBonnet-GitHub-creds', gitToolName: 'git-tool')]) {
                                            sh 'git add .'
                                            sh 'git stash'

                                            sh 'git checkout main'
                                            sh 'git pull'

                                            sh 'git stash apply'
                                            sh 'git commit -a -m "[Jenkins] Applying changes"'
                                            sh 'git push'
                                        }
                                    }

                                    // Performing release
                                    echo 'Verifying ...'
                                    sh 'mvn clean verify'
                                    echo 'Prepare and perform...'
                                    withCredentials([gitUsernamePassword(credentialsId: 'KilianBonnet-GitHub-creds', gitToolName: 'git-tool')]) {
                                        sh 'git checkout main'
                                        sh 'git pull'
                                        sh 'echo "\\n\\n\\n" | mvn release:prepare -Dresume=false'
                                        sh 'mvn release:perform'
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Pull ltest artifacts'){
            when { 
                branch 'main'
                expression { "${skipSteps}" == 'false' } 
            }
            script {
                try {
                    sh 'rmdir releases'
                } catch (Exception e) {
                echo "The directory doesn't exist"
                }
            }
            
            sh 'mkdir releases'
            echo 'Pulling releases ...'
            sh 'python pull_artifactory.py'
            sh "ls -l"
                 
        }
        stage('Create dockers images') {
            when { 
                expression { "${skipSteps}" == 'false' }
            }
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
            when { 
                expression { "${containerWork}" == 'true' && "${skipSteps}" == 'false'} 
            }
            steps{
                sh 'docker ps'
                sh './build-all.sh'
                sh './run-all.sh'                        
            }
        }
        stage('Test end to end') {
            when { 
                expression { "${endToEndAvailable}" == 'true' && "${skipSteps}" == 'false' } }
            steps {
                sh 'apt-get install -y socat'
                sh 'apt install -y python3-pip'
                sh 'pip install psycopg2-binary'
                sh 'docker ps'
                sh 'python3 ./DevopsCli/endToEnd.py'
            }
        }
        stage('Export images on DockerHub (main)') {
            when { 
                branch 'main'
                expression { "${skipSteps}" == 'false' } 
            }
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh './exportImages.sh'
            }
        }
    }
}