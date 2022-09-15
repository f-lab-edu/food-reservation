# app service 제외한 나머지 서비스를 실행하기 위한 스크립트 입니다.
# 즉, 로컬개발을 위해 필요한 service 를 미리 띄워놓기 위한 스크립트 입니다.
docker compose -f docker-compose-dev.yml --project-name food-reservation-dev up

# app 은 intellij 등 IDE 이용하여 직접 실행하면 됩니다.
# 단, IDE 에서 다음과 같은 실행 설정이 필요합니다. (intellij 의 "Edit configurations")
# active profile : 정확히 dev 로 설정해야 합니다.
# JEP : 환경변수로 알맞은 값을 설정해 주어야 합니다.
