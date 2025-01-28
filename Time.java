package Main;

public class Time extends Date {
    private int hour;
    private int min;
    private int sec;

    public Time(int hour, int min, int sec, int day, int month, int year){
        super(day, month, year);
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public Time(int hour, int min, int sec, Date date){
        super(date);
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public Time(Time time){
        super(time.getDay(), time.getMonthNum(), time.getYear());
        this.hour = time.hour;
        this.min = time.min;
        this.sec = time.sec;
    }

    public void advanceTime(int seconds){
        sec += seconds%60;
        min += seconds/60%3600;
        hour += seconds/3600;
    }

    public void advanceTime(int amount, TimeUnit timeUnit) throws Exception{
        advanceTime(convertToSeconds(amount, timeUnit));
    }
    
    public static int convertToSeconds(int amount, TimeUnit timeUnit) throws Exception{
        switch(timeUnit){
            case SECOND:
                return amount;
            case MINUTE:
                return amount * 60;
            case HOUR:
                return amount * 3600;
            case DAY:
                return amount * 86400;
            default:
                throw new Exception("Cannot convert " + timeUnit.toString().toLowerCase() + "s to seconds as it depends on the specific " + timeUnit.toString().toLowerCase());
        }
    }

    public String toString(){
        return hour + ":" + min + ":" + sec;
    }

    public Date getDate(){
        return new Date(super.getDay(), super.getMonthNum(), super.getYear());
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return min;
    }

    public int getSecond(){
        return sec;
    }
}
