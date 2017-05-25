<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 1/5/2017
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function copySql() {
            var ab=window.getSelection().toString();
            var aa=document.getElementById("sqlLine");
            aa.value=ab;
        }
        function sumsql() {
            var aa=document.getElementById("sqlLine");
            var bb=document.getElementById("sqlArea");
            var cc=document.getElementById("sql");
            if(aa.value==""){
                cc.value=bb.value;
            }else {
                cc.value=aa.value;
            }
            document.forms[0].submit();
        }
        function setSql(ss) {
            var cc=document.getElementById("sql");
            cc.value=ss;
            document.forms[0].submit();
        }
        function test() {

        }
    </script>
</head>
<body>
<div>

    <form id="sqlAreaForm" action="executeSqlOracleSqlAction" target="resultFrame">
        <input type="button" value="GO" onclick="sumsql()">
        <input type="button" value="commit" onclick="setSql('commit')">
        <input type="button" value="rollback" onclick="setSql('rollback')">
        <input type="checkbox" value="auto" name="autoSub">AutoCommit
        <input type="button" value="test" onclick="test()">
        <textarea id="sqlArea" name="sqlArea" style="width: 780px;height: 300px" onmouseup="copySql()"></textarea>
        <input id="sqlLine" name="sqlLine" type="hidden">
        <input id="table" name="table" type="hidden" value="zz1265_null">
        <input id="sql" name="sql" type="hidden">
    </form>
</div>
</body>
</html>
