<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>餐廳資料 - listOneStore.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>餐廳資料 - ListOneStore.jsp</h3>
		              <a href="select_page.jsp"><img src="images/huyna13.jpg"  border="0">回首頁</a></td></tr></table>

<table border='1' bordercolor='#CCCCFF' width='1160'>
	<tr>
		<th>餐廳編號</th>
		<th>餐廳名稱</th>
		<th>連絡電話</th>
		<th>營業地址</th>
		<th>網址</th>
		<th>外送費</th>
		<th>最低外送金額</th>
		<th>營業時間</th>
		<th>餐廳類型</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${storeVO.store_id}</td>
		<td>${storeVO.store_name}</td>
        <td>${storeVO.phone_number}</td>
        <td>${storeVO.shop_address}</td>
        <td>${storeVO.web_url}</td>
        <td>${storeVO.subTotal}</td>
         <td>${storeVO.mini_Price}</td>
         <td>${storeVO.deliveryOperationTime}</td>
        <td>${storeVO.style_id}
                 <jsp:useBean id="styleSrv" scope="page" class="com.style.model.StyleService" />
                  <c:forEach var="styleVO" items="${styleSrv.all}">
                         <c:if test="${storeVO.style_id==styleVO.style_id}">
	                                                      【${styleVO.style_name}】
                         </c:if>
                  </c:forEach>     
             </td>
	 </tr>
</table>

</body>
</html>
