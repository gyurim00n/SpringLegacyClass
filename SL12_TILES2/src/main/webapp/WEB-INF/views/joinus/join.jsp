<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="content">
               <form action="" method="post">
                  <h2>회원가입</h2>
                  <h3 class="hidden">방문페이지 로그</h3>
                  <p id="breadscrumb" class="block_hlist clear"><img alt="Step1 개인정보 등록" src="images/step2.png" /></p>
                  <h3 class="hidden">회원가입 폼</h3>
                  <div id="join-form" class="join-form margin-large" >                  
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           아이디
                        </dt>
                        <dd class="join-form-data">
                           <input type="text" name="id" value="scott"/>
                           <input id="btnCheckUid" class="button" type="button" value="중복확인" />
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           비밀번호
                        </dt>
                        <dd class="join-form-data">
                           <input type="password" name="pwd" value="1234"/>
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           비밀번호 확인
                        </dt>
                        <dd class="join-form-data" >
                           <input type="password" name="pwd2" />
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           이름
                        </dt>
                        <dd class="join-form-data">
                           <input type="text" name="name" value="scott"/>
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           성별
                        </dt>
                        <dd class="join-form-data">
                           <select name="gender">
                              <option>남성</option>
                              <option>여성</option>
                           </select>
                        </dd>
                     </dl>
                     <dl class="join-form-row birthday">
                        <dt class="join-form-title">
                           생년월일
                        </dt>
                        <dd class="join-form-data">                        
                                   <span>
                                       <input type="text" name="year" id="year" value="2002"/>년
                                       <input type="text" name="month" id="month" value="3"/>월
                                       <input type="text" name="day" id="day" value="22"/>일
                                     
                                       <!-- <input type="date" name="birth" id="birth"> 이게 베스트-->
                                       <!-- <input type="hidden" name="Birth" id="birth" /> -->
                                        <script>
                                         // focus <-> blur 이벤트 언제 발생...
                                         /* [1]
                                         $("#day").blur(function (){
                                            var year = $("#year").val();
                                            var month = $("#month").val();
                                            var day = $("#day").val();
                                            var birth = `\${year}-\${month}-\${day}`;
                                            $("#birth").val( birth );
                                            // alert(  $("#birth").val() );
                                         });
                                         */
                                       </script>
                                   </span>
                                   <span class="moon">
                                       <input type="radio" name="is_lunar" value="Solar" id="IsSolar" checked />양력
                                       <input type="radio" name="is_lunar" value="Lunar" id="IsLunar" />음력
                                   </span>
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           핸드폰 번호
                        </dt>
                        <dd class="join-form-data">
                           <input type="text" name="cphone" value="010-1234-1234"/><span>  [(-)를 포함할 것: 예) 010-3456-2934]</span>
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           이메일
                        </dt>
                        <dd class="join-form-data">
                           <input type="text" name="email" value="scott@ssdf.com"/>
                        </dd>
                     </dl>
                     <dl class="join-form-row">
                        <dt class="join-form-title">
                           취미
                        </dt>
                        <dd class="join-form-data habit">
                           <input type="checkbox" name="habit" id="music" value="music" checked="checked"/><label for="music">음악</label>
                           <input type="checkbox" name="habit" id="movie" value="movie" checked="checked"/><label for="movie">영화</label>
                           <input type="checkbox" name="habit" id="trip" value="tirp" /><label for="trip">여행</label>
                        </dd>
                     </dl>                  
                  </div>
               <div id="buttonLine">
                  <input class="btn-okay button" type="submit" value="가입" />
               </div>
               </form>   
            </div>
            