pipeline {
    agent any

    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://localhost:3306/employee_db'
        SPRING_DATASOURCE_USERNAME = 'root'
        SPRING_DATASOURCE_PASSWORD = credentials('employee-db-password')
    }

    stages {

        stage('Build and Test') {
            steps {
                bat 'mvnw.cmd clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvnw.cmd package -DskipTests'
            }
        }
    }
}