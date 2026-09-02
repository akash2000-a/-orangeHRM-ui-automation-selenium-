pipeline {
    agent any

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
                echo 'Compiling the project and preparing test suite...'
                bat 'mvn test-compile'
            }
        }
        stage('Execute UI Automation Tests') {
            steps {
                echo 'Executing TestNG Suite in headless browser...'
                bat 'mvn test'
            }
        }
    }
    post {
        always {
            echo 'Publishing Allure Test Execution Report...'
            allure includeProperties: false, 
                   jdk: '', 
                   results: [[path: 'target/allure-results']]
        }
        failure {
            echo 'Test execution failed.'
        }
    }
}

