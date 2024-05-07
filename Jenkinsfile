pipeline{
    agent any
    tools{
        maven 'local_maven'
    }
    stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post {
               success {
                  echo 'Archiving the artifacts'
                  archiveArtifacts artifacts: '**/target/*.war'
               }
            }
        }
        stage('Deploy to tomcat server') {
            steps {
                deploy adapters: [tomcat9(credentialsId: '44b53c82-cc97-4d5b-bb67-233b73561610', path: '', url: 'http://77.37.86.213:9090/')], contextPath: null, war: '**/*.war'
            }

        }
    }
}