# 로컬 컴퓨터에서 dev 프로퍼티 적용한 모든 service 를 container 환경에서 실행하기 위한 스크립트입니다.
# 스크립트 실행 시마다 app 빌드 수행합니다. (코드 변경 사항을 적용하기 위하여)
# 스크립트 실행 전 JEP 환경변수 설정이 필요합니다. => export JEP=(비밀번호)

if [ -n "${JEP}" ]; then
  ./gradlew bootJar
  docker compose -f docker-compose-dev.yml --profile app --project-name food-reservation-dev up --build
else
  echo "스크립트 실행 전 JEP 환경변수 값을 설정해 주세요!"
fi
