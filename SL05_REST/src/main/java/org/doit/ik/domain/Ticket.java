package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

	private Integer tno;	//티켓 번호
	private String owner; 	//소유자
	private String grade;	//등급
}
