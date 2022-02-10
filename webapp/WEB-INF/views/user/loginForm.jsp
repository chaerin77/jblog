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
		
		<div id="loginForm">
			<!-- form지움 -->
	      		<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="textId">아이디</label></td>
		      			<td><input id="textId" type="text" name="id" value=""></td>
		      		</tr>
		      		<tr>
		      			<td><label for="textPassword">패스워드</label> </td>
		      			<td><input id="textPassword" type="password" name="password" value=""></td>   
		      		</tr> 
		      		<tr>
		      			<!--<c:if test="${empty authUser}">-->
			      			<td colspan="2" id="tdMsg" colspan="2">
			      				<span></span>
			      			</td>
		      			<!--</c:if>-->
		      		</tr> 
		      	</table>
	      		<div id="btnArea">
					<button class="btn">로그인</button>
				</div>
	      		
			
		</div>
		<form id="mainForm" method="get" action="${pageContext.request.contextPath }/">
		</form>
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>
	
</body>

  
<script type="text/javascript">
	//로딩완료후 요청
	$(window).load(function(){
		
	});
	
	$("#btnArea").on("click",function(){
		
		var id = $("#textId").val();
		var pw = $("#textPassword").val();
		
		var inputData = {
			id: id,
			password: pw
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/login",
			type : "post",
			//contentType : "application/json",
			data : inputData,
			
			dataType : "json", //받는 데이터 형태가 json
			success : function(result){ //테이터 자바스크립트 형태로 바뀌어서 옴
				/*성공시 처리해야될 코드 작성*/
				
				console.log(result);
				if(result == "0"){
					$("#tdMsg").text("아이디 또는 비번을 확인해 주세요.");
				}else{
					console.log("re")
					window.location.href = "${pageContext.request.contextPath}/";
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		// 로그인 성공 유무 판별
		// 필요데이터 내가 입력한 아이디, 비밀번호
	
	})
	
</script>

</html>