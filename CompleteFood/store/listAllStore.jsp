<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
    StoreService storeSrv = new StoreService();
    List<StoreVO> list = storeSrv.getAll();
    pageContext.setAttribute("list",list);
%>

 <jsp:useBean id="styleSrv" scope="page" class="com.style.model.StyleService" />  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有餐廳資料 - listAllStore.jsp</title>
</head>
<body bgcolor='white'>
<table border='1' cellpadding='5' cellspacing='0' width='800' >
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td><h3>所有資料 - ListAllStore.jsp</h3>
		          <a href="select_page.jsp"><img src="images/huyna13.jpg"  border="0">回首頁</a></td></tr></table>

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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="storeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
		          <c:forEach var="styleVO" items="${styleSrv.all}">
                             <c:if test="${storeVO.style_id==styleVO.style_id}">
	                                                                       【${styleVO.style_name}】
                             </c:if>
                      </c:forEach> 
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="store_id" value="${storeVO.store_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="store_id" value="${storeVO.store_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
