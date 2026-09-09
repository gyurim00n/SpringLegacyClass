package org.doit.ik.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString

public class BoardVO {

	
  private Long bno       ;//number(10)
  private String title     ;//varchar2(200) not null
  private String content   ;//varchar2(2000) not null
  private String writer    ;//varchar2(50) not null
  private Date regdate   ;//date default sysdate
  private Date updatedate;//date default sysdate
}
