# 🦁 멋쟁이 사자처럼 13기 JAVA 스터디 3주차 과제

## ✅ 주제
- 🍔 햄버거 키오스크 (POJO/ JAVA 만 사용해서 문제 풀이)

## 📌 기능 요구 사항
1. 메뉴 관리 (관리자)
- 각 제품은 이름, 가격, 설명, 카테고리, 재고 수량를 갖는다.
-  품절된 상품은 사용자가 선택 할 수 없다 (보이기는 해야함)
2. 주문기능
- 사용자는 키오스크에서 원하는 메뉴를 선택하여 담을 수 있다.
-  재고 수량을 넘는 수량은 선택 할 수 없다.
    - 세트의 경우 일반 버거와 수량을 공유한다.
    - 세트를 주문 할 경우 일반 버거, 감자튀김, 선택한 음료 전부 수량 -1
- 품절된 제품은 주문할 수 없다.
- 주문 완료시 총 금액을 계산하여 출력한다.
3. 결제기능
- 사용자는 현금으로 결제가 가능하다.
- 결제가 완료되면 주문번호와 영수증(주문 내역, 결제 금액, 판매자)이 출력된다.
4. 주문 내역 조회
- 사용자는 본인의 주문 내역을 확인 할 수 있다.
- 주문 내역에는 주문한 메뉴, 총 금액, 결제 방식이 포함된다.

## 📌 프로그래밍 요구 사항
- 각 메소드의 depth는 2 depth 까지만 허용
- 3항 연산자를 쓰지 않는다.
- 메소드의 길이가 15라인을 넘어가지 않도록 구현한다.
- 메소드가 한 가지 일만 잘 하도록 구현한다.
- else, switch/case 예약어를 쓰지 않는다.
- 정적(static) 메서드는 유틸리티성 클래스에서만 사용한다.
    - ex) from(), of() 등.
- 하드코딩된 숫자 또는 문자열을 사용하지 않는다. (상수를 활용)
-  접근제어자를 명확히 구분하고, 필요하지 않은 경우 public 사용을 지양한다. setter()는 쓰지 않는다.

## 예외 처리
#### user
- DUPLICATION_ADMIN("[ERROR] - 해당 관리자는 이미 존재합니다: %s")
- NOT_EXIST_ADMIN("[ERROR] - 존재하지 않는 관리자입니다: %s")
- UNAUTHORIZED_ADMIN("[ERROR] - 관리자가 로그인하지 않았습니다.") 
- DUPLICATION_CUSTOMER("[ERROR] - 해당 회원은 이미 존재합니다: %s")
- NOT_EXIST_CUSTOMER("[ERROR] - 존재하지 않는 회원입니다: %s")
- NOT_ENOUGH_MONEY("[ERROR] - 잔액이 부족합니다.")
#### file
- EMPTY_FILE("[ERROR] - 파일이 존재하지 않습니다.")
- FAILED_READ_FILE("[ERROR] - 파일 읽기에 실패했습니다.")
- EMPTY_INPUT("[ERROR] - 입력이 존재하지 않습니다.")
#### input
- INVALID_INPUT("[ERROR] - 올바르지 않은 형식으로 입력했습니다.")
- INVALID_DELIMITER("[ERROR] - 허용되지 않은 구분자입니다.")
- INVALID_OPTION("[ERROR] - 존재하지 않는 옵션입니다.")
- FAILED_WRITE_FILE("[ERROR] - 파일 쓰기에 실패했습니다.")
#### Menu
- NOT_EXIST_MENU("[ERROR] - 존재하지 않는 상품입니다: %s")
- NOT_EXIST_CATEGORY("[ERROR] - 존재하지 않는 카테고리입니다: %s")
- NOT_EXIST_MENU_STATUS("[ERROR] - 존재하지 않는 형식입니다: %s")
- INVALID_QUANTITY("[ERROR] - 재고 수량을 초과하여 구매할 수 없습니다.")
- INVALID_BUY("[ERROR] - 품절된 메뉴입니다: %s")

## 🔥 Task
1. 프로그램 초기 셋팅
2. 각 도메인 별 예외 메세지 정의 
3. 입출력 메세지 정의
4. 프로그램 시작 전 전처리 기능 구현
5. 관리자 생성 기능 구현
6. 관리자 접속 기능 구현
7. 회원 생성 기능 구현
8. 회원 접속 기능 구현
9. 프로그램 옵션 기능 구현
10. 메뉴 입력 기능 구현
11. 결제 기능 구현
12. 영수증 출력 기능 구현
13. 추가 구매 여부 구현

## 다이어그램
![kiosk2차](https://github.com/user-attachments/assets/0e26512d-23a8-4f25-9eb5-fff3aefb62e2)



## 🤝 Git Convention

### Branch
- `main` : 개발용 branch
- feature : 기능 구현용 branch
- Issue_종류/#Issue_번호 : branch 생성
- Issue 17번 부터 봐주시면 됩니다!

### Commit Prefix

| 종류        | 내용                                             |
|-----------| ------------------------------------------------ |
| 💫 Feat        | 기능 구현                                          |
| 🐛 Fix    | 버그 수정                                           |
| 🔨 Refactor | 코드 리팩토링                                         |
| ✅ Test    | 테스트 업무                                        |
| 🗂️  File   | 파일 이동 또는 삭제, 파일명 변경                         |
| 📝 Docs   | md, yml 등의 문서 작업                               |
| 🔧 Chore  | 이외의 애매하거나 자잘한 수정                            |
| ⚙️ Setting | 빌드,패키지,인프라 등 프로젝트 설정                           |
