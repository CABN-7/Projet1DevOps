pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-17'
    }

    environment {
        ARTIFACTORY_ID = 'artifactory-instance'
        SONAR_HOST_URL = 'http://172.17.0.3:9000'
        /* 172.17.0.3
        Taper la commande suivante pour la trouver :
        docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' sonarqube-custom
        */
        ARTIFACTORY_REPO = 'libs-release-local'
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

        stage('Upload to Artifactory') {
                    steps {
                        script {
                            def server = Artifactory.server 'artifactory-instance'
                            def buildInfo = Artifactory.newBuildInfo()

                            def uploadSpec = """{
                                "files": [{
                                    "pattern": "target/*.jar",
                                    "target": "$ARTIFACTORY_REPO/"
                                }]
                            }"""

                            server.upload spec: uploadSpec, buildInfo: buildInfo
                            server.publishBuildInfo buildInfo
                        }
                    }
        }
    }
}
