[0422]
1차 CRUD프로젝트 개설

로컬 DB연결

spring.datasource.url=jdbc:mysql://localhost:**/first_web<br>
spring.datasource.username= *****<br>
spring.datasource.password=****<br>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver<br>

메인 페이지 접속시 Hello World! 표시되게 작성<br>
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
통해 타임리프 설치완료 <br>

주의할점 : 타임리프 설치 전엔 html 파일을 resources 에서 파일을 찾았으나 설치 이후에는 templates 에서 파일을 검색하는 구조로 변경
<br>
관련 문서 : https://www.baeldung.com/spring-thymeleaf-template-directory