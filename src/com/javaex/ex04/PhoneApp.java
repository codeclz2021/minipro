/*
main 함수에는 기본 구조만 구현 --> 전체의 흐름을 파악하기 좋다.

주요기능은 phoneView, PhoneRepository 클래스 만들어서 구현
phoneView : 화면에서 데이터 받기, 필요한 메세지 출력 등 화면과 관련된 업무를 하는 클래스
PhoneManager : 리스트에 데이터를 추가 삭제 등의 관리를 하고 
               리스트의 정보가 변할경우 PhoneDB.txt파일에 저장하는 업무를 하는 클래스
*/

package com.javaex.ex04;

public class PhoneApp {

	public static void main(String[] args) {

		// PhoneView 생성
		PhoneView phoneView = new PhoneView();

		// PhoneManager 생성 --> 생성자를 통해 파일의 정보가 리스트에 추가된다.
		PhoneRepository phoneRepo = new PhoneRepository();

		//변수
		boolean run = true;

		// 시작시 안내 문구를 출력
		phoneView.showStart();

		// while 시작
		while (run) {
			// 메뉴 출력, 입력값 리턴
			int menuNum = phoneView.showMenu();

			// switch() 시작
			switch (menuNum) {

			// 1(리스트)
			case 1:
				phoneView.showList(phoneRepo.getList());
				break;

			// 2(등록)
			case 2:
				Person phoneVO = phoneView.showAdd();
				phoneRepo.addInfo(phoneVO);
				phoneView.showAddResult();
				break;

			// 3(삭제)
			case 3:
				int delNo = phoneView.showDel();
				phoneRepo.delInfo(delNo);
				phoneView.showDelResult();
				break;

			// 4(검색)
			case 4:
				String keyword = phoneView.showSearch();
				phoneView.showSearchResult(phoneRepo.getList(), keyword);
				break;

			// 5(종료)
			case 5:
				phoneView.showEnd();
				run = false;
				break;

			// 없는 번호일때
			default:
				phoneView.showEtc();
				break;
			}// switch() 종료

		} // while 종료

		 // 종료화면
		phoneView.showEnd();
	}

}
