pipeline {
	agent any
  parameters {
    string(name: 'STACK_NAME', defaultValue: 'example-stack', description: 'Enter the CloudFormation Stack Name.')
    choice(
      name: 'REGION',
      choices: [
          ' ',
          'us-east-1',
          'us-east-2'
          ],
      description: 'AWS Account Region'
    )
  }

  stages {

    stage('check version') {
      steps {
			bat 'aws --version'
            bat 'aws sts get-caller-identity'
          
        }
    }
	  stage('stack-execution') {
      steps {
         withCredentials([[
              $class: 'AmazonWebServicesCredentialsBinding',
              credentialsId: "${CFN_CREDENTIALS_ID}",
              accessKeyVariable: 'AWS_ACCESS_KEY_ID',
              secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                bat 'scripts/deploy-stack.bat ${STACK_NAME} ${REGION}'
            }
      }
    }
   }

  
}