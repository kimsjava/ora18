# quest-sample
Spring Boot 기반의 sample 프로젝트입니다.

## 1. 환경설정

- **Java 버전:** 17  
- **Spring Boot:** 3.1.5  
- **Gradle** 기반 빌드 시스템 사용  
- 기본 **.gitignore** 설정을 통해 빌드 산출물, IDE 파일 등을 제외함  

## 2. 디렉터리 구조
    ```
    quest-sample/
    ├── build.gradle
    ├── .gitignore
    ├── README.md
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── example
        │   │           └── (패키지 구조)
        │   └── resources
        │       ├── application.properties (또는 application.yml)
        │       └── templates (Thymeleaf 템플릿 파일)
        └── test
            └── java
                └── (테스트 코드)
    ```
## 3. Oracle XE 18 설정 (Docker)
Oracle XE 18은 Docker 컨테이너로 실행할 수 있습니다.
아래는 Docker 컨테이너로 Oracle XE 18을 실행하는 예시입니다.

    ```bash
    docker run -d --name oracle-xe \
    -p 1521:1521 -p 8080:8080 \
    -e ORACLE_PWD=oracle \
    gvenzl/oracle-xe:18.4.0
    ```

## 4. M Chip (Colima Docker 설정)
Apple Silicon (M Chip) Mac 사용자는 Colima를 이용하여 Docker를 원활하게 사용할 수 있습니다.
Colima 설치 후 별도의 추가 설정 없이 위의 Docker 명령어로 컨테이너를 실행할 수 있습니다.

## 5. DB 설정 (HDB, Oracle XE)
Spring Boot에서 Oracle XE를 사용하려면 application.properties (또는 application.yml)에 데이터베이스 연결 정보를 설정합니다.
    ```
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
    spring.datasource.username=system
    spring.datasource.password=oracle
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
    ```

## 6. Boards 테이블 CRUD 설정
Boards 엔티티를 생성하고 CRUD 기능을 구현합니다.

Repository: Spring Data JPA 인터페이스 사용
Service: 비즈니스 로직 구현
Controller: RESTful API (또는 MVC 컨트롤러) 구현
예시 엔드포인트:

GET /boards – 전체 리스트 조회
POST /boards – 새 board 생성
GET /boards/{id} – 특정 board 상세 조회
PUT /boards/{id} – board 정보 수정
DELETE /boards/{id} – board 삭제

## 7. RESTful MVC 설정 및 Thymeleaf Template
RESTful MVC
Spring MVC 아키텍처를 기반으로 Controller, Service, Repository 계층으로 구성
RESTful API를 통해 클라이언트와 JSON 형식으로 데이터를 주고받습니다.
Thymeleaf Template 사용
템플릿 파일은 src/main/resources/templates 디렉터리에 위치합니다.
예시: boards.html, board_detail.html 등
템플릿을 통해 서버 사이드 렌더링 방식의 뷰를 제공합니다.