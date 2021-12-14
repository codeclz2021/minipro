package com.javaex.ex03;

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

public class PhoneManager {

	private List<Person> pList;
	private Scanner sc;
	
	//생성자에서 인스턴스화 후에 스캐너와 리스트를 준비한다.
	public PhoneManager() {
		sc = new Scanner(System.in);
		pList = new ArrayList<Person>();

		//파일로 부터 데이터를 읽어 리스트에 추가한다.
		loadList();
	}

	// 시작준비 (시작화면 출려과 리스트 가져온다)
	public void showTitle() {
		// 타이틀 출력
		System.out.println("******************************************");
		System.out.println("*          전화번호 관리 프로그램        *");
		System.out.println("******************************************");
	}

	// 메뉴 출력과 입력을 받는다.
	public int showMenu() {
		int menuNum;
		System.out.println("");
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("------------------------------------------");
		System.out.print(">메뉴번호: ");
		menuNum = sc.nextInt();
		sc.nextLine();
		return menuNum;

	}

	// 1.리스트선택시
	public void showList() {
		System.out.println("<1.리스트>");
		
		//리스트 출력
		printList();
	}

	// 2.등록선택시
	public void showAdd() {
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

	}

	// 3.삭제선택시
	public void showRemove() {
		System.out.println("<3.삭제>");
		System.out.print(">번호 : ");
		int no = sc.nextInt();

		//리스트에서 삭제
		pList.remove(no - 1);

		// 파일에 저장
		saveList();
		System.out.println("[삭제되었습니다.]");
	}

	// 4.검색선택시
	public void showSearch() {
		System.out.println("<4.검색>");
		System.out.print(">이름: ");
		String keyword = sc.nextLine();
		
		//화면에 출력
		printList(keyword);
	}

	// 5.종료시
	public void showEnd() {
		System.out.println("<5.종료>");
		System.out.println("");
		System.out.println("******************************************");
		System.out.println("*                   감사합니다           *");
		System.out.println("******************************************");

		sc.close();
	}
	
	
	// 메뉴번호를 잘못 입력시 안내문구를 출력하는 메소드
	public void showEtc() {
		System.out.println("[다시입력해주세요]");
		System.out.println("");
	}
	
	
	// 파일을 읽어 리스트에 담아 전달한다.
	private void loadList() {
		try {
			Reader fr = new FileReader("./phoneDB.txt");
			BufferedReader br = new BufferedReader(fr);

			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				String[] data = line.split(",");
				String name = data[0];
				String hp = data[1];
				String company = data[2];

				Person person = new Person(name, hp, company);

				pList.add(person);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 리스트를 파일에 저장한다.
	private void saveList() {
		
		try {
			Writer fw = new FileWriter("./phoneDB.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < pList.size(); i++) {
				String str = pList.get(i).getName() + "," + pList.get(i).getHp() + "," + pList.get(i).getCompany();
				bw.write(str);
				bw.newLine();
				
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	// 모든 리스트를 출력한다. *아래메소드와 이름같음(메소드 오버로딩)
	private void printList() {
		printList("");// 아래 메소드에 키워드값을 비워서 호출
	}

	// 이름으로 검색된 리스트의 정보를 출력한다. *위메소드와 이름같음(메소드 오버로딩)
	private void printList(String keyword) {
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
}
