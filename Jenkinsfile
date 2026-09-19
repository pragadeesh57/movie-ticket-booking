pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/pragadeesh57/movie-ticket-booking.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t movie-ticket-booking .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker rm -f movie-ticket-booking-container || true'
                sh 'docker run -d -p 8081:8081 --name movie-ticket-booking-container movie-ticket-booking'
            }
        }
    }
}