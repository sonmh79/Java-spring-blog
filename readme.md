# Java, Spring, JPA를 이용해 게시판 만들기

### 기능

- 회원 기능
  - 로그인
    - 세션 유지
    - 예외 처리 (일치하는 아이디 없음, 패스워드 미일치)
  - 회원가입
    - 예외 처리 (이미 존재하는 아이디, 이름)


- 게시글 기능
  - 작성(write)
    - 예외 처리 (로그인 필요)
  - 조회(articleList)
  - 수정(update)
  - 삭제(delete)
    - 영구 삭제(deleteForever)
  - 휴지통(deletedArticleList)

### 엔티티

- Member(id,name,loginId,password)
- Article(id,title,description,Timestamps(createdDate, updatedDate, deletedDate),member)

### 패키지 구조

- inhafood.inhamall
  - controller
  - domain
  - repository
  - service
  - web
  - exception
