

# 📦 BuildiFy - WMS 시스템 (1차 프로젝트)
<img width="1156" alt="image" src="https://github.com/user-attachments/assets/ca669e96-f1d9-44d1-b46c-b0bd78190c1f" />




## 📌 프로젝트 개요
**BuildiFy**는 Java CLI 환경에서 구현된 창고 관리 시스템(Warehouse Management System, WMS)입니다.  
조립형 PC 부품의 **입출고 및 재고 관리**를 위한 시스템으로, MySQL 데이터베이스를 활용하여 **제품의 입고, 출고, 재고 현황을 효율적으로 관리**합니다.

---

## 🖥 주요 기술 스택

- **Language:** ![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white)
- **Database:** ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
- **Framework/Library:** ![JDBC](https://img.shields.io/badge/JDBC-336791?style=flat&logo=apachemaven&logoColor=white)
- **Version Control:** ![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=git&logoColor=white)

## 🧩 2차 프로젝트 기술 스택 (예정)

![Linux](https://img.shields.io/badge/Linux-000000?style=flat&logo=linux&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=spring&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-FF6600?style=flat&logo=datagrip&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-339933?style=flat&logo=nodedotjs&logoColor=white)
![Ajax](https://img.shields.io/badge/AJAX-0B98DC?style=flat&logo=javascript&logoColor=white)



---

## 📁 프로젝트 구조

```
BuildiFy
├── common
├── config
├── controller
├── domain
│   ├── inbound
│   ├── outbound
│   ├── inventory
│   └── accountManagement
│       ├── User
│       └── Admin
├── dto
├── exception
├── MySql
└── resources
```

### 📂 디렉토리 설명
| 폴더 | 설명 |
|------|------|
| `common` | 공통 유틸 및 상수 정의 |
| `config` | 환경 설정 파일 |
| `controller` | 사용자 요청 처리 |
| `domain` | 도메인별 비즈니스 로직 관리 (입고, 출고, 재고, 계정) |
| `dto` | 데이터 전달 객체 |
| `exception` | 예외 처리 모듈 |
| `MySql` | DB 연결 및 쿼리 처리 |
| `resources` | 리소스 파일 |

---

## 🚀 프로젝트 실행 가이드

### ✅ 실행 방법
1. `Main` 클래스로 실행  
2. **MySQL Connector JAR**와 **Lombok** 라이브러리 사전 세팅 필요

### 🔐 계정 정보

#### 관리자 (ADMIN)
- ID: `admin01`  
- 비밀번호: `admin123`

#### 사용자 (USER)
- ID: `SalesTeam1`  
- 비밀번호: `!dlehdgnl3546`

### 🛠️ 데이터베이스 세팅
`src/MySql/wmsdb.sql` 파일을 터미널에서 실행:

```bash
mysql -u [사용자명] -p [비밀번호] < src/MySql/wmsdb.sql
```

---

## 🧩 주요 기능

### 사용자 (입점 회사)
- 회원가입 신청 (관리자 승인 필요)
- 로그인
- 입고 요청 등록
- 출고 요청 등록
- 자사 재고 조회
- 회원 정보 조회 및 수정

### 관리자
- 사용자 가입 승인 및 관리
- 입고/출고 요청 처리 및 관리
- 전체 재고 관리
- 관리자 정보 조회 및 수정

---

## 🗂 제품 카테고리 (1차 프로젝트 기준)
- CPU  
- 메모리  
- RAM  
- 그래픽카드  
- 모니터  
- 마우스  
- 키보드  

> 📌 **2차 프로젝트에서 웹 환경으로 확장 및 카테고리 추가 예정**

---

## ⚒ 개발 예정 (2차 프로젝트)
- 웹 기반 UI 및 기능 확장  
- 제품 카테고리 세분화  
- 사용자 경험 향상을 위한 UI 개선  

---

## 👥 팀원
- 김선민  
- 김성준  
- 이동휘  
- 신민혁  

---

# 📘 개발 컨벤션

## 🔁 Git 규칙

### Git 사용 순서
```bash
git add .
git commit -m "#이슈번호 커밋타입: 커밋메시지"
git pull origin main
git push origin 브랜치명
```
→ 이후 **Pull Request 생성**, 팀원 모두 확인

### Commit 메시지 규칙

- 제목과 본문 사이 한 줄 공백  
- 제목은 50자 이내, 대문자로 시작, 마침표 ❌  
- 명령문 형태, 과거형 ❌  
- 본문 각 줄은 72자 이내  

#### Commit Type 예시

| 타입 | 설명 |
|------|------|
| `feat` | 새로운 기능 |
| `fix` | 버그 수정 |
| `build` | 빌드 관련 수정 |
| `chore` | 자잘한 수정 |
| `ci` | CI 설정 변경 |
| `docs` | 문서 수정 |
| `style` | 코드 스타일 변경 |
| `refactor` | 리팩토링 |
| `test` | 테스트 코드 수정 |
| `perf` | 성능 개선 |

#### 예시
```bash
git commit -m "feat: 식당관리화면에서 상단 탭 클릭 시 화면 전환(spa)

식당관리자가 볼 수 있는 식당관리 화면에 탭을 추가하여 해당 탭 클릭 시 해당 화면으로 전환됨

resolves: #7
```

---

## 💡 네이밍 컨벤션

| 항목 | 규칙 |
|------|------|
| 변수, 함수 | `camelCase` |
| 클래스, 생성자 | `PascalCase` |
| boolean | `is`, `has`로 시작 |
| 함수명 | 동사+명사 (e.g. `getUserInfo`) |
| 최대 이름 길이 | 20자 이내 |
| tab depth | 최대 4단계 |
| 주석 | 한 줄: `//`, 여러 줄: `/** */` |
| 함수 인자 | 3개까지 권장 |
| 비동기 함수 | `async/await` 사용 |

---

## 🗃 DB 네이밍 규칙

### 스키마 이름
- 영어 소문자, 최대 8자 이내

### 테이블 이름
- 단수형, 소문자 사용  
- 형식: `대분류_설명`, 최대 3단어 (총 26자 이내)

### 컬럼 이름
- `snake_case` 사용  
- `의미_접미사` 형태  
- 예: `product_id`, `created_at`

---

## 🧭 개선할 점 & 계획
- 웹 기술 학습 강화 (2차 프로젝트)  
- 기능 및 제품 카테고리 다양화  
- Notion 등 문서 정리 체계화

---

# 🔍 프로젝트 회고

## 👨‍💻 담당 역할
- 재고 관리 (조회, 수정, 삭제)
- 출고 조회 및 삭제 기능 구현

---

## ✨ 느낀 점

1. **사전 조사 및 자료 준비의 중요성**  
   - WMS 시스템에 대한 타당성 조사를 통해 프로젝트 이해도 향상.  
   - 프로젝트 진행 전 사전 조사와 근거 자료 준비는 필수임을 느낌.

2. **ERD 설계의 어려움**  
   - 엔티티 도출, 관계 설정 등 설계 과정이 생각보다 복잡했음.  
   - 다음에는 주도적으로 참여하고 싶음.

3. **Git 실전 경험**  
   - pull request와 merge 과정을 통해 git의 목적을 명확히 이해.  
   - git에 대한 두려움 해소 및 실전 활용 능력 향상.

4. **MVC 구조 확실히 이해**  
   - Controller → Service → Repository → DB 흐름을 명확히 이해하고 구현.  
   - 의존성 주입 및 Enum 클래스, ValidCheck 클래스 활용으로 코드 개선.

5. **저장 프로시저 및 공통 서버 경험**  
   - 프로시저가 많아지며 관리 어려움 발생 → 공통 서버 도입으로 해결.  
   - 트리거도 직접 구현해보며 트러블슈팅 경험.

---

## ⚠️ 양방향 트리거 무한루프 경험

- 창고와 재고 테이블 간 양방향 트리거 구현 → 무한루프 발생.  
- 순환 참조로 인한 오류 발생 → 재설계로 해결.  
- **교훈:** 양방향 트리거는 위험하므로 지양해야 함.

---

## 📚 배운 점

1. **산출물 관리의 중요성**  
   - 클래스 다이어그램, 기능명세서, 기획서 등 다양한 문서 필요.  
   - 미리 준비하지 않으면 나중에 큰 어려움 발생.

2. **협업과 소통의 중요성**  
   - 소통 부재로 일정 지연 경험 → 소통이 핵심임을 체감.  
   - 팀 프로젝트에서는 대화와 협업이 필수.

---

## 🔧 개선할 점 및 계획

1. **웹 기술 학습 및 활용**  
   - 2차 프로젝트는 웹 기반 → 수업 열심히 따라가서 재미있게 작업할 예정.

2. **기능 및 카테고리 다양화**  
   - 창고 개수 및 카테고리 확장, 사이트 느낌 구현 목표.

3. **문서 및 노션 관리 강화**  
   - 노션 적극 활용, 보기 좋게 문서 정리 계획.




