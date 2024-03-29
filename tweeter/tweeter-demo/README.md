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

### ERROR

```
�� t voting:likes
```

```
template.keySerializer = StringRedisSerializer()
template.valueSerializer = GenericJackson2JsonRedisSerializer()
template.hashKeySerializer = StringRedisSerializer()
template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
```

<br/><br/><br/>

###

| CMD           | FORMAT                        | DESC                                 |
|---------------|-------------------------------|--------------------------------------|
| `INCR`        | `INCR key name`               | Key 로 저장된 value 값을 1 증가시킴            |
| `DECR`        | `DECR key name`               | Key 로 저장된 value 값을 1 감소시킴            |
| `INCRBY`      | `INCRBY key name amount`      | Key 로 저장된 value 값을 amount 만큼 증가시킴    |
| `DECRBY`      | `DECRBY key name amount`      | Key 로 저장된 value 값을 amount 만큼 감소시킴    |
| `INCRBYFLOAT` | `INCRBYFLOAT key name amount` | Key 로 저장된 value 값을 제공 amount 만큼 증가시킴 |



















