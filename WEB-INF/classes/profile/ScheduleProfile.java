package profile;

public class ScheduleProfile{
	private String theater;
	private String movie_title;
	private String release_period;
	private String start_time;
	private String end_time;
	private String schedule_detail_id;
	
	public String getTheater(){
		return theater;
	}
	public void setTheater(String theater){
		this.theater=theater;
	}

	public String getTitle(){
		return movie_title;
	}
	public void setTitle(String movie_title){
		this.movie_title=movie_title;
	}

	public String getRelease_period(){
		return release_period;
	}
	public void setRelease_period(String release_period){
		this.release_period=release_period;
	}

	public String getStart_time(){
		return start_time;
	}
	public void setStart_time(String start_time){
		this.start_time=start_time;
	}

	public String getEnd_time(){
		return end_time;
	}
	public void setEnd_time(String end_time){
		this.end_time=end_time;
	}

	public String getSchedule_detail_id(){
		return schedule_detail_id;
	}
	public void setSchedule_detail_id(String schedule_detail_id){
		this.schedule_detail_id=schedule_detail_id;
	}
}

