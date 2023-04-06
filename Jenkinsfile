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
                    sh 'docker ps'                
                    try{sh 'docker stop i_saw_where_you_parked_last_summer'}catch (Exception e){echo "no container to close"}
                    try{sh 'docker stop bank'}catch (Exception e){echo "no container to close"}
                    try{sh 'docker stop db'}catch (Exception e){echo "no container to close"}
                    try{sh 'docker stop server'}catch (Exception e){echo "no container to close"}
                    try{sh 'docker stop cli'}catch (Exception e){echo "no container to close"}
                    try{sh 'docker rm bank db server cli i_saw_where_you_parked_last_summer'}catch (Exception e){echo "no container to close"}
                    try{sh 'docker rmi $(docker images --filter "dangling=true" -q --no-trunc)'}catch (Exception e){echo "no container to close"}

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
        stage('Pull latest artifacts'){
            when { 
                branch 'main'
                expression { "${skipSteps}" == 'false' } 
            }
            steps {
                script {
                    try {
                        sh 'rm -rf releases'
                    } catch (Exception e) {
                    echo "The directory doesn't exist"
                    }

                    sh 'mkdir releases'
                    echo 'Pulling releases ...'

                    // Backend pulling
                    def last_backend_version = sh(returnStdout: true, script: 'python3 artifactory_pull/backend_latest_version.py').split("\n")[1]
                    echo "Downloading backend (v${last_backend_version})"
                    sh "cd ./releases && echo \\n | jf rt dl  --recursive --user=admin --password=zEzEBf7mD2aCHA8XG4! --url=http://134.59.213.138:8002/artifactory 'libs-release-local/fr/polytech/isa-devops-22-23-team-h-23/${last_backend_version}/*'"
                    
                    // Cli pulling
                    def last_cli_version = sh(returnStdout: true, script: 'python3 artifactory_pull/cli_latest_version.py').split("\n")[1]
                    echo "Downloading cli (v${last_cli_version})"
                    sh "cd ./releases && echo \\n | jf rt dl  --recursive --user=admin --password=zEzEBf7mD2aCHA8XG4! --url=http://134.59.213.138:8002/artifactory 'libs-release-local/fr/univcotedazur/fidelity/cli/${last_cli_version}/*'"
                
                    sh "ls -l ./releases"
                }
            }       
        }
        stage('Create dockers images') {
            when { 
                expression { "${skipSteps}" == 'false' }
            }
            steps {
                script {
                    def last_backend_version = sh(returnStdout: true, script: 'python3 artifactory_pull/backend_latest_version.py').split("\n")[1]
                    def last_cli_version = sh(returnStdout: true, script: 'python3 artifactory_pull/cli_latest_version.py').split("\n")[1]
                    if( env.BRANCH_NAME == 'main'){     
                        echo "Downloading cli (v${last_cli_version})"           
                        echo "last_backend_version = ${last_backend_version} and last_cli_version = ${last_cli_version}"
                        sh "cd"
                    }
                    else{
                        directories.each { directory ->
                        stage ("Create $directory image") {
                            echo "$directory"
                            dir("./$directory") {
                                echo 'Testing...'
                                sh './build.sh '
                                }
                            }
                        }
                    }
                    
                }
                sh 'docker images'
            }
        }
        stage('Start containers') {            
            when { 
                expression { "${containerWork}" == 'true' && "${skipSteps}" == 'false' &&  env.BRANCH_NAME != 'main'} 
            }
            steps{
                sh 'docker ps'
                sh './build-all.sh'
                sh 'cat ${WORKSPACE}/cli/scripts/demo.txt'
                sh './run-all.sh'
            }
        }
        stage('Test end to end') {
            when { 
                expression { "${endToEndAvailable}" == 'true' && "${skipSteps}" == 'false' &&  env.BRANCH_NAME != 'main'} }
            steps {
                sh 'apt-get install -y socat'
                sh 'apt install -y python3-pip'
                sh 'pip install psycopg2-binary'
                sh 'docker ps'
                                              
                dir("./DevopsCli"){
                    sh 'python3 ./endToEnd.py'
                    sh 'python3 ./printBdContent.py'
                }
                
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