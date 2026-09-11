package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main (String[] args){
		//p;40 스프링용하지않고 객체 조립사용하기
		
		//성적 정보 입력받아서 출력하는일: 인터페이스, 클래스
		
		RecordImpl record = new RecordImpl();
		
		//생성자 DI
		//RecordViewImpl rvi = new RecordViewImpl(record);
		
		//Setter DI
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		
		rvi.input();
		rvi.output();
		
		System.out.println("아몰라 ");
		
		
	}

}
