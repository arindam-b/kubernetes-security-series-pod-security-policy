#README

To create a new cluster with PodSecurityPolicy, run the following command:

1. gcloud beta container clusters create my-cluster --enable-pod-security-policy --zone us-central1-a

To update an existing cluster:

gcloud beta container clusters update my-cluster --enable-pod-security-policy


###Disabling PodSecurityPolicy controller

2. gcloud beta container clusters update my-cluster --no-enable-pod-security-policy

### Create a namespace

3. kubectl create ns my-namespace


### The following PodSecurityPolicy, my-psp.yaml, simply prevents the creation of privileged Pods. The policy also affects several other control aspects, such as allowing access to all available volumes:

4. kubectl apply -f psp.yaml

# Authorizing policies

Cluster Admin - has full access, it creates ClusterRole

ClusterRole - has cluster wise full access, it creates Role

Role - has access to specific namespace


Important: Accounts with the cluster-admin role can use role-based access control to create a Role or ClusterRole that grants the desired service accounts access to PodSecurityPolicies. A ClusterRole grants cluster-wide permissions, and a Role grants permissions within a namespace that you define.

the following ClusterRole, clusterrole.yaml, grants access to the my-psp PodSecurityPolicy, as indicated by verb: use

5. kubectl apply -f clusterrole.yml


After creating a ClusterRole, associate it with the desired service accounts by creating a RoleBinding resource.

RoleBinding, rolebinding.yml, binds the ClusterRole, my-clusterrole, to the service accounts in a specific namespace, my-namespace:

The subjects field specifies to which accounts the ClusterRole is bound in the rolebinding.yml

6. kubectl apply -f rolebinding.yml


# Create service account - build-robot in the namespace

7. kubectl apply -f service-account.yml

# Apply the deployment


Below deployment will work and create game-api deployment where pod security works perfectly.

8. kubectl apply -f game-api\k8s\k8s.yaml

But below deployment will not work as it does not ave build-robot service account attached. So it will not be able to schedule pod.

9. kubectl apply -f game-api\k8s\k8s-without-service-account

Reference screen shot 1 & 2

## seccomp  - it did not work

It is secure compute mode.

Well, containers are actually just a process running inside a given machine. It shares the kernel with all the other applications. If all containers had the ability to make any system calls, it would not take long for malicious programs to bypass the container isolation and impact other applications — eavesdrop information, change system level settings, etc.
Your seccomp profiles defines what system calls should be allowed or blocked, and the container runtime will apply then at container start time so the kernel can enforce it. Once applied, you are effectively decreasing your attack surface and limiting the damage in case anything inside your container (i.e. your dependencies, or their dependencies…) start doing something they should not be allowed to.

Reference: https://cloud.google.com/kubernetes-engine/docs/how-to/pod-security-policies
https://kubernetes.io/docs/concepts/policy/pod-security-policy/



