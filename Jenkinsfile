pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1' // Correspond au nom que tu as donné à l'installation Maven dans Jenkins
        jdk 'jdk-17'
    }

    environment {
        SONARQUBE_URL = 'http://localhost:9000'  // Adresse de SonarQube
        ARTIFACTORY_ID = 'artifactory-instance' // Nom configuré dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/CABN-7/Projet1DevOps.git' // Remplace par ton repo Git
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
