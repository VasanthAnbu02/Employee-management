pipeline {
    agent any

    environment {
        MYSQL_ROOT_PASSWORD = credentials('employee-db-password')
        MYSQL_DATABASE = 'employee_db'

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

stage('Docker Engine Check') {
    steps {
        bat 'set DOCKER_HOST=npipe:////./pipe/dockerDesktopLinuxEngine&& docker version'
    }
}

       stage('Start MySQL') {
    steps {
        bat 'set DOCKER_HOST=npipe:////./pipe/dockerDesktopLinuxEngine&& docker-compose up -d mysql'
    }
}

       stage('Check MySQL') {
    steps {
        bat 'set DOCKER_HOST=npipe:////./pipe/dockerDesktopLinuxEngine&& docker inspect --format="{{.State.Health.Status}}" employee-mysql'
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