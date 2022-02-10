<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id" value=""></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
		</div>
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>

</body>

<script type="text/javascript">
//페이지 로딩완료되어있는 상태에서 요청
	//로딩완료후 요청
	$(window).load(function(){
		
	});
	
	//중복아이디체크 버튼이 클릭될때
	$("#btnIdCheck").on("click",function(){
	
		var id = $("#txtId").val(); //입력한 아이디의 값-value
		console.log(id);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/overCheck",
			type : "post",
			//contentType : "application/json",
			data : {id: id},
			
			dataType : "json", 
			success : function(result){ 
				/*성공시 처리해야될 코드 작성*/
				
				console.log(result);
				if(result == "1"){
					$("#tdMsg").text("다른 아이디로 가입해 주세요");
					$("txtId").val("");
				}else{
					$("#tdMsg").text("사용할 수 있는 아이디 입니다.");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		}); //아이디체크 눌렀을때 내가 입력한 아이디가 db에 저장되어있는지 판별해야함
		
	})
	
	
	//회원가입 버튼을 눌렀을때
	$("#btnJoin").on("click",function(){
		console.log("회원가입버튼 클릭")
		
		var id = $("#txtId").val();
		var idcheck = $("#btnIdCheck").val();//val 말고 다른거써야 사용할수있는 아이디입니다. 뜰때 체크완료되게끔..될텐데
		var pw = $("#txtPassword").val();
		var name = $("#txtUserName").val();
		var agree = $("#chkAgree").val();
		
		if(id == ""){
			alert("아이디를 입력 해주세요");
			return false;
		}
		if(idcheck == ""){ //아이디체크 버튼을 누르지 않았다면
			alert("아이디 중복체크를 해주세요");
			return false;
		}
		if(pw == ""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		if(name == ""){
			alert("이름을 입력 해주세요");
			return false;
		}
		if(agree == ""){
			alert("약관에 동의 해주세요");
			return false;
		}
		
	});


</script>


</html>