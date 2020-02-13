
<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core ???????--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ja">
    <head>
        <meta charset="shift_jis">
        <title>座席表</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            var count = 0;
            var seat =[];
            function getId(seatNo){
                console.log(seatNo);
                if(count<5){
                    if(seat.indexOf(seatNo) == -1){
                        $("#submit").before("<input type='hidden' name='seatNo' value="+seatNo+">");
                        $("#select").append("<p>"+seatNo+"</p>");
                        seat.push(seatNo);
                        console.log(seat);
                        count++;
                    }
                }
            }
        </script>
    </head>
    <body>
        <div>
            ${seat}
            <div id="select">

            </div>
            <form action="ConfirmServlet" method="GET">
                <input type="submit" id="submit" value="予約">
                <input type="hidden" name="schedule_detail_id" value="${schedule_detail_id}">
            </form>
        </div>
    </body>
</html>