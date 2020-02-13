public class MyPageBean{

    private int seat_number_id;
    private String seat_number;
    private int schedule_detail_id;
    private int schedule_id;
    private String start_time;
    private String end_time;
    private String theater;

    public int getSeat_number_id() {
        return seat_number_id;
    }
    public void setSeat_number_id(int seat_number_id) {
        this.seat_number_id = seat_number_id;
    }

    public String getSeat_number() {
        return seat_number;
    }
    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public int getSchedule_detail_id() {
        return schedule_detail_id;
    }
    public void setSchedule_detail_id(int schedule_detail_id) {
        this.schedule_detail_id = schedule_detail_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }
    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }


    public String getStart_time() {
        return start_time;
    }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    
    public String getEnd_time() {
        return end_time;
    }
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }


    public String getTheater() {
        return theater;
    }
    public void setTheater(String theater) {
        this.theater = theater;
    }

}