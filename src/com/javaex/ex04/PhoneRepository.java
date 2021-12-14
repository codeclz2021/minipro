package com.javaex.ex04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepository {
	private List<Person> pList;
	
	//생성자
	public PhoneRepository(){

		//파일로 부터 데이터를 읽어 리스트에 추가한다.
		pList = new ArrayList<Person>();
		loadList();

	}
    
	//getter
	//리스트를 리턴해 줍니다.
	public List<Person> getList(){
		return pList;
	}
	
    //phoneDB.txt 파일을 읽어 모든 전화번호(리스트)를 전달하는 메소드
	public void loadList(){
		
		try {
			Reader fr = new FileReader("./phoneDB.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while(true){
				Person phoneVO = new Person();
				String line;
				
				line = br.readLine();
				if(line!=null){
					String[] dataArray = new String[3];
					dataArray = line.split(",");
								
					phoneVO.setName(dataArray[0]);
					phoneVO.setHp(dataArray[1]);
					phoneVO.setCompany(dataArray[2]);
					
					pList.add(phoneVO);
					
				}else{
					break;
				}
				
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	//phoneDB.txt 에 모든 전화번호 리스트를 저장하는 메소드
	private void saveList(){
		
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
	
	//기존데이터에 새로입력받은 데이터를 추가하여 모두저장하는 메소드 
	public void addInfo(Person phoneVO) {
		pList.add(phoneVO);
		saveList();
	}

	//선택한 번호의 데이터를 삭제하고 저장하는 메소드(모두 다시저장)
	public void delInfo(int num) {
		pList.remove(num-1);
		saveList();
	}
	

}
