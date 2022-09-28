Google Auth
===========

Install AWS SAM CLI

```bash
brew tap aws/tap
brew install aws-sam-cli
```

Create a native executable using:

```bash
mvn clean package -Pnative
```

Running AWS SAM locally

```bash
# Make sure you have your "sam/env.json" file in place
sam local invoke --template sam/sam.native.yaml --event sam/events/authorize.json
```

```bash
# Make sure you have your "sam/env.json" file in place
sam local invoke --template sam/sam.native.yaml --event sam/events/callback.json
```
