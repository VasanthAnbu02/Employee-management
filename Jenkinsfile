pipeline {
    agent any

    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://127.0.0.1:3306/employee_db'
        SPRING_DATASOURCE_USERNAME = 'root'
        SPRING_DATASOURCE_PASSWORD = credentials('employee-db-password')

        DOCKER_IMAGE = 'vasanthanbu/employee-backend'
    }

    stages {

        stage('Docker Engine Check') {
            steps {
                bat 'docker context show'
                bat 'docker info'
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvnw.cmd clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvnw.cmd package -DskipTests'
            }

            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE%:%BUILD_NUMBER% .'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    bat 'docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%'
                }
            }
        }

        stage('Docker Push') {
            steps {
                bat 'docker push %DOCKER_IMAGE%:%BUILD_NUMBER%'
            }
        }
    }

    post {
        success {
            echo 'Docker image pushed successfully!'
            echo "Image: ${env.DOCKER_IMAGE}:${env.BUILD_NUMBER}"
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}