<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>
			��f�X�P�W���[���Ǘ�
		</title>
    </head>
    <body>
		<h1>��f�X�P�W���[���Ǘ�</h1>
<!--�[�[�[�[�[�[�[�[�f��ٖ��ƃ^�C�g���Ō�������f�X�P�W���[���̏����擾����[�[�[�[�[�[�[�[-->
        <form method='post' action='schedulesearchservlet'><!--ScheduleSearchServlet��-->
			�f�@��@�ف@<input type="text" size="20" name="theater_name" value="${theater_name}"><br>
			�f��^�C�g��<input type="text" size="20" name="title" value="${title}">
			<input type='submit' value='����'>
		</form>
<!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�X�P�W���[���[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
<!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[���[�v�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[-->
		<c:forEach var="schedulelist" items="${schedulelist}">
			<form method='post' action='scheduledeleteservlet'><!--ScheduleDeleteServlet��-->
				<div id="movie_Details">
					<div id="movie_Details_Minute">
						<!--�N/��/��-->
						<input type="text" size="20" name="release_period" style="width: 70px;" value="${schedulelist.release_period}"><br>
						<!--�J�n����-->
						<input type="text" size="20" name="start_time" style="width: 40px;" value="${schedulelist.start_time}">
						~<!--�I������-->
						<input type="text" size="20" name="end_time" style="width: 40px;" value="${schedulelist.end_time}"><br>
						<!--�V�A�^�[�ԍ�-->
						<input type="text" size="20" name="theater" style="width: 80px;" value="${schedulelist.theater}"><br>
						<!--��ӂ̔ԍ��@�����Ȃ�����-->
						<input type="text" size="20" name="schedule_detail_id" style="width: 25px;" value="${schedulelist.schedule_detail_id}">
						<!--�f��ف@�^�C�g���@�����Ȃ�����-->
						<input type="hidden" size="20" name="theater_name" value="${theater_name}">
						<input type="hidden" size="20" name="title" value="${title}">
					</div>
					<input type='submit' value='�ύX' formaction='schedulechangeservlet'>�@<input type='submit' value='�폜'>
				 </div>
			</form>
			<form method='post' action='schedulechangeservlet' id="schedulechangeservlet"></form>
		</c:forEach>
<!--�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�ǉ��p�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[--><br><p>�[�[�[�ǉ��p�[�[�[�[�[�[</p>
		<form method='post' action='scheduleaddservlet'><!--ScheduleAddServlet��-->
			<div id="movie_Details">
				<div id="movie_Details_Minute">
					<!--�N/��/�� ����:���� �J�n����-->
					<input type="text" size="20" name="start_time" style="width: 110px;" value="2019/11/22 11:00">
					~<!--�N/��/�� ����:���� �I������-->
					<input type="text" size="20" name="end_time" style="width: 110px;" value="2019/11/22 13:00"><br>
					<!--�V�A�^�[�ԍ�-->
					<input type="text" size="20" name="theater" style="width: 90px;" value="�V�A�^�[2"><br>
					<!--�f��ف@�����Ȃ�����-->
					<input type="hidden" size="20" name="theater_name" value="${theater_name}">
					<!--�f��^�C�g���@�����Ȃ�����-->
					<input type="hidden" name="title" value="${title}">
				</div>
				<input type='submit' value='�ǉ�'>
			</div>
		</form>

		<a href="kanriTop.html">�Ǘ��g�b�v��</a>
        <a href="kanrimovie">�f��Ǘ���ʂ�</a>
    </body>
</html>

