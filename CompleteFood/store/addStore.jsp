<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>餐廳新增</title>
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
		<table border='1' cellpadding='5' cellspacing='0' width='500'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td><h3>餐廳資料新增</h3></td>
				<td><a href="restaurant.jsp"><img src="images/huyna13.jpg"
						border="1"> 回餐廳資訊 </a></td>
			</tr>
		</table>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-5">
			<h3 style="font-style: italic; color: darkblue">餐廳基本資料:</h3>
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
	<br>
	<div class="row">
	<FORM METHOD="post" ACTION="store.do" name="form1">
		
			<div class="col-md-4"></div>
			<div class="col-md-8">
				<table border="0">

					<tr>
						<td class="font cr1">餐廳名稱:</td>
						<td><input type="TEXT" name="store_name" size="45"
							class="font"
							value="<%=(storeVO == null) ? "" : storeVO.getStore_name()%>" /></td>
					</tr>
					<tr>
						<td class="font cr2">聯絡電話:</td>
						<td><input type="TEXT" name="phone_number" size="45"
							class="font"
							value="<%=(storeVO == null) ? " " : storeVO.getPhone_number()%>" /></td>
					</tr>
					<tr>
						<td class="font cr1">營業地址:</td>
						<td><input type="TEXT" name="shop_address" size="45"
							class="font"
							value="<%=(storeVO == null) ? " " : storeVO.getShop_address()%>" /></td>

					</tr>
					<tr>
						<td class="font cr2">網址:</td>
						<td><input type="TEXT" name="web_url" size="45" class="font"
							value="<%=(storeVO == null) ? " " : storeVO.getWeb_url()%>" /></td>
					</tr>
					<tr>
						<td class="font cr1">外送費:</td>
						<td><input type="TEXT" name="subTotal" size="45" class="font"
							value="<%=(storeVO == null) ? " " : storeVO.getSubTotal()%>" /></td>
					</tr>
					<tr>
						<td class="font cr2">最低外送金:</td>
						<td><input type="TEXT" name="mini_Price" size="45"
							class="font"
							value="<%=(storeVO == null) ? " " : storeVO.getMini_Price()%>" /></td>
					</tr>
					<tr>
						<td class="font cr1">營業時間:</td>
						<td><input type="TEXT" name="deliveryOperationTime" size="45"
							class="font"
							value="<%=(storeVO == null) ? " " : storeVO
					.getDeliveryOperationTime()%>" /></td>
					</tr>
					<jsp:useBean id="styleSrv" scope="page"
						class="com.style.model.StyleService" />
					<tr>
						<td class="font cr2">餐廳類型:<font color=red><b>*</b></font></td>
						<td><select size="1" name="style_id" class="font">
								<c:forEach var="styleVO" items="${styleSrv.all}">
									<option value="${styleVO.style_id}"
										${(storeVO.style_id==styleVO.style_id)? 'selected':'' }>${styleVO.style_name}
								</c:forEach>
						</select></td>
					</tr>


				</table>
			</div>
		</div>
		
		<br><br><br><br>
		<div class="row">
		<div class="col-md-5"></div>
		<div class="col-md-4">
		 <input type="hidden" name="action" value="insert"> <input
			type="submit" class="btn-success" value="送出新增"></div>
	
	</FORM>
		</div>
</body>

</html>
