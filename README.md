# Monew MVC

[![CI/CD](https://github.com/lmnop-95/monew-mvc/actions/workflows/deploy.yml/badge.svg)](https://github.com/lmnop-95/monew-mvc/actions/workflows/deploy.yml)

뉴스 API를 통합하여 맞춤형 뉴스를 제공하고, 의견을 나눌 수 있는 소셜 기능을 갖춘 서비스의 API 서버입니다.

## Tech Stack

### Backend

<div>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?logo=springboot&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/QueryDSL-5.1-4285F4" alt="badge">
  <img src="https://img.shields.io/badge/MapStruct-1.6-FF6B35" alt="badge">
</div>

### Database & Storage

<div>
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/MongoDB-47A248?logo=mongodb&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/AWS_S3-569A31?logo=amazons3&logoColor=white" alt="badge">
</div>

### Monitoring & Infrastructure

<div>
  <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/AWS_ECS-FF9900?logo=amazonecs&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?logo=githubactions&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/Spring_Actuator-6DB33F?logo=spring&logoColor=white" alt="badge">
</div>

### Testing

<div>
  <img src="https://img.shields.io/badge/JUnit_5-25A162?logo=junit5&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/Mockito-78A641" alt="badge">
  <img src="https://img.shields.io/badge/Testcontainers-2496ED?logo=docker&logoColor=white" alt="badge">
  <img src="https://img.shields.io/badge/JaCoCo-CC0200" alt="badge">
</div>

## Architecture

```
src/main/java/com/monew/monew_server/
├── config/          Spring 설정 (JPA, MongoDB, QueryDSL, OpenAPI, WebMvc)
├── auth/            인증 유틸리티
├── exception/       커스텀 예외 및 글로벌 핸들러
├── log/             AOP 로깅
└── domain/
    ├── user/            사용자 관리 (회원가입, 로그인, 수정, 삭제)
    ├── article/         뉴스 기사 (수집, 검색, 조회, 백업/복구)
    ├── comment/         댓글 (CRUD, 좋아요)
    ├── interest/        관심사 (CRUD, 구독)
    ├── notification/    알림 (생성, 확인)
    ├── user_activity/   활동 내역 (MongoDB 역정규화 조회)
    └── common/          공통 Base Entity
```

각 도메인 모듈은 `controller / service / repository / entity / dto` 계층을 기본으로 구성되며, 필요에 따라 `mapper`, `storage`, `enums` 등 추가 계층을 포함합니다.

## API Endpoints

| Domain | Method | Endpoint | Description |
|--------|--------|----------|-------------|
| User | `POST` | `/api/users` | 회원가입 |
| User | `POST` | `/api/users/login` | 로그인 |
| User | `PATCH` | `/api/users/{id}` | 닉네임 수정 |
| User | `DELETE` | `/api/users/{id}` | 논리 삭제 |
| User | `DELETE` | `/api/users/{id}/hard` | 물리 삭제 |
| Article | `GET` | `/api/articles` | 기사 목록 (커서 페이지네이션) |
| Article | `GET` | `/api/articles/{id}` | 기사 상세 |
| Article | `GET` | `/api/articles/sources` | 출처 목록 |
| Article | `POST` | `/api/articles/{id}/article-views` | 조회 등록 |
| Article | `DELETE` | `/api/articles/{id}` | 논리 삭제 |
| Article | `DELETE` | `/api/articles/{id}/hard` | 물리 삭제 |
| Article | `GET` | `/api/articles/restore` | 백업 복구 |
| Comment | `POST` | `/api/comments` | 댓글 등록 |
| Comment | `GET` | `/api/comments` | 댓글 목록 (커서 페이지네이션) |
| Comment | `PATCH` | `/api/comments/{id}` | 댓글 수정 |
| Comment | `DELETE` | `/api/comments/{id}` | 논리 삭제 |
| Comment | `DELETE` | `/api/comments/{id}/hard` | 물리 삭제 |
| Comment | `POST` | `/api/comments/{id}/comment-likes` | 좋아요 |
| Comment | `DELETE` | `/api/comments/{id}/comment-likes` | 좋아요 취소 |
| Interest | `GET` | `/api/interests` | 관심사 목록 (커서 페이지네이션) |
| Interest | `POST` | `/api/interests` | 관심사 등록 |
| Interest | `PATCH` | `/api/interests/{id}` | 관심사 수정 |
| Interest | `DELETE` | `/api/interests/{id}` | 관심사 삭제 |
| Interest | `POST` | `/api/interests/{id}/subscriptions` | 구독 |
| Interest | `DELETE` | `/api/interests/{id}/subscriptions` | 구독 취소 |
| Notification | `GET` | `/api/notifications` | 미확인 알림 목록 |
| Notification | `PATCH` | `/api/notifications/{id}` | 알림 확인 |
| Notification | `PATCH` | `/api/notifications` | 전체 확인 |
| Activity | `GET` | `/api/user-activities/{userId}` | 활동 내역 조회 |

## Application Ports

| Server | Management |
|--------|------------|
| 8080 | 8081 |
