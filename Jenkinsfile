pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-17'
    }

    environment {
        SONARQUBE_URL = 'Sonar' // Nom configuré dans Jenkins
        ARTIFACTORY_ID = 'artifactory-instance'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/CABN-7/Projet1DevOps.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonar') { // Doit correspondre au nom défini dans la config de Jenkins
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}
