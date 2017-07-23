<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- �����ĥ� JSTL �P EL ���� --%>

<%
    StoreService storeSrv = new StoreService();
    List<StoreVO> list = storeSrv.getAll();
    pageContext.setAttribute("list",list);
%>

 <jsp:useBean id="styleSrv" scope="page" class="com.style.model.StyleService" />  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�Ҧ��\�U��� - listAllStore.jsp</title>
</head>
<body bgcolor='white'>
<table border='1' cellpadding='5' cellspacing='0' width='800' >
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td><h3>�Ҧ���� - ListAllStore.jsp</h3>
		          <a href="select_page.jsp"><img src="images/huyna13.jpg"  border="0">�^����</a></td></tr></table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='1160'>
	<tr>
		<th>�\�U�s��</th>
		<th>�\�U�W��</th>
		<th>�s���q��</th>
		<th>��~�a�}</th>
		<th>���}</th>
		<th>�~�e�O</th>
		<th>�̧C�~�e���B</th>
		<th>��~�ɶ�</th>
		<th>�\�U����</th>
		<th>�ק�</th>
		<th>�R��</th>
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
	                                                                       �i${styleVO.style_name}�j
                             </c:if>
                      </c:forEach> 
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="store_id" value="${storeVO.store_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="store_id" value="${storeVO.store_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
