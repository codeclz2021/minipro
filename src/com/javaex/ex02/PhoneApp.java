/*
main 함수 이용한 코딩
메소드로 기능 분리
  - 시작시 파일을 읽어 리스트에 추가하기: 가독성
  - 리스트 화면에 출력기능 메소드로 분리 (1.리스트 4.검색)
  - 리스트의 모든 값을 파일에 저장한다.(2.등록 3.삭제)
*/
package com.javaex.ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	private static List<Person> pList;

	public static void main(String[] args) throws IOException {

		// 스캐너
		Scanner sc = new Scanner(System.in);

		// 파일을 읽어서 리스트에 담는다.
		loadList();

		// 시작화면
		System.out.println("******************************************");
		System.out.println("*          전화번호 관리 프로그램        *");
		System.out.println("******************************************");

		// while 시작
		boolean run = true;
		while (run) {
			// 메뉴 출력
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("------------------------------------------");
			System.out.print(">메뉴번호: ");

			// 메뉴 입력
			int menuNum = sc.nextInt();
			sc.nextLine();

			// switch() 시작
			switch (menuNum) {
			// 1(리스트)
			case 1:
				System.out.println("<1.리스트>");
				
				//화면에 출력
				showList();
				break;

			// 2(등록)
			case 2:
				System.out.println("<2.등록>");
				// 이름받기
				System.out.print("이름 > ");
				String name = sc.nextLine();
				// 휴대전화 받기
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				// 회사번호받기
				System.out.print("회사번호 > ");
				String company = sc.nextLine();

				// 리스트에 추가하기
				Person person = new Person();
				person.setName(name);
				person.setHp(hp);
				person.setCompany(company);

				pList.add(person);

				//파일에 저장
				saveList();
				System.out.println("[등록되었습니다.]");
				break;

			// 3(삭제)
			case 3:
				System.out.println("<3.삭제>");
				System.out.print(">번호 : ");
				int no = sc.nextInt();

				//리스트에서 삭제
				pList.remove(no - 1);

				// 파일에 저장
				saveList();
				System.out.println("[삭제되었습니다.]");
				break;

			// 4(검색)
			case 4:
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String keyword = sc.nextLine();

				//화면에 출력
				showList(keyword);

				break;

			// 5(종료)
			case 5:
				run = false;
				break;

			// 없는 번호일때
			default:
				System.out.println("[다시 입력해주세요.]");
				break;

			}// switch() 종료

		} // while 종료
		sc.close();

		// 종료화면
		System.out.println("");
		System.out.println("******************************************");
		System.out.println("*                   감사합니다                             *");
		System.out.println("******************************************");
	}
	//main메소드 종료

	// 파일을 읽어 리스트에 담는다.
	public static void loadList() throws IOException {
		Reader fr = new FileReader("./phoneDB.txt");
		BufferedReader br = new BufferedReader(fr);

		pList = new ArrayList<Person>();

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}

			String[] data = line.split(",");
			String name = data[0];
			String hp = data[1];
			String company = data[2];

			Person phone = new Person(name, hp, company);

			pList.add(phone);

		}
		br.close();
	}

	// 모든 리스트를 출력한다. *아래메소드와 이름같음(메소드 오버로딩)
	public static void showList() {
		showList(""); // 아래함수에 키워드를 아무것도 없는 값으로 전달
	}

	// 이름으로 검색된 리스트의 정보를 출력한다. *위메소드와 이름같음(메소드 오버로딩)
	public static void showList(String keyword) {
		for (int i = 0; i < pList.size(); i++) {
			String serchName = pList.get(i).getName();
			if (serchName.contains(keyword)) {
				System.out.print(i + 1 + ".   ");
				System.out.print(pList.get(i).getName() + "\t");
				System.out.print(pList.get(i).getHp() + "\t");
				System.out.print(pList.get(i).getCompany() + "\t");
				System.out.println("");
			}
		}
	}

	// 리스트를 파라미터로 받아 파일에 저장한다.
	public static void saveList() throws IOException {
		Writer fw = new FileWriter("./phoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < pList.size(); i++) {
			String str = pList.get(i).getName() + "," + pList.get(i).getHp() + "," + pList.get(i).getCompany();
			bw.write(str);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}