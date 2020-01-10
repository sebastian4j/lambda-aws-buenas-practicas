aws cloudformation package --template-file src/main/aws/template.yml --output-template-file target/template.yml --s3-bucket buenas-practicas-dev
aws cloudformation deploy --template-file target/template.yml --stack-name buenas-practicas --capabilities CAPABILITY_IAM --region us-east-1
# aws cloudformation delete-stack --stack-name buenas-practicas