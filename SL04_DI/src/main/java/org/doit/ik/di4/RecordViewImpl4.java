package org.doit.ik.di4;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
@Setter
@Component(value =  "rvi")
public class RecordViewImpl4 implements RecordView4 {
	//결합력이 높은 코딩. 좋지않다.
	//private RecordImpl record = new RecordImpl(); 
	
	@Autowired
	private RecordImpl4 record=null;
	
	public RecordViewImpl4() {
		
	}
	
	public RecordViewImpl4(RecordImpl4 record) {
		this.record = record;
	}
	
	// 생성자나 Setter를 통해 주입이 제대로 되고 있는지 확인 필요
	/*
	 * @Autowired public void setRecord(RecordImpl record) { this.record = record; }
	 */
	
	
	@Override
	public void input() {
		try(Scanner scanner = new Scanner(System.in)) {
			System.out.print(">kor, eng , mat input?: ");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
			
			System.out.println("성적 입력 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("성적 입력 실패!! ");
		}
		
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		System.out.printf(">kor= %d, eng= %d, mat= %d, tot= %d, avg= %.2f\n",
				this.record.getKor()
				,this.record.getEng()
				,this.record.getMat()
				,this.record.total()
				,this.record.avg()
				
				);
	}

}
