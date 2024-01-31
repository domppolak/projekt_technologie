pipeline {
    environment {
        registry = "jaromirb/ccc"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
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
        stage('Deploy') {
            agent any
            steps{
                sh "docker run -d -p 8081:8081 --name ccc --pull always jaromirb/ccc:$BUILD_NUMBER"
            }
        }
    }
}