<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>映画館管理画面</title>
    </head>
    <body>
        <h1>映画館管理画面</h1>
    <!--ーーーーーーーーーーーーーーー追加ーーーーーーーーーーーーーーー-->
        <label>
        <form method='post' action='theateraddservlet'><!--theateraddservlet-->
            <input type='text' name='add'>
            <!--ボタン-->
            <input type='submit' value='追加'><br>
        </form>
        </label>
        <br>

    <!--ーーーーーーーーーーーーーーー削除ーーーーーーーーーーーーーーー-->
        
        <c:forEach var="theaterlist" items="${theaterlist}">
            <label>
            <form method='post' action='theaterdeleteservlet'><!--theaterdeleteservlet-->
                <p style="display:inline;">${theaterlist.name}</p>
                <input type='hidden' name='delete' value='${theaterlist.name}'>
                <input type='submit' value='削除'>
            </form>
            </label>
        </c:forEach>
        <br>
        
    <!--ーーーーーーーーーーーーーーー変更ーーーーーーーーーーーーーーー-->
        <label>
        <form method='post' action='theaterchangeservlet'><!--theaterchangeservlet-->
            変更前
            <select name="beforechange">
                <c:forEach var="theaterlist" items="${theaterlist}">
                    <option value='${theaterlist.name}'>${theaterlist.name}</option>
                    <br>
                </c:forEach>
            </select>
            <br>
            <br>
            変更後<input type='text' name='afterchange'>
            <!--ボタン-->
            <input type='submit' value='変更'>
        </form>
        </label>
        <!--管理者トップ-->
        <a href="kanriTop.html">管理トップへ</a>
    </body>
        
</html>

