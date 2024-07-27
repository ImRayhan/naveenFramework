pipeline {
    agent any
    
    tools {
        git 'Default' // Use the Git installation named 'Default'
        maven 'maven' // Ensure 'maven' is also configured in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    git branch: 'main', url: 'https://github.com/jglick/simple-maven-project-with-tests.git'
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
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage("Deploy to QA") {
            steps {
                echo("deploy to qa")
            }
        }
        
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    script {
                        git branch: 'main', url: 'https://github.com/ImRayhan/naveenFramework.git'
                    }
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
                }
            }
        }
        
        stage('Publish Allure Reports') {
            steps {
                script {
                    // Ensure the allure results directory exists
                    sh 'mkdir -p allure-results'
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }
        
        stage('Publish Extent Report') {
            steps {
                script {
                    def reportDir = "${env.WORKSPACE}/reports"
                    if (!fileExists(reportDir)) {
                        sh "mkdir -p ${reportDir}"
                    }
                }
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false, 
                             keepAll: true, 
                             reportDir: 'reports', 
                             reportFiles: 'TestExecutionReport.html', 
                             reportName: 'HTML Regression Extent Report', 
                             reportTitles: ''])
            }
        }
        
        stage("Deploy to Stage") {
            steps {
                echo("deploy to Stage")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    script {
                        git branch: 'main', url: 'https://github.com/ImRayhan/naveenFramework.git'
                    }
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml"
                }
            }
        }
        
        stage('Publish Sanity Extent Report') {
            steps {
                script {
                    def reportDir = "${env.WORKSPACE}/reports"
                    if (!fileExists(reportDir)) {
                        sh "mkdir -p ${reportDir}"
                    }
                }
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false, 
                             keepAll: true, 
                             reportDir: 'reports', 
                             reportFiles: 'TestExecutionReport.html', 
                             reportName: 'HTML Sanity Extent Report', 
                             reportTitles: ''])
            }
        }
        
        stage("Deploy to PROD") {
            steps {
                echo("deploy to PROD")
            }
        }
    }
}
