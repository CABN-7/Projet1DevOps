pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk-17'
    }

    environment {
        ARTIFACTORY_ID = 'artifactory-instance'  // ID défini dans Jenkins
        SONAR_HOST_URL = 'http://172.17.0.3:9000'
        /* 172.17.0.3
        Taper la commande suivante pour la trouver :
        docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' sonarqube-custom
        */
        ARTIFACTORY_REPO = 'libs-release-local'  // Nom du dépôt cible sur Artifactory
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
                withSonarQubeEnv('Sonar') {  // Utilisation de l'environnement Sonar défini dans Jenkins
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        // Récupération du token SonarQube depuis les credentials de Jenkins
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
                    def server = Artifactory.server(ARTIFACTORY_ID) // Connexion à Artifactory
                    def buildInfo = Artifactory.newBuildInfo()

                    withCredentials([
                        usernamePassword(credentialsId: 'artifactory-credentials', usernameVariable: 'ARTIFACTORY_USER', passwordVariable: 'ARTIFACTORY_PASS')
                    ]) {
                        // Récupération des identifiants d’Artifactory
                        def uploadSpec = """{
                            "files": [{
                                "pattern": "target/*.jar",
                                "target": "$ARTIFACTORY_REPO/",
                                "props": "build.name=Projet1DevOps;build.number=${env.BUILD_NUMBER}"
                            }]
                        }"""

                        server.upload spec: uploadSpec, buildInfo: buildInfo
                        server.publishBuildInfo buildInfo
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build et déploiement réussis !"
        }
        failure {
            echo "❌ Une erreur s'est produite. Vérifie les logs."
        }
    }
}
