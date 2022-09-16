# dev 환경을 위해 생성된 모든 서비스 컨테이너, 네트워크 등을 삭제합니다.
docker compose -f docker-compose-dev.yml --project-name food-reservation-dev --profile app down
