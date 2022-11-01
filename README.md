# 2022년 2학기 모바일프로그래밍 개인 과제
로그인, 회원가입, 상품 정보 3가지 페이지 구현

## 과제 내용
1. 첫번째 화면 (Relative Layout 혹은 Fragment 사용) - 5점
- 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button)
- 첫번째 화면 초기화시에 기존에 저장된 개인정보 읽어 오기
- ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
- ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동
- 회원가입 없이도 메인 버튼(상품 출력 페이지)을 클릭하면 세번째 화면으로 이동 가능 
<br/>
2. 두번째 화면 (Linear Layout 혹은 Fragment 사용) - 5점
- 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력
- ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크)
- 이름/전화번호/주소(EditView)
- 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept)
- 회원정보를 저장하고 첫번째 페이지로 이동 
  회원정보 저장은 전역변수, 프레퍼런스(Preference), 파일 중에 하나를 선택하여 활용  
<br/>
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
1. 첫번째 화면 (Relative Layout 혹은 Fragment 사용) - 5점 <br/>
  1-1. 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button) <br/>
       <img src="/images/Screenshot_login.png" title="person_info" alt="person_info" width="300px"></img> <br/>
  1-2. 첫번째 화면 초기화시에 기존에 저장된 개인정보 읽어 오기
       <img src="/images/Screenshot_20221101_212049.png" title="person_info" alt="person_info"></img>
       마지막으로 로그인한 아이디를 자동으로 입력
  1-3. ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
       <img src="/images/Screenshot_id_fault.png" title="person_info" alt="person_info"></img>
       아이디 오류 시 경고 메세지
       <img src="/images/Screenshot_pw_fault.png" title="person_info" alt="person_info"></img>
       비밀번호 오류 시 경고 메세지





## 개발 환경
- Android Studio @2021.2.1

## 어플리케이션 버전
- minSdkVersion : 31
- targetSdkVersion : 32
- SDK Version: Android Version 12

## preferences 정보 (데이터베이스를 대체하여 회원 정보 저장)
person_info: 회원가입 시 회원 정보(아이디, 비밀번호, 이름, 전화번호, 주소) 저장
<img src="/images/login_info_screenshot.png" title="person_info" alt="person_info"></img>

login_info: 로그인 여부, 로그인한 아이디, 최근 로그인한 아이디를 저장
<img src="/images/person_info_screenshot.png" title="login_info" alt="login_info"></img>
