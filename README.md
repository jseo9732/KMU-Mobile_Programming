# 2022년 2학기 모바일프로그래밍 개인 과제
로그인, 회원가입, 상품 정보 3가지 페이지 구현

## 과제 내용
1. 첫번째 화면 (Relative Layout 혹은 Fragment 사용) - 5점
- 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button)
- 첫번째 화면 초기화시에 기존에 저장된 개인정보 읽어 오기
- ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
- ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동
- 회원가입 없이도 메인 버튼(상품 출력 페이지)을 클릭하면 세번째 화면으로 이동 가능 

2. 두번째 화면 (Linear Layout 혹은 Fragment 사용) - 5점
- 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력
- ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크)
- 이름/전화번호/주소(EditView)
- 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept)
- 회원정보를 저장하고 첫번째 페이지로 이동 
  회원정보 저장은 전역변수, 프레퍼런스(Preference), 파일 중에 하나를 선택하여 활용  
  
3. 세번째 화면 (Constraint Layout, Table Layout, Grid Layout, Frame Layout 중 하나 사용) - 5점
- 상품명, 상품이미지 리스트를 보여주는 화면 (5개이상 이미지를 기본으로 출력)
  (선택) 화면 아래 부분에서 상품명, 상품이미지를 등록 및 삭제하는 버튼 추가
- 화면 아래 부분에 회원정보 버튼은 회원인 경우는 가입한 회원 정보를 보여주고
  회원이 아닌 경우는 회원정보 버튼을 클릭하면 회원가입 여부를 물어보고
  원하면 회원가입 페이지인 두번째 화면으로 이동 (유저관리 - 5점)
- View을 상속한 여러가지 위젯을 사용하여 화면을 구성(기능에 맞는 위젯 선택하여 구성)
  View Group을 상속한 위젯 ListView, GridView, AdapterView, ToolBar 등
  Text View을 상속한 CheckBox, Switch, ToggleButton, RadioButton 등
  ImageView, ImageButton 등

## 기능 소개
### 1. 첫번째 화면
  #### 1-1. 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button)
  <img src="/images/Screenshot_login.png" title="person_info" alt="person_info" width="250px"></img> <br/>
  #### 1-2. 첫번째 화면 초기화시에 기존에 저장된 개인정보 읽어 오기
  <img src="/images/Screenshot_20221101_212049.png" title="person_info" alt="person_info" width="250px"></img> <br/>
  마지막으로 로그인한 아이디를 자동으로 입력<br/>  
  <img src="/images/2022-11-01_9.51.48.png" title="person_info" alt="person_info"></img> <br/>
  프레퍼런스를 통해 login_info.xml 파일에서 최근 로그인한 아이디를 가져와 입력해준다.

  #### 1-3. ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
  <img src="/images/Screenshot_id_fault.png" title="person_info" alt="person_info" width="250px"></img>
  <img src="/images/Screenshot_pw_fault.png" title="person_info" alt="person_info" width="250px"></img><br/>
  (왼)아이디 오류 시 경고 메세지 (오)비밀번호 오류 시 경고 메세지<br/>
  <img src="/images/2022-11-01_9.54.18.png" title="person_info" alt="person_info"></img><br/>
  1. 아이디 입력창에 입력한 아이디로 person_info.xml 파일에서 같은 아이디가 없거나 다르면 경고창을 표시한다.
  2. 찾는 아이디있다면 긴 문자열 형태로 가져와 SEP을 기준으로 슬라이스 하여 배열에 저장한다.
  3. 배열에 저장한 첫번째 값과 입력한 아이디를 비교하여 동일하면 비밀번호를 확인한다.
  4. 배열에 저장한 두번째 값과 비밀번호 입력창에 입력한 비밀번호를 비교하여 다르면 경고창을 표시한다.
  5. 같으면 로그인 처리 후 인텐트를 통해 세번째 화면으로 넘어간다.
  <img src="/images/2022-11-01_10.03.42.png" title="person_info" alt="person_info"></img><br/>
  1. 회원가입 버튼을 누르면 인텐트를 통해 회원가입 페이지로 넘어간다.
  2. 메인 버튼을 누르면 login_info.xml 파일에 로그인 상태를 false로 저장하고 로그인한 아이디 정보를 삭제한 뒤 세번째 페이지로 이동한다.

