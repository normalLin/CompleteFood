<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.food_items.model.*"%>
<%@ page import="com.species.model.*"%>

<%
	Food_itemsVO food_itemsVO = (Food_itemsVO) request
			.getAttribute("food_itemsVO"); //Food_itemsServlet.java (Concroller), 存入req的food_itemsVO物件 (包括幫忙取出的food_itemsVO, 也包括輸入資料錯誤時的food_itemsVO物件)
%>

<html>
<head>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>菜單資料修改</title>
<style>
.font {
	font-size: 130%
}
</style>
<script>
	$(function() {
		$("#submitBtn").click(function(e) {
			var confirmMsg = confirm("確定送出?");
			if (confirmMsg == false) {
				e.preventDefault();
			}
		});
	});
</script>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body style="background-color: Wheat">

	<div class="row">
		<div class="rol-md-12">
			<br>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<table border='1' cellpadding='5' cellspacing='0' width='400'>
				<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
					<td><h3>菜單資料修改</h3> <a href="restaurant.jsp"><img
							src="images/huyna15.jpg" border="0">回餐廳資訊 </a></td>
				</tr>
			</table>
		</div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<h3>資料修改:</h3>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</c:if>
		</div>
	</div>
	<br>

	<div class="row">
		<FORM METHOD="post" ACTION="item.do" name="form1">
			<div class="col-md-4"></div>
			<div class="col=md=4">
				<table border="0">
					<tr>
						<td class="font">菜色編號:<font color=red><b>*</b></font></td>
						<td class="font"><%=food_itemsVO.getItems_id()%></td>
					</tr>
					<tr>
						<td class="font">菜的大名:</td>
						<td><input type="TEXT" name="item_name" size="45"
							class="font" value="<%=food_itemsVO.getItem_name()%>" /></td>
					</tr>
					<tr>
						<td class="font">價格:</td>
						<td><input type="TEXT" name="price" size="45" class="font"
							value="<%=food_itemsVO.getPrice()%>" /></td>
					</tr>

					<jsp:useBean id="speciesSrv" scope="page"
						class="com.species.model.SpeciesService" />
					<tr>
						<td class="font">菜的品種:<font color=red><b>*</b></font></td>
						<td><select size="1" name="species_id" class="font">
								<c:forEach var="speciesVO" items="${speciesSrv.all}">
									<option value="${speciesVO.species_id}"
										${(food_itemsVO.species_id==speciesVO.species_id)?'selected':'' }>${speciesVO.species_name}
								</c:forEach>
						</select></td>
					</tr>

				</table>
			</div>

			<br><br>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="items_id"
						value="<%=food_itemsVO.getItems_id()%>"> <input
						id="submitBtn" class="btn-success" type="submit" value="送出修改">
				</div>
			</div>
		</FORM>
	</div>

</body>
</html>
