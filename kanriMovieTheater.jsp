<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>�f��يǗ����</title>
    </head>
    <body>
        <h1>�f��يǗ����</h1>
    <!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�ǉ��[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
        <label>
        <form method='post' action='theateraddservlet'><!--theateraddservlet-->
            <input type='text' name='add'>
            <!--�{�^��-->
            <input type='submit' value='�ǉ�'><br>
        </form>
        </label>
        <br>

    <!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�폜�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
        
        <c:forEach var="theaterlist" items="${theaterlist}">
            <label>
            <form method='post' action='theaterdeleteservlet'><!--theaterdeleteservlet-->
                <p style="display:inline;">${theaterlist.name}</p>
                <input type='hidden' name='delete' value='${theaterlist.name}'>
                <input type='submit' value='�폜'>
            </form>
            </label>
        </c:forEach>
        <br>
        
    <!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�ύX�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
        <label>
        <form method='post' action='theaterchangeservlet'><!--theaterchangeservlet-->
            �ύX�O
            <select name="beforechange">
                <c:forEach var="theaterlist" items="${theaterlist}">
                    <option value='${theaterlist.name}'>${theaterlist.name}</option>
                    <br>
                </c:forEach>
            </select>
            <br>
            <br>
            �ύX��<input type='text' name='afterchange'>
            <!--�{�^��-->
            <input type='submit' value='�ύX'>
        </form>
        </label>
        <!--�Ǘ��҃g�b�v-->
        <a href="kanriTop.html">�Ǘ��g�b�v��</a>
    </body>
        
</html>

