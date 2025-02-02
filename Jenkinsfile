pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-17'
    }

    environment {
        ARTIFACTORY_ID = 'artifactory-instance'
        SONAR_HOST_URL = '172.20.196.35:9000'
        /* 172.20.196.35 est l'aadresse Ip de ma machine (sur WSL2)
        Taper la commande suivante pour la trouver :
        ip addr show eth0 | grep "inet\b" | awk '{print $2}' | cut -d/ -f1
        */
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
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh """
                            mvn sonar:sonar \
                            -Dsonar.projectKey=Projet1DevOps \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_TOKEN
                        """
                    }
                }
            }
        }
    }
}
