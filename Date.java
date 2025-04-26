/**
 * Date class that keeps track of a specific date.
 * 
 * @author Kushagra Pant
 * @since 01/27/2025
 */
public class Date {
    private int day;
    private int month;
    private int year;
    private static boolean shortForm = false;
    private final static String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final static int[] daysPerMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Instantiates a Date object using a month in number form
     * @param day - The day
     * @param month - The month
     * @param year - The year
     */
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Instantiates a Date object using a String month
     * @param day - The day
     * @param month - The month
     * @param year - The year
     */
    public Date(int day, String month, int year) throws Exception{
        this.day = day;
        this.month = Date.monthNum(month);
        this.year = year;
    }

    /**
     * Instantiates a date object using another date object (Copy Constructor)
     * @param date - The date object
     */
    public Date(Date date){
        day = date.day;
        month = date.month;
        year = date.year;
    }

    /**
     * Advances the date by 1 day
     */
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

    /**
     * Advances the date by a custom number of days
     * @param days - Number of days
     */
    public void advanceDate(int days){
        for(int i = 0; i < days; i++){
            advanceDate();
        }
    }

    /**
     * Advances the date by a certain amount of a time unit. Seconds, Minutes, and Hours will be less precise
     * 
     * @param amount - The amount the date should advance by
     * @param timeUnit - The unit of time that the amount is measured in
     * @throws Exception
     */
    public void advanceDate(int amount, TimeUnit timeUnit) throws Exception{
        if(timeUnit == TimeUnit.MONTH){
            month += amount % 12;
            year += amount / 12;
            return;
        }
        if(timeUnit == TimeUnit.YEAR){
            year += amount;
            return;
        }
        if(timeUnit == TimeUnit.DAY){
            advanceDate(amount);
            return;
        }
        advanceDate(Time.convertToSeconds(amount, timeUnit) / 86400);
    }

    /**
     * Converts a month in number form to string form. Form depends on the class settings. 
     * @param month - the month in number form
     * @return - the month in string form
     */
    public static String monthToString(int month){
        month = month % 12;
        if(shortForm){
            return months[month - 1].substring(0, 3);   
        }
        return months[month - 1];
    }

    /**
     * Converts a month in String form (either short or long form) to number form.
     * @param month - Month in string form
     * @return - Month in number form
     * @throws Exception
     */
    public static int monthNum(String month) throws Exception{
        for(int i = 0; i < 12; i++){
            if(months[i].substring(0, 3).toLowerCase() == month.substring(0, 3).toLowerCase()){
                return i + 1;
            }
        }
        throw new RuntimeException("Invalid Month");
    }

    /**
     * Identifies if a year is a leap year, i.e. it has 366 days instead of the standard 365.
     * @param year - The year in question
     * @return - True if the year is a leap year
     */
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

    /**
     * Overrides the Object toString method 
     * @return - The date formatted in a user-friendly way
     */
    public String toString(){
        return monthToString(month) + " " + day + ", " + year;
    }

    /**
     * Determines the number of days in any month in String form, depending on the year
     * @param month - The month 
     * @param year - The year
     * @return - The number of days in the month
     * @throws Exception
     */
    public static int daysInMonth(String month, int year) throws Exception{
        int m = monthNum(month);
        return daysInMonth(m, year);
    }

    /**
     * Determines the days in the current month
     * @return - the number of days
     */
    public int daysInMonth(){
        return daysInMonth(month, year);
    }

    /**
     * Determins the number of days in any month in number form, depending on the year
     * @param month - the month
     * @param year - the year
     * @return - the number of days in the month
     */
    public static int daysInMonth(int month, int year){
        if (month == 2 && isLeapYear(year)){
            return 29;
        }
        return daysPerMonth[month - 1];
    }

    /**
     * Toggles the String Format for Months
     * @return if short form is enabled
     */
    public static boolean toggleForm(){
        return (shortForm = !shortForm);
    }

    /**
     * Retrieves the current year
     * @return the current year
     */
    public int getYear(){
        return year;
    }

    /**
     * Retrieves the current month in number form
     * @return - the current month
     */
    public int getMonthNum(){
        return month;
    }

    /**
     * Retrieves the current month in String form, with its form depending on if short form is toggled or not
     * @return - the current month
     */
    public String getMonth(){
        if(shortForm){
            return getMonth().substring(0, 3);
        }
        return monthToString(month);
    }

    /**
     * Retrieves the current day
     * @return the current day
     */
    public int getDay(){
        return day;
    }
}
