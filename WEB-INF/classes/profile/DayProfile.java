package profile;

import java.util.ArrayList;

public class DayProfile{
	private String movie_title;
	private String schedule_detail_id;
	private String detailed_explanation;
	private ArrayList day = new ArrayList();

	public String getTitle(){
		return movie_title;
	}
	public void setTitle(String movie_title){
		this.movie_title=movie_title;
	}
	
	public String getDetailed_explanation(){
		return detailed_explanation;
	}
	public void setDetailed_explanation(String detailed_explanation){
		this.detailed_explanation=detailed_explanation;
	}

	public String getSchedule_detail_id(){
		return schedule_detail_id;
	}
	public void setSchedule_detail_id(String schedule_detail_id){
		this.schedule_detail_id=schedule_detail_id;
	}
	public void detailsAdd(DayDetailsProfile ddp){
		day.add(ddp);
	}
	public ArrayList getDay(){
		return day;
	}
	public void setDay(ArrayList day){
		this.day=day;
	}

}

