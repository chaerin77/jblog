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
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			<tr>
							<td>1</td>
							<td>자바프로그래밍</td>
							<td>7</td>
							<td>자바기초와 객체지향</td>
						    <td class='text-center'>
						    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						    </td>
						</tr>
						<tr>
							<td>2</td>
							<td>오라클</td>
							<td>5</td>
							<td>오라클 설치와 sql문</td>
						    <td class='text-center'>
						    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						    </td>
						</tr>
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" value=""></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	
	//로딩된 후 요청
	$(window).load(function(){
		
	});
	
	var content = $("[name='content']").val();
	var desc = $("[name='desc']").val();
	
	
	
	//ajax사용하기
	$.ajax({
		//요청
		url : "${pageContext.request.contextPath }/api/user/overCheck",
		type : "post",
		//contentType : "application/json",
		data : userVo,
		
		//응답
		dataType : "json",
		success : function(inputId){
			//성공시 처리해야될 코드 작성
			message(inputId);
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
</script>




</html>