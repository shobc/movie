<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>�f��Ǘ�</title>
    </head>
    <body>
		<h1>�f��Ǘ�</h1>
	<!--�[�[�[�[�[�[�[�[�f��ٖ��Ō����������擾����[�[�[�[�[�[�[�[-->
		<form method='post' action='moviesearchservlet'><!--MovieSearchServlet��-->
			�f�@��@�ف@<input type="text" size="20" name="theater_name" value="${theater_name}">
			<input type='submit' value='����'>
		</form>
		<p>�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[</p>

	<!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�X�P�W���[���[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
	<!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[���[�v�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
		<c:forEach var="movielist" items="${movielist}">
			<form method='post' action='moviedeleteservlet' id="moviedeleteservlet"><!--MovieDeleteServlet��-->
				�^�C�g��<input type='text' name='title' value="${movielist.title}"><br>
				��@���@<input type='text' name='image' value="${movielist.image}"><br>
				�J�n����<input type='text' name="release_period" value="${movielist.release_period}"><br>
				�I������<input type='text' name="end_period" value="${movielist.end_period}"><br>
				��i�ڍ�<input type="text" size="20" name="Detailed_explanation" 
								style="width: 350px; height: 100px;" value="${movielist.detailed_explanation}"><br>
				<!--schedule_id �f��@��� �����Ȃ�����-->
				<input type='hidden' name="schedule_id" value="${movielist.schedule_id}"><br>
				<!--�f��ف@�^�C�g���@�����Ȃ�����-->
				<input type="hidden" size="20" name="theater_name" value="${theater_name}">

				<!--�[�[�[�[�[�[�[�[�[�[�f���ύX����[�[�[�[�[�[�[�[�[�[-->
				<input type='submit' value='�ύX' formaction='moviechangeservlet'>

				<!--�[�[�[�[�[�[�[�[�[�[�f����폜����[�[�[�[�[�[�[�[�[�[-->
				<input type='submit' value='�폜' formaction="moviedeleteservlet">
				<p>�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[</p>
			</form>

			<form method='post' action='moviechangeservlet' id="moviechangeservlet">
				<!--�ύX-->
			</form>
		</c:forEach>
			
		
	
	<!--�[�[�[�[�[�[�[�[�[�[�f���ǉ�����[�[�[�[�[�[�[�[�[�[-->
        <form method='post' action='movieaddservlet'>
            <p>�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�ǉ��[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[</p>	
	        <div id="movietheater_add">
		        �f��ف@<input type="text" size="20" name="theater_name" value="${theater_name}"><br>
				�^�C�g��<input type='text' name='title'><br>
	            ��@���@<input type='text' name='image'><br>
				�J�n����<input type='text' name="release_period"><br>
				�I������<input type='text' name="end_period"><br>
	            ��i�ڍ�<input type="text" size="20" name="Detailed_explanation" style="width: 350px; height: 100px;"><br>
	            <input type='submit' value='�ǉ�'><br><br>
			</div>
		</form>

        <a href="kanriTop.html">�Ǘ��g�b�v��</a>        
    </body>
</html>

