# Fin-Target Convention

## 브랜치 전략 🌱

| 브랜치 | 설명 |
|---|---|
| `main` | 배포 가능한 안정 버전 (PR로만 머지) |
| `dev` | 개발 통합 브랜치 |
| `feature/*` | 기능 개발 |
| `fix/*` | 버그 수정 |
| `chore/*` | 설정, 환경 등 기타 작업 |

브랜치 이름은 타입/작업내용 형식으로, 소문자와 하이픈(-) 사용
feature/login-kakao-oauth
fix/policy-null-matching
chore/gitignore-update

---

## 커밋 메시지 📝
type: 제목

| type | 설명 |
|---|---|
| `feat` | 새로운 기능 추가 |
| `fix` | 버그 수정 |
| `docs` | 문서 수정 |
| `style` | 코드 포맷팅 (로직 변경 없음) |
| `refactor` | 리팩토링 |
| `test` | 테스트 코드 |
| `chore` | 빌드, 설정 등 기타 작업 |

- 제목은 50자 이내, 한국어 사용
- 제목 끝 마침표 생략

---

## Pull Request 🔁

제목은 커밋 메시지 형식과 동일하게 작성
변경 내용
상세 작업 내용
테스트 방법
고려 사항

- `dev ← feature` 방향으로 PR
- Squash and merge 통일
- 머지 후 브랜치 삭제

---

## 코드 스타일 💡

**Java / Spring Boot**
- 클래스 `PascalCase` / 메서드·변수 `camelCase` / 상수 `UPPER_SNAKE_CASE`
- 패키지는 도메인 단위로 분리 (`controller / service / repository / dto / entity`)
- DTO와 Entity 분리, Entity setter 지양
- 예외 메시지 한국어 통일
- 응답 형식 `ApiResponse<T>` 통일

---

## 작업 순서 ✔️

1. 브랜치 생성
2. 작업 및 커밋
3. dev 브랜치로 PR 생성
4. 리뷰 후 Squash and merge
5. 브랜치 삭제
