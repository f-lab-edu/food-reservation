# app 테스트를 수행하기 위한 환경을 세팅하는 스크립트 입니다.
# app 서비스 제외한 나머지 서비스가 실행됩니다.
docker compose -f docker-compose-test.yml --project-name food-reservation-test up

# 스크립트를 수행하여 환경을 세팅한 후, 앱의 테스트를 실행해야 합니다.
# 테스트 클래스에 ActiveProfile("test") 를 적어주어, test 프로파일을 이용하여 실행될 수 있도록 해야 합니다.
