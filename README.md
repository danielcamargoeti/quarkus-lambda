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
sam local invoke --template sam/sam.native.yaml --env-vars sam/env.json --event sam/events/authorize.json
```

```bash
sam local invoke --template sam/sam.native.yaml --env-vars sam/env.json --event sam/events/callback.json
```
