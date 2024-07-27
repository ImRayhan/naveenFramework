pipeline {
    agent any
    
    tools {
        maven 'maven' // Ensure 'maven' is configured in Jenkins Global Tool Configuration
    }

    environment {
        // Ensure the right directory paths for the test results
        ALLURE_RESULTS = 'allure-results'
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    git url: 'https://github.com/ImRayhan/naveenFramework.git', branch: 'main'
                }
            }
        }
        
        stage('Build') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
                }
                failure {
                    echo "Build failed."
                }
            }
        }
        
        stage('Deploy to QA') {
            steps {
                echo "deploy to qa"
            }
        }
        
        stage('Regression Automation Tests') {
            s
