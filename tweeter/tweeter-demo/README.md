
[refer] (https://benstitou-anas.medium.com/deploy-java-spring-application-with-mysql-db-on-kubernetes-1e456271c6a1)

```shell
❯ docker build -t gngsn/tweeter-demo:0.1 .
❯ docker push gngsn/tweeter-demo:0.1
```

`ConfigMap`: used by MySql container and the app container to share common configurations
`Secret`: to save sensitive data like passwords for databases …
`Deployment`: to checkout the MySql image and run it as a Pod/Container
`PersistentVolumeClaim`: to manage storage
`Service`: to expose the MySql container inside Kubernetes

### Kubernetes

```shell
kubectl apply -f k8s/postgres-configmap.yaml
```

