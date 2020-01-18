#!/bin/sh

# CREATE CLUSTER (TO BE DONE BEFORE)
# eksctl create cluster --name ufo-quarkus
# you can test the nodes using: kubectl get nodes

# PUSH ALL PODS AND SERVICES IN KUB CLUSTER
kubectl apply -f kubernetes/kubernetes.yml

