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
        �\����e>>
        
        

        
        <form action="ReservationServlet" method="GET">
            <c:forEach var="seat_id" items="${seat_id}">
                ${seat_id.h_seat_no}-----------------------------------------------------------------
                <input type="hidden" name="seat_id" value=${seat_id.seat_number_id}>
                <br>
                <input type="hidden" name="seat_number" value="${requestScope.sn}"> 
                <h2>����</h2>
                <div>
                    <!-- pay�����܂��͂���ȊO�Ńl�b�g�Ŏx�������������̃t���O����� -->
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Infant">�c��<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Schoolchild">���w��<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Middle_school_students">���w��<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="High_school_student">���Z��<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="adult">��l(��w���ȏ�)<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="Senior">�V�j�A<br>
                    <input type="radio" name="denomination${seat_id.seat_number_id}" value="disabilities">�Ⴊ����
                    
                </div>
                <br>
            </c:forEach>
            <p>-----------------------------------------------------------------</p>
            <h2>�x�������@</h2>
            <input type="radio" name="payment" value="pay">pay<br>
            <input type="radio" name="payment" value="money">����<br>
            <input type="submit" value="�\��m�F"><br>
        </form>
    </body>
</html>