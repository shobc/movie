
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
       <div>
            <c:forEach var="result" items="${result}">
                seat_number_id = ${result.seat_number_id}
                <br>
                seat_number = ${result.seat_number}
                <br>
                schedule_detail_id = ${result.schedule_detail_id}
                <br>
                schedule_id = ${result.schedule_id}
                <br>
                start_time = ${result.start_time}
                <br>
                end_time = ${result.end_time}
                <br>
                theater = ${result.theater}
                <br><br><br>
            </c:forEach>
        </div>
   </body>
</html>