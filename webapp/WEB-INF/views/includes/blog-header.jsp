<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<%-- <h1><a href="">${blogVo.userName}의 블로그입니다.</a></h1> --%>
			<h1><a href="">${blogVo.blogTitle}</a></h1>
			<ul class="clearfix">
				
				<!-- 로그인 후 메뉴 -->
				<!-- 자신의 블로그일때만 관리 메뉴가 보인다. choose안에조건한번더 -->
				<c:choose>
					<c:when test="${!empty authUser}">
						<c:if test="${authUser.id == blogVo.id}">
						<li><a class="btn_s" href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">내블로그 관리</a></li>
						</c:if>
						<li><a class="btn_s" href="${pageContext.request.contextPath}/${blogVo.id}/logout">로그아웃</a></li>
					</c:when>
		 	
		 			<c:otherwise>
						<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
					</c:otherwise>
				</c:choose>	
				<!-- 로그인 전 메뉴 -->
			</ul>
		</div>
		<!-- //header -->
		