#!/usr/bin/env bash

set -Eeuo pipefail

# Локальный образ, который создаёт run-tests.sh.
IMAGE_NAME="${IMAGE_NAME:-nbank-tests}"
LOCAL_IMAGE="${LOCAL_IMAGE:-${IMAGE_NAME}:latest}"

# Данные Docker Hub. Токен обязательно передаётся через окружение.
DOCKERHUB_USERNAME="${DOCKERHUB_USERNAME:-}"
DOCKERHUB_TOKEN="${DOCKERHUB_TOKEN:-}"
TAG="${TAG:-latest}"

if [[ -z "$DOCKERHUB_USERNAME" ]]; then
  read -rp "Введите имя пользователя Docker Hub: " DOCKERHUB_USERNAME
fi

if [[ -z "$DOCKERHUB_USERNAME" ]]; then
  echo "Ошибка: имя пользователя Docker Hub не может быть пустым." >&2
  exit 1
fi

if [[ -z "$DOCKERHUB_TOKEN" ]]; then
  read -rsp "Введите Docker Hub Access Token: " DOCKERHUB_TOKEN
  echo
fi

if [[ -z "$DOCKERHUB_TOKEN" ]]; then
  echo "Ошибка: Docker Hub Access Token не может быть пустым." >&2
  exit 1
fi

if ! command -v docker >/dev/null 2>&1; then
  echo "Ошибка: команда docker не найдена. Установите и запустите Docker." >&2
  exit 1
fi

if ! docker image inspect "$LOCAL_IMAGE" >/dev/null 2>&1; then
  echo "Ошибка: локальный образ '$LOCAL_IMAGE' не найден." >&2
  echo "Сначала соберите его командой: docker build -t $IMAGE_NAME ." >&2
  exit 1
fi

REMOTE_IMAGE="${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${TAG}"

echo ">>> Авторизация в Docker Hub"
printf '%s' "$DOCKERHUB_TOKEN" | docker login --username "$DOCKERHUB_USERNAME" --password-stdin

echo ">>> Тегирование: $LOCAL_IMAGE -> $REMOTE_IMAGE"
docker tag "$LOCAL_IMAGE" "$REMOTE_IMAGE"

echo ">>> Отправка образа в Docker Hub: $REMOTE_IMAGE"
docker push "$REMOTE_IMAGE"

echo ">>> Образ успешно опубликован. Скачать его можно командой:"
echo "docker pull $REMOTE_IMAGE"
