pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-17'
    }

    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONAR_TOKEN = 'squ_739b34d27efe77880ac79f4a4661e4a9725cd5bb' // Remplace par le vrai token
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
                    /*SonarQube va automatiquement créer le projet "Projet1DevOps" s'il n'existe pas encore.
                    Mais attention !
                      Si l'instance SonarQube est configurée avec un mode d’administration stricte,
                      il faudra activer la création automatique des projets dans l’interface d’administration. */
                }
            }
        }
    }
}
