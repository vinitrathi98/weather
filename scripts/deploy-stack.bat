#!/bin/bash

# Please ensure that you have the correct AWS credentials configured.
# Enter the name of the stack and the region name.

if [ $# -ne 2 ]; then
    echo "Enter stack name and the region name. "
    exit 0
else
    STACK_NAME=$1
    REGION=$2
fi

    aws cloudformation deploy \
    --stack-name $STACK_NAME \
	--template-file cloudformation/cf.yaml \
    --capabilities CAPABILITY_NAMED_IAM \
    --region $REGION \
    --no-execute-changeset
