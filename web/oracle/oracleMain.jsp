<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 1/5/2017
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="jquery.treeview.css">
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/jquery.treeview.js"></script>

</head>
<body>
<div>
    <ul id="tree" class="filetree">
        <li><span class="folder">TABLE</span>
            <ul id="tables">
                <c:forEach items="${tables}" var="table">
                    <li><a href="executeSqlOracleSqlAction?table=${table}" target="resultFrame">${table}</a></li>
                </c:forEach>
            </ul>
        </li>
        <li><span class="folder">VIEW</span>
            <ul id="views">
                <c:forEach items="${views}" var="view">
                    <li><a href="executeSqlOracleSqlAction?sql=select *from ${view}" target="resultFrame">${view}</a></li>
                </c:forEach>
            </ul>
        </li>
        <li><span class="folder">SEQUENCE</span>
            <ul id="sequences">
                <c:forEach items="${sequences}" var="sequence">
                    <li>${sequence}</li>
                </c:forEach>
            </ul>
        </li>
        <li><span class="folder">INDEX</span>
            <ul id="index">
                <c:forEach items="${indexs}" var="index">
                    <li>${index}</li>
                </c:forEach>
            </ul>
        </li>
    </ul>
</div>
<script type="text/javascript">
    $("#tree").treeview();
</script>
<div style="position: absolute;left: 300px;top: 20px;width: 800px;height: 270px">
    <iframe src="oracle/oracleSqlPage.jsp" style="width: 800px;height:  250px"></iframe>
</div>
<div style="position: absolute;left: 300px;top:300px;">
    <iframe style="width: 800px;height: 400px" name="resultFrame"></iframe>
</div>
</body>
</html>
