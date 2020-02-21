pipeline {
    agent any
    tools {
            maven 'MVN_3.6.3'
            jdk 'JDK_8.221'
        }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('deploy') {
            steps {
                sh 'oc --version'
            }

        }
    }
}