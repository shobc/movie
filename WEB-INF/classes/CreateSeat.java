import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
//Propertiesファイルを使う

//データベースの構造をチェック
//データベースを変更して仮の値を入れる

//データベースから予約された座席を呼び出してStringに入れる
//例外を調べる web.xml jsp Exceptionクラスを作成

//jspの表示方法を調べる

public class CreateSeat{
	//メインメソッド
	public static void main(String[] args) {
		// String a =CreateSeat.seatingChart("1");
		// System.out.println(a);
	}

	//席の列をあらわす文字列
	public static final char[] SEAT_ROW_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	//座席を返すメソッド
	public static String seatingChart(String key,String schedule_detail_id) {
		//戻り値で使う変数　初期化
		String seatData = null;
		
		//getSeatsメソッドのkayの中身を呼び出す
		String columnSeats = TheaterFactory.getSeats(key);	
		//配列の中身をカンマ区切りで分割
		String[] seats = columnSeats.split(",");	

		//String配列をint配列に変える
		int[] seatMax = new int[seats.length];		
		for(int i=0; i<seats.length; i++){
			seatMax[i] = Integer.parseInt(seats[i]);
		}

		//予約済みの座席番号
		String seatReservationStr = database.getReservedSeatDB(schedule_detail_id);
		//使用不可能な座席番号
		String notSelectableStr = NotSelectable.getNotSelectableSeats(key);

		//座席レイアウトを作成する
		List<Seat> layoutData = getLayout(seatMax);
		//予約データを作成する
		Set<String> reservationData = getReservationData(seatReservationStr);
		//選択不可能の座席データを作成する
		Set<String> notSelectableData = getnotSelectableData(notSelectableStr);

		//選択不可能データ用のArrayList
		ArrayList<String> notSelectableAry = new ArrayList<String>();
		//ArrayListに選択不可能データを追加
		for (Iterator<String> i = notSelectableData.iterator(); i.hasNext();) {
			String dd = (String)i.next();
			notSelectableAry.add(dd);
		}
		


		//座席に予約を引き当てする
		doReservation(layoutData, reservationData);
		//座席に予約不可能データを引き当てする
		doNotSelectable(layoutData, notSelectableData);

		//予約済のデータ格納変数
		StringBuilder stb = new StringBuilder();

		//予約済のデータ作成
		for(int i = 0 ; i < seatMax.length; i++) {
			//座席列単位の表示データを作成する
			char prefix = SEAT_ROW_CHAR[i];
			stb.append(prefix).append("列 ");
			layoutData.stream()
			.filter(seat -> seat.getSeatNo().charAt(0) == prefix)
			.forEach(seat -> stb.append(seat.print()));
			stb.append("\n");
		}

		//予約済のデータに選択不可能データ追加
		for(int x = 0;x < notSelectableAry.size();x++){
			//選択不可能な座席格納用
			String notSelectableStore = (String)notSelectableAry.get(x);
			//選択不可能な座席の数字部分を取り出し”〇列”分(二文字分)を追加
			int notSelectableNumber = Integer.parseInt(notSelectableStore.substring(1)) + 2 + Integer.parseInt(notSelectableStore.substring(1)) + Integer.parseInt(notSelectableStore.substring(1))-1;
			System.out.println("notSelectableNumber"+notSelectableNumber);
			//席の列をあらわす文字列を入れる変数の初期化
			String seatRow = "";

			for(int i = 0 ; i < seatMax.length; i++) {
				//席の列をあらわす文字列を入れる
				seatRow = String.valueOf(SEAT_ROW_CHAR[i]);
				for(int j = 0; j < seatMax[i]; j++){
					//もし、席の列をあらわす文字列seatRowと選択不可能な座席の列を表す文字列が一致しているなら
					if(seatRow.equals(notSelectableStore.substring(0,1))){
						//選択不可能な座席の数字データnotSelectableNumberの部分に”▲”を置き換える
						stb.setCharAt(notSelectableNumber,'△');
					}
				}
				//次の列に行くために４行分追加
				notSelectableNumber = notSelectableNumber + seatMax[i] + 4 + seatMax[i] + seatMax[i];
			}
		}
		//廊下の列を入れる
		ArrayList cn = new ArrayList(); 
		int row = 3;
		int addRow = row * 3; 
		for(int i = 0; i < seatMax.length; i++){
			stb.insert(addRow,"　");
			addRow = addRow + seatMax[i] * 3 + 5;
			cn.add(i+1);
		}

		//座席データ表示
		System.out.println(stb.toString());
		//座席データの改行を＜ｐ＞に置換
		seatData = (String)stb.toString().replaceAll("\n", "</p><p>").replaceAll("b", "<button>").replaceAll("d", "</button>");
		ArrayList<String> al = new ArrayList<String>();
		al.add("A");al.add("B");al.add("C");al.add("D");al.add("E");al.add("F");al.add("G");al.add("H");
		
		for(int i = 0; i < seatMax.length; i++){
			al.get(i);
			for(int j = 1; j <= seatMax[i]; j++){
				seatData = seatData.replaceFirst("<button>", "<button id='"+al.get(i)+Integer.toString(j)+"' onclick='getId(this.id);'>");
			}
		}
		//座席データを戻す
		return seatData;
	}






