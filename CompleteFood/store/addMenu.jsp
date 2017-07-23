
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.food_items.model.*"%>

<%
	Food_itemsVO food_itemsVO = (Food_itemsVO) request
			.getAttribute("food_itemsVO");
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
<title>菜色新增</title>
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

<body style="background-color:Wheat">
	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<table border='1' cellpadding='5' cellspacing='0' width='500'>
				<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
					<td><h3>菜色資料新增</h3></td>
					<td><a href="restaurant.jsp"><img src="images/huyna13.jpg"
							border="1"> 回餐廳資訊 </a></td>
				</tr>
			</table>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<h3 style="font-style: italic; color: darkblue">菜色基本資料:</h3>
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
			<div class="col-md-5">
				<table border="0">
					<input type="text" value="${store_id}" name="store_id"
						style="display: none">
					<tr>
						<td class="font cr1">菜色名稱:</td>
						<td><input type="TEXT" name="item_name" size="45"
							class="font"
							value="<%=(food_itemsVO == null) ? "" : food_itemsVO.getItem_name()%>" /></td>
					</tr>
					<tr>
						<td class="font cr2">價格:</td>
						<td><input type="TEXT" name="price" size="45" class="font"
							value="<%=(food_itemsVO == null) ? "" : food_itemsVO.getPrice()%>" /></td>
					</tr>

					<jsp:useBean id="speciesSrv" scope="page"
						class="com.species.model.SpeciesService" />
					<tr>
						<td class="font cr1">餐色種類:<font color=red><b>*</b></font></td>
						<td><select size="1" name="species_id" class="font">
								<c:forEach var="speciesVO" items="${speciesSrv.all}">
									<option value="${speciesVO.species_id}"
										${(food_itemsVO.species_id==speciesVO.species_id)? 'selected':'' }>${speciesVO.species_name}
								</c:forEach>
						</select></td>
					</tr>
				</table>
			</div>
			
			<div class="row">
				<div class="col-md-12"><br><br></div>
				<div class="col-md-4"></div>
				<div class="col-md-2">
					<input type="hidden" name="action" value="insertmenu"> <input
						class="btn-success" type="submit" value="送出新增">
				</div>
			</div>
		</FORM>
	</div>
</body>

</html>
