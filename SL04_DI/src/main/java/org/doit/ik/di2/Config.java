package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration

//@ImportResource("classpath:org/doit/ik/di/application-context.xml")
//@import Config2.class 자바 설정파일을 조립할떄 사용하는 어노테이션
@ComponentScan(basePackages = "ord.doit.ik.di4")
public class Config {
	//RecordImpl rvi = new RecordImpl(); 
	@Bean
	public RecordImpl record() {
		return new RecordImpl();
	}
	
	//RecordViewImpl rvi = new RecordViewImpl(record)
	//rvi.setRecord(record);
	@Bean(name = "rvi")
	public RecordViewImpl getRecordViewImpl() {
		return new RecordViewImpl(record());
		
	}
	
}//class