	/** 指定した座席を予約済みにする * @param layoutData @param reservationData */
	private static void doReservation(List<Seat> layoutData, Set<String> reservationData) {
		for(String seatNo:reservationData) layoutData.stream()
		.filter(seat -> seat.seatNo.equals(seatNo))
		.forEach(seat -> seat.setReserved(true));
	}
	/**カンマ区切り文字列から予約データを作成する * @param seatReservationStr * @return */
	private static Set<String> getReservationData(String seatReservationStr) {
		Set<String> reservationData = new HashSet<>();
		for(String seatNo:seatReservationStr.split(","))
		reservationData.add(seatNo);
		return reservationData;
	}
	

	/** 指定した座席を選択不可能にする * @param layoutData @param notSelectableData */
	private static void doNotSelectable(List<Seat> layoutData, Set<String> notSelectableData) {
		for(String seatNo:notSelectableData) layoutData.stream()
		.filter(seat -> seat.seatNo.equals(seatNo))
		.forEach(seat -> seat.setReserved2(true));
	}
	/**カンマ区切り文字列から選択不可能データを作成する * @param notSelectableStr * @return */
	private static Set<String> getnotSelectableData(String notSelectableStr) {
		Set<String> notSelectableData = new HashSet<>();
		for(String seatNo:notSelectableStr.split(","))
		notSelectableData.add(seatNo);
		return notSelectableData;
	}
	

	/**全体座席レイアウトを作成する * @param seatMax * @return */
	private static List<Seat> getLayout(int[] seatMax) {
		List<Seat> layoutData = new ArrayList<>();
		for(int i = 0; i < seatMax.length; i++)
		layoutData.addAll(getRow(seatMax[i], SEAT_ROW_CHAR[i]));
		return layoutData;
	}
	/**座席列単位のレイアウトを作成する * @param max * @param prefix * @return */
	private static List<Seat> getRow(int max, char prefix) {
		List<Seat> rowData = new ArrayList<>();
		CreateSeat createSeat = new CreateSeat();
		for(int i = 1; i <= max; i++)
		rowData.add(createSeat.new Seat(
		Character.toString(prefix).concat(Integer.toString(i))));
		return rowData;
	}
	/*座席クラス*/
	public class Seat {
		//シート番号
		private final String seatNo;
		//予約状態
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
			return (isReserved) ? "b済d" : "b〇d" ;
		}
		public void setReserved2(boolean isReserved2) {
			this.isReserved2 = isReserved2;
		}
		public boolean isReserved2() {
			return isReserved2;
		}
		public String print2() {
			return (isReserved2) ? "▲" : "○" ;
		}
	}
}
