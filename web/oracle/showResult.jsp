<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 1/7/2017
  Time: 6:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        function update(x,y){
            var mm=document.getElementById(x+"s"+y);
            $.ajax({url:'updateOracleAjaxAction',data:'x='+x+'&y='+y+'&value='+mm.value,type:'post',dataType:'text',success:function (result) {
                
            }});
        }
    </script>
</head>
<body>
<table>
    <c:forEach items="${myResultSet}" var="array" varStatus="cc">
        <tr>
            <c:forEach items="${array}" var="value" varStatus="bb">
                <c:if test="${cc.index>1}">
                    <c:if test="${canUpdate}">
                        <td><input type="text" value="${value}" onchange="update(${cc.index},${bb.index})" id="${cc.index}s${bb.index}"></td>
                    </c:if>
                    <c:if test="${canUpdate==false}">
                        <td>${value}</td>
                    </c:if>
                </c:if>
                <c:if test="${cc.index<2}">
                    <td>${value}</td>
                </c:if>

            </c:forEach>
        </tr>
    </c:forEach>
</table>
<form action="insertOracleAjaxAction" method="post">
    <table>
<c:if test="${canUpdate}">
    <c:forEach items="${myResultSet}" var="array" varStatus="cc">
        <c:if test="${cc.index<1}">
            <tr>
                <c:forEach items="${array}" var="value" varStatus="bb">
                    <td>
                        <input type="text" id="in${bb.index}" name="insert">
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td><input type="submit" value="insert"></td>
            </tr>
        </c:if>
    </c:forEach>
</c:if>
    </table>
</form>
</body>
</html>
