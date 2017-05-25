<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 1/5/2017
  Time: 5:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="jquery.treeview.css">
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/jquery.treeview.js"></script>
</head>
<body>
<ul id="tree" class="filetree">
    <li><span class="folder">系统管理</span>
        <ul>
            <li><span class="folder">部门管理</span></li>
            <li><span class="folder">岗位管理</span>
                <ul>
                    <li><span class="folder">岗位添加</span></li>
                    <li><span class="folder">岗位删除</span></li>
                </ul>
            </li>
            <li><span class="folder">用户管理</span>
                <ul>
                    <li><span class="folder">添加用户</span></li>
                    <li><span class="folder">修改用户</span></li>
                </ul>
            </li>
        </ul>
    </li>
    <li><span class="folder">审批流转</span></li>
</ul>

<script type="text/javascript">
    $("#tree").treeview();
</script>
</body>
</html>
