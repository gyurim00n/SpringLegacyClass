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

public class ReplyVO {

	
  private Long rno     ;//number(10)
  private Long bno     ;//varchar2(200) not null
  private String reply   ;//varchar2(2000) not null
  private String replyer    ;//varchar2(50) not null
  private Date replyDate   ;//date default sysdate
  private Date updateDate;//date default sysdate
}
