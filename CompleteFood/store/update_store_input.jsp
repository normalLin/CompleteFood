<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //StoreServlet.java (Concroller), 存入req的storeVO物件 (包括幫忙取出的storeVO, 也包括輸入資料錯誤時的storeVO物件)
%>

<html>
<head>
<title>餐廳資料修改</title>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
	$(function() {
		$("#submitBtn").click(function(e) {
			var confirmMsg = confirm("確認送出?");
			if (confirmMsg == false) {
				e.preventDefault();
			}
		});
	});
</script>
<style>
.font {
	font-size: 130%
}
.cr1 {
	color: DarkMagenta;
}

.cr2 {
	color: Navy;
}
</style>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body style="background-color:Wheat">

	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<table border='1' cellpadding='5' cellspacing='0' width='400'>
				<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
					<td><h3>餐廳資料修改</h3> <a href="restaurant.jsp"><img
							src="images/huyna15.jpg" border="0">回餐廳資訊 </a></td>
				</tr>
			</table>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-4">
			<h3 style="font-style: italic; color: darkblue">資料修改:</h3>
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
		<FORM METHOD="post" ACTION="store.do" name="form1">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<table border="0">
					<tr>
						<td class="font cr1">餐廳編號:<font color=red><b>*</b></font></td>
						<td class="font"><%=storeVO.getStore_id()%></td>
					</tr>
					<tr>
						<td class="font cr2">餐廳名稱:</td>
						<td><input type="TEXT" name="store_name" size="45"
							class="font" value="<%=storeVO.getStore_name()%>" /></td>
					</tr>
					<tr>
						<td class="font cr1">聯絡電話:</td>
						<td><input type="TEXT" name="phone_number" size="45"
							class="font" value="<%=storeVO.getPhone_number()%>" /></td>
					</tr>
					<tr>
						<td class="font cr2">營業地址:</td>
						<td><input type="TEXT" name="shop_address" size="45"
							class="font" value="<%=storeVO.getShop_address()%>" /></td>
					</tr>
					<tr>
						<td class="font cr1">網址:</td>
						<td><input type="TEXT" name="web_url" size="45" class="font"
							value="<%=storeVO.getWeb_url()%>" /></td>
					</tr>
					<tr>
						<td class="font cr2">外送費:</td>
						<td><input type="TEXT" name="subTotal" size="45" class="font"
							value="<%=storeVO.getSubTotal()%>" /></td>
					</tr>
					<tr>
						<td class="font cr1">最低外送金:</td>
						<td><input type="TEXT" name="mini_Price" size="45"
							class="font" value="<%=storeVO.getMini_Price()%>" /></td>
					</tr>
					<tr>
						<td class="font cr2">營業時間:</td>
						<td><input type="TEXT" name="deliveryOperationTime" size="45"
							class="font" value="<%=storeVO.getDeliveryOperationTime()%>" /></td>
					</tr>
					<jsp:useBean id="styleSrv" scope="page"
						class="com.style.model.StyleService" />
					<tr>
						<td class="font cr1">餐廳類型:<font color=red><b>*</b></font></td>
						<td><select size="1" name="style_id" class="font">
								<c:forEach var="styleVO" items="${styleSrv.all}">
									<option value="${styleVO.style_id}"
										${(storeVO.style_id==styleVO.style_id)?'selected':'' }>${styleVO.style_name}
								</c:forEach>
						</select></td>
					</tr>
					</div>
				</table>
				<br><br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="store_id"
					value="<%=storeVO.getStore_id()%>"> <input id="submitBtn"
					class="btn-success" type="submit" value="送出修改">
		</FORM>
	</div>
</body>
</html>
