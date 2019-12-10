package profile;

public class MovieProfile{
	private String title;
	private String image;
	private String end_period;
	private String release_period;
	private String detailed_explanation;
	private String schedule_id;
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}

	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image=image;
	}

	public String getEnd_period(){
		return end_period;
	}
	public void setEnd_period(String end_period){
		this.end_period=end_period;
	}

	public String getRelease_period(){
		return release_period;
	}
	public void setRelease_period(String release_period){
		this.release_period=release_period;
	}

	public String getDetailed_explanation(){
		return detailed_explanation;
	}
	public void setDetailed_explanation(String detailed_explanation){
		this.detailed_explanation=detailed_explanation;
	}

	public String getSchedule_id(){
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id){
		this.schedule_id=schedule_id;
	}
}

