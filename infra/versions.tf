terraform {
  required_providers {
    ncloud = {
      source = "navercloudplatform/ncloud"
    }
  }

  cloud {
    // organization = ""
    // terraform 명령어 내리기 전 환경변수 설정 필요 ==> export TF_CLOUD_ORGANIZATION=...
    workspaces {
      name = "food-reservation"
    }
  }
}
