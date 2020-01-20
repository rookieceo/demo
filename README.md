# 샘플 게시판
==================================================

**Springboot, JPA, Nuxt** 등을 활용한 게시판 예제

적용기술
-----------

프론트와 백엔드 분리, 프론트의 경우 SPA 형태로 구현. 

본 샘플예제는 아래와 같은 특징을 가집니다.

* springboot : 2.2.3.RELEASE 
* spring-data-jpa
* H2 Database : In-memory DB
* springfox-swagger2 - API 테스팅
* nuxt - Vue 기반 프레임워크
* vuetify - MaterialDesign를 준수하는 Vue기반 UI 프레임워크 
* axios - Promise based HTTP client 

그 외에는 소스내역을 참고.  


빌드 및 실행
---------------

1. Frontend 빌드 (Nodejs 및 npm 설치 필요)

        $ cd src/main/front-nuxt
        $ npm install
        $ npm run generate

2. Backend 빌드 (Maven 빌드)

        $ mvnw clean install

3. Web Application 구동  
 
        $ mvnw spring-boot:run

4. 브라우저에서 실행 (크롬, 파이어폭스, 오페라 등 에서 구동 - IE 지원안함)
: [http://localhost:8080](http://localhost:8080)    
5. REST API 확인 및 테스트
: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)   
 
