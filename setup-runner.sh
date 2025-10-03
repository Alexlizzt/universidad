#!/usr/bin/env bash

REPO_URL="https://github.com/alexlizzt/universidad"
RUNNER_VERSION="2.308.0"
RUNNER_DIR="$HOME/actions-runner"

sudo apt update
sudo apt install -y curl git docker.io docker-compose openjdk-17-jdk maven
sudo usermod -aG docker $USER
newgrp docker
sudo systemctl enable docker
sudo systemctl start docker

mkdir -p $RUNNER_DIR && cd $RUNNER_DIR

curl -O -L "https://github.com/actions/runner/releases/download/v$RUNNER_VERSION/actions-runner-linux-x64-$RUNNER_VERSION.tar.gz"
tar xzf actions-runner-linux-x64-$RUNNER_VERSION.tar.gz

echo "🛠️ Abre el siguiente enlace para obtener el token:"
echo "$REPO_URL/settings/actions/runners/new"
read -p "🔑 Ingresa el token de registro: " TOKEN

./config.sh --url $REPO_URL --token $TOKEN --unattended --labels multipass
sudo ./svc.sh install
sudo ./svc.sh start
