<%@page pageEncoding="windows-31j"
    contentType="text/html;charset=windows-31j"%>


<%--JSTL 1.1.2 core ???????--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
    <head>
        <meta charset="windows-31j">
        <title>seatNO</title>
    </head>
    <body>
        予約内容>>
        
        

        
        <form action="ReservationServlet" method="GET">
            <c:forEach var="seat_id" items="${seat_id}">
                ${seat_id.h_seat_no}-----------------------------------------------------------------
                <input type="hidden" name="seat_id" value=${seat_id.seat_number_id}>
                <br>
                <input type="hidden" name="seat_number" value="${requestScope.sn}"> 
                <h2>券種</h2>
                <div>
                    <!-- pay払いまたはそれ以外でネットで支払いをした時のフラグを作る -->
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Infant">幼児<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Schoolchild">小学生<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Middle_school_students">中学生<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="High_school_student">高校生<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="adult">大人(大学生以上)<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Senior">シニア<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="disabilities">障がい者
                    
                </div>
                <br>
            </c:forEach>
            <p>-----------------------------------------------------------------</p>
            <h2>支払い方法</h2>
            <input type="radio" name="payment" value="pay">pay<br>
            <input type="radio" name="payment" value="money">現金<br>
            <input type="submit" value="予約確認"><br>
        </form>
    </body>
</html>