package Main;

public class Date {
    private int day;
    private int month;
    private int year;
    private final static String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final static int[] daysPerMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void advanceDate(){
        if(day == daysInMonth(month, year)){
            day = 1;
            month += 1;
            if(month == 13){
                month = 1;
                year += 1;
            }
        } else {
            day += 1;
        }
    }

    public void advanceDate(int days){
        for(int i = 0; i < days; i++){
            advanceDate();
        }
    }

    public static String monthToString(int month){
        month = month % 12;
        return months[month - 1];
    }

    public static int monthNum(String month) throws Exception{
        for(int i = 0; i < 12; i++){
            if(months[i].substring(0, 3).toLowerCase() == month.substring(0, 3).toLowerCase()){
                return i + 1;
            }
        }
        throw new RuntimeException("Invalid Month");
    }

    public static boolean isLeapYear(int year){
        if(year % 400 == 0){
            return true;
        }
        if(year % 100 == 0){
            return false;
        }
        if(year % 4 == 0){
            return true;
        }
        return false;
    }

    public static int daysInMonth(String month, int year) throws Exception{
        int m = monthNum(month);
        return daysInMonth(m, year);
    }

    public static int daysInMonth(int month, int year){
        if (month == 2 && isLeapYear(year)){
            return 29;
        }
        return daysPerMonth[month - 1];
    }

    public int getYear(){
        return year;
    }

    public int getMonthNum(){
        return month;
    }

    public String getMonth(){
        return monthToString(month);
    }

    public String getMonthShort(){
        return getMonth().substring(0, 3);
    }

    public int getDay(){
        return day;
    }
}
