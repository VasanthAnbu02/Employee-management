pipeline {
    agent any

    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://localhost:3306/employee_db'
        SPRING_DATASOURCE_USERNAME = 'root'
        SPRING_DATASOURCE_PASSWORD = credentials('employee-db-password')
    }

    stages {

        stage('Environment Check') {
            steps {
                bat 'java -version'
                bat 'git --version'
                bat 'docker --version'
                bat 'docker-compose --version'
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvnw.cmd clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t employee-backend:%BUILD_NUMBER% .'
            }
        }
    }
}