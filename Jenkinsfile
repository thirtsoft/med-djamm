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
                    echo "Archiving the Artifacts"
                    archiveArtifacts artifacts '**/target/*.war'
                }
            }
        }
        stage('Deploy to tomcat server') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'c19fd0c9-3c1f-49fa-b174-929c0222ea8b', path: '', url: 'http://77.37.86.213:8080/')], contextPath: null, war: '**/*.war'
            }

        }
    }
}