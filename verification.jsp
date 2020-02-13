
<%@ page pageEncoding="Windows-31J"
contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ja">
   <head>
       <meta charset="shift_jis">
       <title>映画トップページ</title>
       <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
       <script>
           
       </script>
   </head>
   <body>
        <c:forEach var="sBean" items="${sBean}">
            <div>
                <p>-----------------------------------------------------------------</p>
                券種　${sBean.denomination}
                <br>
                支払い方法　${sBean.payment}
                <br>
                ランダム　${sBean.automatic_number}
                <br>
            </div>   
        </c:forEach>
   </body>
</html>