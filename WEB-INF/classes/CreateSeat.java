import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
//Properties�t�@�C�����g��

//�f�[�^�x�[�X�̍\�����`�F�b�N
//�f�[�^�x�[�X��ύX���ĉ��̒l������

//�f�[�^�x�[�X����\�񂳂ꂽ���Ȃ��Ăяo����String�ɓ����
//��O�𒲂ׂ� web.xml jsp Exception�N���X���쐬

//jsp�̕\�����@�𒲂ׂ�

public class CreateSeat{
	//���C�����\�b�h
	public static void main(String[] args) {
		// String a =CreateSeat.seatingChart("1");
		// System.out.println(a);
	}

	//�Ȃ̗������킷������
	public static final char[] SEAT_ROW_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	//���Ȃ�Ԃ����\�b�h
	public static String seatingChart(String key,String schedule_detail_id) {
		//�߂�l�Ŏg���ϐ��@������
		String seatData = null;
		
		//getSeats���\�b�h��kay�̒��g���Ăяo��
		String columnSeats = TheaterFactory.getSeats(key);	
		//�z��̒��g���J���}��؂�ŕ���
		String[] seats = columnSeats.split(",");	

		//String�z���int�z��ɕς���
		int[] seatMax = new int[seats.length];		
		for(int i=0; i<seats.length; i++){
			seatMax[i] = Integer.parseInt(seats[i]);
		}

		//�\��ς݂̍��Ȕԍ�
		String seatReservationStr = database.getReservedSeatDB(schedule_detail_id);
		//�g�p�s�\�ȍ��Ȕԍ�
		String notSelectableStr = NotSelectable.getNotSelectableSeats(key);

		//���ȃ��C�A�E�g���쐬����
		List<Seat> layoutData = getLayout(seatMax);
		//�\��f�[�^���쐬����
		Set<String> reservationData = getReservationData(seatReservationStr);
		//�I��s�\�̍��ȃf�[�^���쐬����
		Set<String> notSelectableData = getnotSelectableData(notSelectableStr);

		//�I��s�\�f�[�^�p��ArrayList
		ArrayList<String> notSelectableAry = new ArrayList<String>();
		//ArrayList�ɑI��s�\�f�[�^��ǉ�
		for (Iterator<String> i = notSelectableData.iterator(); i.hasNext();) {
			String dd = (String)i.next();
			notSelectableAry.add(dd);
		}
		


		//���Ȃɗ\����������Ă���
		doReservation(layoutData, reservationData);
		//���Ȃɗ\��s�\�f�[�^���������Ă���
		doNotSelectable(layoutData, notSelectableData);

		//�\��ς̃f�[�^�i�[�ϐ�
		StringBuilder stb = new StringBuilder();

		//�\��ς̃f�[�^�쐬
		for(int i = 0 ; i < seatMax.length; i++) {
			//���ȗ�P�ʂ̕\���f�[�^���쐬����
			char prefix = SEAT_ROW_CHAR[i];
			stb.append(prefix).append("�� ");
			layoutData.stream()
			.filter(seat -> seat.getSeatNo().charAt(0) == prefix)
			.forEach(seat -> stb.append(seat.print()));
			stb.append("\n");
		}

		//�\��ς̃f�[�^�ɑI��s�\�f�[�^�ǉ�
		for(int x = 0;x < notSelectableAry.size();x++){
			//�I��s�\�ȍ��Ȋi�[�p
			String notSelectableStore = (String)notSelectableAry.get(x);
			//�I��s�\�ȍ��Ȃ̐������������o���h�Z��h��(�񕶎���)��ǉ�
			int notSelectableNumber = Integer.parseInt(notSelectableStore.substring(1)) + 2 + Integer.parseInt(notSelectableStore.substring(1)) + Integer.parseInt(notSelectableStore.substring(1))-1;
			System.out.println("notSelectableNumber"+notSelectableNumber);
			//�Ȃ̗������킷�����������ϐ��̏�����
			String seatRow = "";

			for(int i = 0 ; i < seatMax.length; i++) {
				//�Ȃ̗������킷�����������
				seatRow = String.valueOf(SEAT_ROW_CHAR[i]);
				for(int j = 0; j < seatMax[i]; j++){
					//�����A�Ȃ̗������킷������seatRow�ƑI��s�\�ȍ��Ȃ̗��\�������񂪈�v���Ă���Ȃ�
					if(seatRow.equals(notSelectableStore.substring(0,1))){
						//�I��s�\�ȍ��Ȃ̐����f�[�^notSelectableNumber�̕����Ɂh���h��u��������
						stb.setCharAt(notSelectableNumber,'��');
					}
				}
				//���̗�ɍs�����߂ɂS�s���ǉ�
				notSelectableNumber = notSelectableNumber + seatMax[i] + 4 + seatMax[i] + seatMax[i];
			}
		}
		//�L���̗������
		ArrayList cn = new ArrayList(); 
		int row = 3;
		int addRow = row * 3; 
		for(int i = 0; i < seatMax.length; i++){
			stb.insert(addRow,"�@");
			addRow = addRow + seatMax[i] * 3 + 5;
			cn.add(i+1);
		}

		//���ȃf�[�^�\��
		System.out.println(stb.toString());
		//���ȃf�[�^�̉��s���������ɒu��
		seatData = (String)stb.toString().replaceAll("\n", "</p><p>").replaceAll("b", "<button>").replaceAll("d", "</button>");
		ArrayList<String> al = new ArrayList<String>();
		al.add("A");al.add("B");al.add("C");al.add("D");al.add("E");al.add("F");al.add("G");al.add("H");
		
		for(int i = 0; i < seatMax.length; i++){
			al.get(i);
			for(int j = 1; j <= seatMax[i]; j++){
				seatData = seatData.replaceFirst("<button>", "<button id='"+al.get(i)+Integer.toString(j)+"' onclick='getId(this.id);'>");
			}
		}
		//���ȃf�[�^��߂�
		return seatData;
	}






