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

주의할점 : 타임리프 설치 전엔 html 파일을 resources/static 에서 파일을 찾았으나 설치 이후에는 resources/templates 에서 파일을 검색하는 구조로 변경
<br>
관련 문서 : https://www.baeldung.com/spring-thymeleaf-template-directory

[0423] <br>
게시판[Board]
CRUD구현 완료 
D(Delete) 진행시 "정말 삭제 하시겠습니까?" 팝업 노출로<br>
사후 검증 로직까지 추가하였음

[0424]<br>
Board <-> Post 연관관계 매핑작업 완료(게시판 삭제시 연결된 게시글 삭제) <br>
회원가입, 로그인 로직구현 <br>
관리자 권한 유저만 게시판에 대한 C,U,D 실행하도록 개발

[0425]<br>
Post CRUD 구현완료
Comment CRUD 구현완료

[0426]<br>
게시글 작성자만 U,D 가능하게 변경 <br>
댓글 작성자만 U,D 가능하게 변경 <br>
Board CRU 영역 css적용
Post CRU 영역 css적용

[0429]<br>
댓글 업데이트창 css적용 - 기능 적용되는곳에 대한 전체 css는 적용

[0430]<br>
SecurityConfig 수정<br>
로그인한 사용자만 다른 페이지 등 접속가능하게 변경