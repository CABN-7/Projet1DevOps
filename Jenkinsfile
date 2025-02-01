pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-17'
    }

    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONAR_TOKEN = 'TON_TOKEN_ICI' // Remplace par ton token réel
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
                withSonarQubeEnv('Sonar') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=Projet1DevOps -Dsonar.host.url=$SONARQUBE_URL -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }
    }
}
