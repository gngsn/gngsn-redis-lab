## Create Kubernetes Service

### Installing Minikube

**1. brew**

```
$ brew install minikube
$ brew unlink minikube
$ brew link minikube
```

**2. binary**

```
$ curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-darwin-arm64
$ sudo install minikube-darwin-arm64 /usr/local/bin/minikube
```

<br/>

### Installing the Kubernetes client

**1. brew**

```
$ brew install kubectl
```

<br/>

### Checking to see the cluster is up and kubectl can talk to it

```
❯ kubectl cluster-info
Kubernetes control plane is running at https://127.0.0.1:6443
CoreDNS is running at https://127.0.0.1:6443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy

To further debug and diagnose cluster problems, use 'kubectl cluster-info dump'.
```

<br/>

### kubectl yaml 설정 모를 때

```
$ kubectl explain pods
$ kubectl explain pod.spec
```

<br/>

### Kubernetes 시작

```Bash
# `tweetwe-demo-manual.yaml` 파일 생성
❯ kubectl create -f k8s/tweeter-demo-manual.yaml
pod/tweeter-demo-manual created

❯ kubectl get po tweeter-demo-manual -o yaml
❯ kubectl get po tweeter-demo-manual -o yaml
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: "2023-12-20T15:15:44Z"
  name: tweeter-demo-manual
  namespace: default
  resourceVersion: "383416"
  uid: 7375ff53-8f42-4d53-905f-bbc1cd886c9d
spec:
  containers:
  - image: gngsn/tweeter-demo
    imagePullPolicy: Always
    name: tweeter-demo
    ports:
    - containerPort: 8080
      protocol: TCP
...
```

<br/>

#### Error: STATUS - `ImagePullBackOff`

```
❯ kubectl get pods
NAME                               READY   STATUS             RESTARTS   AGE
nginx-deployment-cbdccf466-jsvkv   1/1     Running            2          36d
nginx-deployment-cbdccf466-kmmkl   1/1     Running            2          36d
tweeter-demo-manual                0/1     ImagePullBackOff   0          2m25s
```

---

#### Postgres 설정

1. postgres-config-map 작성
2. postgres-configmap 생성

```
❯ kubectl apply -f postgres-configmap.yaml
configmap/postgres-config-map created
❯ kubectl get configmap
NAME                  DATA   AGE
kube-root-ca.crt      1      46d
postgres-config-map   3      7s
```

2. PersistentVolume 생성

```
❯ kubectl apply -f postgres-config.yaml
configmap/postgres-config-map unchanged
persistentvolume/postgres-pv-volume created
persistentvolumeclaim/postgres-pv-claim created
❯ kubectl get pvc
NAME                STATUS   VOLUME               CAPACITY   ACCESS MODES   STORAGECLASS   AGE
postgres-pv-claim   Bound    postgres-pv-volume   5Gi        RWX            manual         39s
```

3. Deployment 작성

```
❯ kubectl apply -f postgres-deployment.yaml
deployment.apps/postgres created
❯ kubectl get deployments
NAME               READY   UP-TO-DATE   AVAILABLE   AGE
postgres           0/1     1            0           52s
```

4. Service 작성

```
❯ kubectl apply -f postgres-service.yaml

service/postgres created
❯ kubectl get all
NAME                                   READY   STATUS             RESTARTS   AGE
pod/postgres-7b8bfb4588-lgjtz          1/1     Running            0          10m

NAME                 TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
service/postgres     NodePort    10.100.116.77   <none>        5432:31146/TCP   26s

NAME                               READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/postgres           1/1     1            1           10m

NAME                                         DESIRED   CURRENT   READY   AGE
replicaset.apps/postgres-7b8bfb4588          1         1         1       10m
```

5. Postgresql 접속

```
❯ kubectl exec -it postgres-7b8bfb4588-lgjtz --  psql -h localhost -U postgres --password -p 5432 voting
Password for user postgres: 
psql (10.1)
Type "help" for help.

voting=# 
```

[Ref](https://www.airplane.dev/blog/deploy-postgres-on-kubernetes)