	/** �w�肵�����Ȃ�\��ς݂ɂ��� * @param layoutData @param reservationData */
	private static void doReservation(List<Seat> layoutData, Set<String> reservationData) {
		for(String seatNo:reservationData) layoutData.stream()
		.filter(seat -> seat.seatNo.equals(seatNo))
		.forEach(seat -> seat.setReserved(true));
	}
	/**�J���}��؂蕶���񂩂�\��f�[�^���쐬���� * @param seatReservationStr * @return */
	private static Set<String> getReservationData(String seatReservationStr) {
		Set<String> reservationData = new HashSet<>();
		for(String seatNo:seatReservationStr.split(","))
		reservationData.add(seatNo);
		return reservationData;
	}
	

	/** �w�肵�����Ȃ�I��s�\�ɂ��� * @param layoutData @param notSelectableData */
	private static void doNotSelectable(List<Seat> layoutData, Set<String> notSelectableData) {
		for(String seatNo:notSelectableData) layoutData.stream()
		.filter(seat -> seat.seatNo.equals(seatNo))
		.forEach(seat -> seat.setReserved2(true));
	}
	/**�J���}��؂蕶���񂩂�I��s�\�f�[�^���쐬���� * @param notSelectableStr * @return */
	private static Set<String> getnotSelectableData(String notSelectableStr) {
		Set<String> notSelectableData = new HashSet<>();
		for(String seatNo:notSelectableStr.split(","))
		notSelectableData.add(seatNo);
		return notSelectableData;
	}
	

	/**�S�̍��ȃ��C�A�E�g���쐬���� * @param seatMax * @return */
	private static List<Seat> getLayout(int[] seatMax) {
		List<Seat> layoutData = new ArrayList<>();
		for(int i = 0; i < seatMax.length; i++)
		layoutData.addAll(getRow(seatMax[i], SEAT_ROW_CHAR[i]));
		return layoutData;
	}
	/**���ȗ�P�ʂ̃��C�A�E�g���쐬���� * @param max * @param prefix * @return */
	private static List<Seat> getRow(int max, char prefix) {
		List<Seat> rowData = new ArrayList<>();
		CreateSeat createSeat = new CreateSeat();
		for(int i = 1; i <= max; i++)
		rowData.add(createSeat.new Seat(
		Character.toString(prefix).concat(Integer.toString(i))));
		return rowData;
	}
	/*���ȃN���X*/
	public class Seat {
		//�V�[�g�ԍ�
		private final String seatNo;
		//�\����
		private boolean isReserved;
		private boolean isReserved2;

		public Seat(final String seatNo) {
			this.seatNo = seatNo;
		}
		public String getSeatNo() {
			return seatNo;
		}
		public void setReserved(boolean isReserved) {
			this.isReserved = isReserved;
		}
		public boolean isReserved() {
			return isReserved;
		}
		public String print() {
			return (isReserved) ? "b��d" : "b�Zd" ;
		}
		public void setReserved2(boolean isReserved2) {
			this.isReserved2 = isReserved2;
		}
		public boolean isReserved2() {
			return isReserved2;
		}
		public String print2() {
			return (isReserved2) ? "��" : "��" ;
		}
	}
}
