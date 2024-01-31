pipeline {
    environment {
        registry = "jaromirb/ccc"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
        remote = [:]
    }
    agent { label 'docker-build' }
    stages {
        stage('Cloning our Git') {
            steps {
                git branch: 'main', 
                credentialsId: 'dominik', 
                url: 'https://github.com/domppolak/projekt_technologie'
            }
        }
        stage('Building our image') {
            steps{
                script {
                    dockerImage = docker.build(registry + ":$BUILD_NUMBER", "./sources/CityCardCore")
                }
            }
        }
        stage('Push our image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Cleaning up') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
        stage("Deploy"){
            steps{
                script{
                    withCredentials([sshUserPrivateKey(credentialsId: 'dockerb2', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                        remote.user = userName
                        remote.identityFile = identity
                        remote.host = 'ec2-44-211-218-110.compute-1.amazonaws.com'
                        remote.allowAnyHosts = true
                        
                        sshCommand remote: remote, command: 'docker rm -f ccc'
                        sshCommand remote: remote, command: 'docker run -d -p 8081:8081 --name ccc  --network cc-n --pull always jaromirb/ccc:$BUILD_NUMBER'
            
                    }
                }
            }
        }
    }
}