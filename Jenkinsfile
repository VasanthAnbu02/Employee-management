pipeline {
    agent any

    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://127.0.0.1:3306/employee_db'
        SPRING_DATASOURCE_USERNAME = 'root'
        SPRING_DATASOURCE_PASSWORD = credentials('employee-db-password')
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
                bat 'docker build -t employee-backend:%BUILD_NUMBER% .'
            }
        }
    }
}