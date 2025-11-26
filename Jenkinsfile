pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'jayeshpandit'
        ROLLNO = 'imt2023111'
        IMAGE_NAME = "${DOCKERHUB_USER}/${ROLLNO}-todo-cli"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/generator45/Todo-Cli-IMT2023111.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java project...'
                // Compile code (no tests)
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn -B test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging jar...'
                sh 'mvn -B package'
            }
        }

        stage('Build Docker Image') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                script {
                    echo "Building Docker image ${IMAGE_NAME}:latest"
                    sh "docker build -t ${IMAGE_NAME}:latest ."
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
                    sh "docker push ${IMAGE_NAME}:latest"
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
            sh 'docker images'
        }
        failure {
            echo 'Pipeline failed. Check the logs.'
        }
    }
}
