# 디저트타임 (Dessert Time) 🍰

## 소개
디저트타임은 디저트 리뷰 공유 플랫폼입니다. 사용자들이 다양한 디저트에 대한 솔직한 리뷰를 작성하고 공유할 수 있습니다.

## 주요 기능 
- 🔐 소셜 로그인 (카카오, 네이버, 구글)
- 📝 디저트 리뷰 작성 및 공유
- 🏷️ 카테고리별 디저트 검색
- ❤️ 좋아요 및 북마크 기능
- 👤 개인화된 프로필 관리
- 🌟 리뷰 평가 시스템
- 💰 리워드 시스템 (밀)

## 기술 스택
- **언어**: Kotlin
- **아키텍처**: MVVM, Clean Architecture
- **UI**: Jetpack Compose
- **DI**: Hilt
- **비동기 처리**: Coroutines, Flow
- **이미지 로딩**: Coil
- **로깅**: Timber

## 프로젝트 구조
```
desserttime/
├── app/                # 앱 메인 모듈
├── core/              # 코어 유틸리티
├── data/              # 데이터 계층
├── domain/            # 도메인 계층
├── design/            # UI 디자인 시스템
└── feature/           # 기능별 모듈
    ├── auth/          # 인증
    ├── home/          # 홈
    ├── category/      # 카테고리
    ├── review/        # 리뷰
    ├── like/          # 좋아요
    └── mypage/        # 마이페이지
```
## 설치 방법
1. 프로젝트를 클론합니다
git clone https://github.com/yourusername/desserttime.git

2. `local.properties` 파일에 필요한 API 키를 추가합니다
KAKAO_API_KEY=your_kakao_api_key

3. Android Studio에서 프로젝트를 열고 빌드합니다

## 기여 방법
1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'Add some AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

## 라이선스
이 프로젝트는 MIT 라이선스 하에 있습니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 연락처
- 이메일: desserttime@example.com
- 웹사이트: https://desserttime.com

## 스크린샷
[여기에 앱 스크린샷 추가]