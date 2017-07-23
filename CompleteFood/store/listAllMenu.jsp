<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.food_items.model.*"%>
<%@ page import="com.species.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<jsp:useBean id="speciesSrv" scope="page"
	class="com.species.model.SpeciesService" />
<jsp:useBean id="storeSrv" scope="page"
	class="com.store.model.StoreService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
	$(function() {
		$(".submitBtn").click(function(e) {
			var confirmMsg = confirm("確認刪除此菜色?");
			if (confirmMsg == false) {
				e.preventDefault();
			}
		});
	});
</script>
<style>
.font1{
font-style: italic;
color: MediumOrchid;
text-align:center;
}
</style>

<title>所有菜單資料</title>
</head>
<body style="background-color:Wheat">
	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<table border='1' cellpadding='5' cellspacing='0' width='800'>
				<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
					<td><h3>所有菜單</h3> <a href="restaurant.jsp"><img
							src="images/huyna13.jpg" border="0">回首頁</a></td>
				</tr>
			</table>
		</div>
	</div>
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
	<br>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<table border='1' bordercolor='#CCCCFF' width='1160'>
				<tr>
					<th class="font1">餐廳名稱</th>
					<th class="font1">菜名</th>
					<th class="font1">價格</th>
					<th class="font1">種類</th>

					<th class="font1">修改菜單</th>
					<th class="font1">刪除菜色</th>
				</tr>

				<c:forEach var="food_itemsVO" items="${food_itemsVO}">
					<tr align='center' valign='middle'>
						<td><c:forEach var="storeVO" items="${storeSrv.all}">
								<c:if test="${food_itemsVO.store_id==storeVO.store_id}">
	                                                                       【${storeVO.store_name}】
                             </c:if>
							</c:forEach></td>
						<td>${food_itemsVO.item_name}</td>
						<td>${food_itemsVO.price}</td>




						<td><c:forEach var="speciesVO" items="${speciesSrv.all}">
								<c:if test="${food_itemsVO.species_id==speciesVO.species_id}">
	                                                                       【${speciesVO.species_name}】
                             </c:if>
							</c:forEach></td>

						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/item.do">
								<input type="submit" class="btn btn-warning" value="修改">
								<input type="hidden" name="items_id"
									value="${food_itemsVO.items_id}"> <input type="hidden"
									name="action" value="getMenu_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/item.do">
								<input type="submit" class="btn btn-danger btn-sm submitBtn"
									value="刪除"> <input type="hidden" name="items_id"
									value="${food_itemsVO.items_id}"> <input type="hidden"
									name="action" value="delete">
							</FORM>
						</td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<form class="row form-columned" role="form">				
					<a href='addMenu.jsp' class="btn-primary btn-sm"> 新增菜單 </a>
			</form>
		</div>
	</div>
</body>
</html>
