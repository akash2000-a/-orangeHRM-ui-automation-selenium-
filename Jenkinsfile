pipeline {
    agent {
        dockerfile true
    }

    environment {
        HEADLESS = 'true'
    }

    stages {
        stage('Checkout source code') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        stage('Compile and prepare test suite') {
            steps {
                echo 'Compiling the project and preparing test suite inside Docker container...'
                sh 'mvn clean test-compile'
            }
        }
        stage('Execute UI Automation Tests') {
            steps {
                echo 'Executing TestNG Suite in headless browser inside Docker container...'
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            echo 'Publishing Allure Test Execution Report...'
            allure commandline: 'Allure',
                   includeProperties: false, 
                   jdk: '', 
                   results: [[path: 'target/allure-results']]
        }
        failure {
            echo 'Test execution failed.'
        }
    }
}



