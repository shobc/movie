package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScheduleInsert{
	
	public static void main(String[] args){
		// ScheduleInsert.insert("2019/11/22 11:00","2019/11/22 13:00","�V�A�^�[2","�Ȃ�����Ǝq������","�؉f���");
		ArrayList aa = new ArrayList();
		aa = getWeek("2020/02/07");
		for(int i = 0;i < aa.size();i++){
			System.out.println(aa.get(i));
		}
	}
	
	public static int insert(String start_time,String end_time,String theater,String title,String theater_name){
		int count = 0; //���������s�������邽�߂̕ϐ�
		try{
			//Driver�C���^�[�t�F�C�X����������N���X�����[�h����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X��Ԃ�
			Connection cn=
				DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");//���[�U�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[
			
			//�����R�~�b�g��OFF�ɂ���
			cn.setAutoCommit(false);
			
			System.out.println("�ڑ�����");
			
			//SQL����ϐ��Ɋi�[����
			String sql="insert into schedule_detail_table values("+
						"schedule_detail_number.NEXTVAL,"+
						"(SELECT schedule_id FROM schedule_table WHERE title = '"+title+"' "+
						"AND movie_theater_id = (SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')),"+
						"to_date('"+start_time+"','yyyy/mm/dd hh24:mi'),"+
						"to_date('"+end_time+"','yyyy/mm/dd hh24:mi'),'"+theater+"')" ;

			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();
			
			//SQL�����s���g�����U�N�V�������J�n�����B�����������Ԃ����
			count=st.executeUpdate(sql);
			
			System.out.println(count+"����������");
			System.out.println(theater_name+"��"+title+"��"+start_time+"��"+end_time+"��"+theater+"��ǉ�");

			//�g�����U�N�V�������R�~�b�g����
			cn.commit();
			
			//�X�e�[�g�����g���N���[�Y����
			st.close();
			
			//RDBMS����ؒf����
			cn.close();
			
			System.out.println("�ؒf����");
			System.out.println(" ");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

	//��T�Ԃ��擾���郁�\�b�h
	public static ArrayList getWeek(String date){

		//return�p��ArrayList
		ArrayList listDate = new ArrayList();
		//�擾�����f�[�^������String[]
		String[] newDate = new String[7];

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("�ڑ�����");
			
			
			//select��
			String sql="select "+
						"to_char(to_date('"+date+"','yyyy/mm/dd'),'yyyy/mm/dd'), "+
						"to_char(to_date('"+date+"','yyyy/mm/dd')+1,'yyyy/mm/dd'), "+
						"to_char(to_date('"+date+"','yyyy/mm/dd')+2,'yyyy/mm/dd'), "+
						"to_char(to_date('"+date+"','yyyy/mm/dd')+3,'yyyy/mm/dd'), "+
						"to_char(to_date('"+date+"','yyyy/mm/dd')+4,'yyyy/mm/dd'), "+
						"to_char(to_date('"+date+"','yyyy/mm/dd')+5,'yyyy/mm/dd'), "+
						"to_char(to_date('"+date+"','yyyy/mm/dd')+6,'yyyy/mm/dd') from dual";
			
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();
			
			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(sql);
			

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			rs.next();
			for(int i = 0;i < 7;i++){
				newDate[i] = rs.getString(i+1);//i��ڂ̃f�[�^���擾
				// System.out.println("newDate["+i+"]="+newDate[i]);
				listDate.add(newDate[i]);
			}

			//Oracle����ؒf����
			cn.close();

			System.out.println("�ؒf����");
			System.out.println(" ");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�N���X���Ȃ��݂����B");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
		return listDate;
		
	}
}