### 2. 두번째 화면
<img src="/images/Screenshot_signup.png" title="person_info" alt="person_info" width="250px"></img>
   #### 2-1. 아이디 중복 검사
   <img src="/images/Screenshot_id_check.png" title="person_info" alt="person_info" width="250px"></img>
   <img src="/images/Screenshot_id_dup.png" title="person_info" alt="person_info" width="250px"></img><br/>
   사용 중인 아이디와 사용 가능한 아이디 중복 확인 시 경고 표시
   <img src="/images/2022-11-01_10.06.50.png" title="person_info" alt="person_info" width="250px"></img><br/>
   1. 아이디에 입력한 값이 없다면 아이디를 입력해달라는 경고창을 표시하고 isIdDup 변수를 false로 유지한다.
   2. person_info.xml 파일에서 회원가입할 아이디와 동일한 프레퍼런스가 있다면 사용중인 아이디라는 경고창을 토스트를 통해 표시하고 isIdDup 변수를 false로 유지한다.
   3. person_info.xml 파일에 회원가입할 아이디와 동일한 프레퍼런스가 없다면 사용가능한 아이디라는 경고창을 표시하고 isIdDup 변수를 true로 변경하여 회원가입이 가능하도록한다.

   #### 2-2. 비밀번호 유효성 검사 (4~8글자 제한, 숫자와 특수문자 필수 포함)
   <img src="/images/Screenshot_pw_length.png" title="person_info" alt="person_info" width="250px"></img>
   <img src="/images/Screenshot_pw_vaild.png" title="person_info" alt="person_info" width="250px"></img><br/>
   4~8글자 제한과 숫자와 특수문자 필수 포함이 아니면 경고 표시
   
   <img src="/images/2022-11-01_10.11.23.png" title="person_info" alt="person_info"></img>
   정규표현식을 사용하여 숫자와 특수문자가 포함되어있는지 확인 후 Boolean값을 반환한다.
   글자 수는 회원가입 버튼 클릭시 확인한다.
   
   #### 2-3. 개인정보 수집 동의
   <img src="/images/Screenshot_nop.png" title="person_info" alt="person_info" width="250px"></img><br/>
   개인정보 수집 비동의 시 경고창 표시
   <img src="/images/2022-11-01_10.14.53.png" title="person_info" alt="person_info"></img><br/>
   <img src="/images/2022-11-01-10.18.32.png" title="person_info" alt="person_info"></img><br/>
   1. 회원가입 완료 버튼 클릭 시 아이디 중복 여부를 체크한다.
   2. 비밀번호 글자 수가 4~8글자인지 확인한다.
   3. 비밀번호 유효성 검사(숫자, 특수문자 필수 포함) 여부를 확인한다.
   4. 개인 정보 수집 동의 수락을 체크했는지 확인한다.
   5. addMember()함수를 이용하여 person_info.xml에 긴 문자열 형태로 저장한다.
   
   
### 3. 세번째 화면
   #### 3-1. 상품 이미지 5개 이상 표시
   <img src="/images/Screenshot_product.png" title="person_info" alt="person_info" width="250px"></img><br/>
   ListView를 이용하여 5개의 상품 표시

   #### 3-2. 회원인 경우는 가입한 회원 정보 표시
   <img src="/images/Screenshot_userInfo.png" title="person_info" alt="person_info" width="250px"></img><br/>

   #### 3-3. 비회원인 경우 회원가입 여부 묻기
   <img src="/images/Screenshot_onLogin.png" title="person_info" alt="person_info" width="250px"></img><br/>
   "예" 클릭 시 회원 가입 페이지로 이동
   "아니오" 클릭 시 로그인 페이지로 이동

## 개발 환경
- Android Studio @2021.2.1

## 어플리케이션 버전
- minSdkVersion : 31
- targetSdkVersion : 32
- SDK Version: Android Version 12

## preferences 정보 (데이터베이스를 대체하여 회원 정보 저장)
person_info: 회원가입 시 회원 정보(아이디, 비밀번호, 이름, 전화번호, 주소) 저장
<img src="/images/person_info_screenshot.png" title="person_info" alt="person_info"></img>

login_info: 로그인 여부, 로그인한 아이디, 최근 로그인한 아이디를 저장
<img src="/images/login_info_screenshot.png" title="login_info" alt="login_info"></img>
