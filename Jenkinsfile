pipeline {
    environment {
   	 PROJECT = "iit-cc-cw"
 	   APP_NAME = "coinbase"
     BRANCH_NAME = "main"
   	 IMAGE_TAG = "mewan/${PROJECT}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
                }
    agent any 
    options {
        skipStagesAfterUnstable()
            }
    stages {
	      stage('Building & Deploy Image') {
		         
              steps {
                  sh 'mkdir images'
                  sh 'cp Dockerfile images/'
                  sh 'cp manage.py images/'
                  sh 'cp requirements.txt images/'
                  sh 'cp -avr app/ images/'
                  sh 'cp .gitignore images/'
                  sh 'docker build -t ${APP_NAME} images/.'
                  sh 'docker tag ${APP_NAME} ${IMAGE_TAG}'
                  sh 'docker login -u pavaradatum -p ftd@010203'
                  sh 'docker push ${IMAGE_TAG}'
                  sh 'docker image rm ${IMAGE_TAG}'
                  sh 'docker image rm ${APP_NAME}'
                  sh 'rm -rf images/'			
                    }
                                         }
        stage('Deploy cluster') {
              steps {
                  sh '''cat <<EOF > deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: coinbase-deploy
spec:
  selector:
    matchLabels:
      app: coinbase-stage
      department: coinbase-app
  replicas: 2
  template:
    metadata:
      labels:
        app: coinbase-stage
        department: coinbase-app
    spec:
      containers:
      - name: hello
        image: ${IMAGE_TAG}
        env:
        - name: "PORT"
          value: "5000"
EOF''' 
                  sh '''cat <<EOF > service.yaml 
apiVersion: v1
kind: Service
metadata:
  name: coinbase-service
spec:
  type: LoadBalancer
  selector:
    app: coinbase-stage
    department: coinbase-app
  ports:
  - protocol: TCP
    port: 5000
    targetPort: 5000
EOF'''         
                  sh 'gcloud auth activate-service-account --key-file /var/lib/jenkins/.certificate/thematic-keel-338120-e7decd1ed7a4.json'
                  sh 'gcloud config set compute/zone asia-southeast1-b'
                  sh 'gcloud config set project My First Project'
                  sh 'gcloud container clusters get-credentials space-cluster --zone asia-southeast1-b --project My First Project'
                  sh 'export KUBECONFIG=$(pwd)/config'
                  sh 'kubectl get nodes'
                  // sh 'kubectl create ns production'
                  sh 'kubectl  apply -f deployment.yaml'
                  // sh 'kubectl  apply -f service.yaml'
                    }
                                }

           }
           post {
              always {
                 mail to: 'kavindasg@gmail.com',
                          subject: "Success Pipeline: ${currentBuild.fullDisplayName}",
                          body: "${PROJECT}/${APP_NAME}/${env.BRANCH_NAME}/${env.BUILD_NUMBER}"
                     }
              failure {
                    mail to: 'kavindasg@gmail.com',
                          subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                          body: "Something is wrong with ${env.BUILD_URL}"
                      }
                }
        }