pipeline {
    agent any

    tools {
        jdk 'JDK 21'
    }

    stages {

        stage('Compile Stage') {
            steps {
                script {
                    def mvnHome = tool name: 'maven_3_9_9', type: 'maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat "${mvnHome}\\bin\\mvn clean compile"
                    }
                }

            }
        }

        stage('Test Stage') {
            steps {
                script {
                    def mvnHome = tool name: 'maven_3_9_9', type: 'maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat "${mvnHome}\\bin\\mvn test -Dtest=RunnerTest"
                    }
                }
            }

        }

        stage('Cucumber Reports') {
            steps {
                cucumber buildStatus: "UNSTABLE",
                        fileIncludePattern: "**/cucumber.json",
                        jsonReportDirectory: 'target'
            }
        }

    }

}
