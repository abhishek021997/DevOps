pipeline {
    agent { label "dev" }

    stages {
        stage('Code') {
            steps {
                echo 'Git file clone'
                sh "pwd > /tmp/testout"
                git branch: 'main', credentialsId: 'git_auth', url: 'https://github.com/abhishek021997/DevOps.git'
               
            }
        }
        
        stage('Build') {
            steps {
              echo "docker build start"
              sh "docker build -t nginx:v1 ."
              echo "docker build successfully"
            }
        }
        
        stage('commit image') {
            steps {
               echo 'commit image'
               sh "docker tag nginx:v1 docker.io/abhishek1997sh/jenkin_repo:v2"
            }
        }
        
        stage('Docker Push') {
            steps {
                echo "Docker pai puch krna hai"
                withCredentials([usernamePassword(credentialsId: 'jenkin_docker_login', passwordVariable: 'docker_pass', usernameVariable: 'dockerusername')]){
                    sh "docker login -u ${env.dockerusername} -p ${env.docker_pass}" 
                    sh "docker push docker.io/abhishek1997sh/jenkin_repo:v2"
                    echo "docker push ho gaya hai"
                }
                
            }
        }
    }
}
