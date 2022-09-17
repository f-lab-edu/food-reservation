# CI 환경에서의 test 수행에 필요한 컨테이너를 실행시키는 스크립트입니다.
# github workflow 가 사용합니다.
# 로컬 컴퓨터에서 실행시킬 용도로 생성된 스크립트는 아닙니다.
docker compose -f docker-compose-test.yml up -d
