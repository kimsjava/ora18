#!/bin/bash

# 변수 설정
CONTAINER_NAME="oracle-xe"
SYS_PASSWORD="oracle123"
ORACLE_USER="oracle"
ORACLE_USER_PWD="oracle123"
IMAGE="gvenzl/oracle-xe:18.4.0-slim"

# 기존 컨테이너 정리
echo "🧹 기존 컨테이너 제거 (있다면)..."
docker rm -f $CONTAINER_NAME 2>/dev/null

# 이미지 실행
echo "🚀 Oracle XE Docker 컨테이너 시작 중..."
docker run -d \
  --name $CONTAINER_NAME \
  -p 1521:1521 \
  -e ORACLE_PASSWORD=$SYS_PASSWORD \
  $IMAGE

# 초기화 대기
echo "⏳ Oracle 기동 대기 중..."
sleep 40

# oracle 사용자 생성
echo "👤 oracle 사용자 생성 중..."
docker exec -i $CONTAINER_NAME bash -c "echo "
CREATE USER $ORACLE_USER IDENTIFIED BY $ORACLE_USER_PWD;
GRANT CONNECT, RESOURCE TO $ORACLE_USER;
" | sqlplus system/$SYS_PASSWORD@localhost/XE"

echo "✅ 설치 완료. 사용자 '$ORACLE_USER' 생성됨 (비번: $ORACLE_USER_PWD)"
