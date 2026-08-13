#!/bin/bash

# Настройка
IMAGE_NAME=nbank-tests
TEST_PROFILE=${1:-api} # аргумент запуска
TIMESTAMP=$(date +"%Y%m%d_%H%M")
TEST_OUTPUT_DIR="$(pwd -W)/test-output/$TIMESTAMP"

# Собираем Docker образ
echo ">>> Сборка тестов запущена"
docker build -t $IMAGE_NAME .

mkdir -p "$TEST_OUTPUT_DIR/logs"
mkdir -p "$TEST_OUTPUT_DIR/results"
mkdir -p "$TEST_OUTPUT_DIR/report"

# Запуск Docker контейнера
echo ">>> Тесты запущены"
MSYS_NO_PATHCONV=1 docker run --rm \
  --mount "type=bind,source=${TEST_OUTPUT_DIR}/logs,target=/app/logs" \
  --mount "type=bind,source=${TEST_OUTPUT_DIR}/results,target=/app/target/surefire-reports" \
  --mount "type=bind,source=${TEST_OUTPUT_DIR}/report,target=/app/target/site" \
  -e TEST_PROFILE="$TEST_PROFILE" \
  -e SERVER=http://host.docker.internal:4111 \
  -e UIBASEURL=http://172.26.160.1:3000 \
  "$IMAGE_NAME"

# Вывод итогов
echo ">>> Тесты завершены"
echo "Лог файл: $TEST_OUTPUT_DIR/logs/run.log"
echo "Результаты тестов: $TEST_OUTPUT_DIR/results"
echo "Репорт: $TEST_OUTPUT_DIR/report"