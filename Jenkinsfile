pipeline {
    environment {
        registry = "jaromirb/ccc"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    agent label 'docker-build'
    stages {
        stage('Cloning our Git') {
            steps {
                git 'https://github.com/pwr-twwo/ci-cd-environment-sro11-3.git'
            }
        }
        stage('Building our image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
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
        stage('Deploy') {
            agent any
            steps{
                sh "docker run -d --name ccc --pull always jaromirb/ccc"
            }
        }
    }
}