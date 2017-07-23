<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	StoreService storeSrv = new StoreService();
    List<StoreVO> list = storeSrv.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="styleSrv" scope="page"
	class="com.style.model.StyleService" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>Adminstration_Schedule</title>
<style>
.h1_title {
	position: absolute;
	left: 800px;
	top: 50px;
	font-size: 550%;
	color: tomato;
	font-style: oblique;
}

.h2_right {
	position: absolute;
	left: 1400px;
	top: 350px;
	font-size: 150%;
	font-style: italic;
}
.h3{
background-color:Khaki;
}
</style>
<script>
	$(function() {
		$(".submitBtn").click(function(e) {
			var confirmMsg = confirm("確認下架此餐廳?");
			if (confirmMsg == false) {
				e.preventDefault();
			}
		});
	});
</script>
</head>

<body style="background-color:Wheat">

	<header id="header" class="media"> <section id="main"
		class="p-relative" role="main"> <!-- Sidebar --> <aside
		id="sidebar"> <!-- Sidbar Widgets -->
	<div class="side-widgets overflow">
		<!-- Profile Menu -->
		<div class="text-center s-widget m-b-25 dropdown" id="profile-menu"
			style="float: left">
			<a href="" data-toggle="dropdown"> <img
				class="profile-pic animated" src="images/huyna14.jpg"
				style="margin: 5px" alt="">
			</a>
			<ul class="dropdown-menu profile-menu">
				<li><a href="">我的首頁</a><i class="icon left">&#61903;</i><i
					class="icon right">&#61815;</i></li>
				<li><a href="">訊息</a><i class="icon left">&#61903;</i><i
					class="icon right">&#61815;</i></li>
				<li><a href="">設定</a><i class="icon left">&#61903;</i><i
					class="icon right">&#61815;</i></li>
				<li><a href="">登出</a><i class="icon left">&#61903;</i><i
					class="icon right">&#61815;</i></li>
			</ul>
			<h4 class="m-0">administrator</h4>
			@gmail.com
		</div>

		<div style="margin: 1px auto">
			<a id="menu-toggle"></a> <a class="h1_title">食濟</a>
		</div>

	</div>
	</aside></header>
	<!-- Content -->
	<section id="content" class="container"> <!-- Shortcuts -->
	<div class="block-area shortcut-area" style="padding-top: 50px">

		<a class="shortcut tile" href=""restaurant.jsp""> <img
			src="images/shortcuts/restaurant.png" width="70" height="55" alt="">
			<small class="t-overflow">餐廳資訊</small>
		</a>
		<%--  功能尚未開放     <a class="shortcut tile" href="order.html">
                        <img src="images/shortcuts/order.jpg" width="70" height="55" alt="">
                        <small class="t-overflow">訂單管理</small>
                    </a>
                    <a class="shortcut tile" href="popular.html">
                        <img src="images/shortcuts/pop.png" width="70" height="55" alt="">
                        <small class="t-overflow">人氣流量統計</small>
                    </a>
                    <a class="shortcut tile" href="financial.html">
                        <img src="images/shortcuts/stats.png" width="70" height="55" alt="">
                        <small class="t-overflow">財務分析</small>
                    </a>
                    <a class="shortcut tile" href="adv.html">
                        <img src="images/shortcuts/advertising.png" width="70" height="55" alt="">
                        <small class="t-overflow">廣告管理</small>
                    </a>
                    <a class="shortcut tile" href="member.html">
                        <img src="images/shortcuts/member.png" width="70" height="55" alt="">
                        <small class="t-overflow">會員管理</small>
                    </a>																																			 
                     <a class="shortcut tile" href="schedule.html">
                        <img src="images/shortcuts/calendar.png" width="70" height="55" alt="">
                        <small class="t-overflow">行程規劃</small>
                    </a> 																														 --%>
	</div>

	<hr class="whiter" />

	<!--///////////////////////////////////////////////////--> <!-- Multi Column -->
	<div class="block-area">

		<form class="row form-columned" role="form">

			<div style="margin-right: auto" class="h2_right">
				<a href='addStore.jsp' style="color: HotPink"> 新增餐廳 </a>
		</form>
	</div>

	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if> <%@ include file="page1.file"%> <c:forEach
		var="storeVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">


		<!-- Content Boxes -->
		<div class="block-area " id="content-boxes">
			<div >
				<div class="col-sm-4 col-md-3">
					<div class="thumbnail tile h3">

						<div class="p-15">
							<h4 style="font-size: 130%; color: darkgreen">${storeVO.store_name}</h4>

							<p>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/store.do">
								<input type="submit" class="btn-info btn-sm" value="查看詳情">
								<input type="hidden" name="store_id" value="${storeVO.store_id}">
								<input type="hidden" name="action" value="check_store">
							</FORM>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/store.do">
								<input  type="submit"
									class="btn btn-warning btn-sm submitBtn" value="下架"> <input
									type="hidden" name="store_id" value="${storeVO.store_id}">
								<input type="hidden" name="action" value="delete">
							</FORM>

							</p>
						</div>
					</div>
				</div>
			</div>
	</c:forEach> </section>
	</section>
	<%@ include file="page2.file"%>
</body>
</html>