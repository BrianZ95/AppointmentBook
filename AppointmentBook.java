import javax.xml.stream.events.StartDocument;

public class AppointmentBook {
    private boolean[][] schedule;
    /**
     * Returns true if minute in period is available for an appointment and returns
     * false otherwise
     * Preconditions: 1 <= period <= 8; 0 <= minute <= 59
     */

     public AppointmentBook(boolean[][] schedule)
     {
        this.schedule = schedule;
     }

    private boolean isMinuteFree(int period, int minute) {
        return schedule[period-1][minute];
        /* implementation not shown */ }

    /**
     * Marks the block of minutes that starts at startMinute in period and
     * is duration minutes long as reserved for an appointment
     * Preconditions: 1 <= period <= 8; 0 <= startMinute <= 59;
     * 1 <= duration <= 60
     */
    public void printPeriod(int period)
    {
        for(int i = 0; i < schedule[period -1].length; i++)
        System.out.println(i+ " "+ schedule[period-1][i]);
    }
    private void reserveBlock(int period, int startMinute, int duration) {
        for(int i=startMinute; i<startMinute + duration; i++)
        schedule[period-1][i]=false;
        /* implementation not shown */ }

    /**
     * Searches for the first block of duration free minutes during period, as
     * described in
     * part (a). Returns the first minute in the block if such a block is found or
     * returns -1 if no
     * such block is found.
     * Preconditions: 1 <= period <= 8; 1 <= duration <= 60
     */
    public int findFreeBlock(int period, int duration) {
        int block =0;
        for(int i=0; i<50; i++)
        {
            if(isMinuteFree(period, i))
            {
                block++;
                if(block == duration)
                {
                    return (i - duration + 1);
                }
            }
            else 
            {
                block=0;
            }
            return -1;
        }
    }
        /* to be implemented in part (a) */ 

    /**
     * Searches periods from startPeriod to endPeriod, inclusive, for a block
     * of duration free minutes, as described in part (b). If such a block is found,
     * calls reserveBlock to reserve the block of minutes and returns true;
     * otherwise
     * returns false.
     * Preconditions: 1 <= startPeriod <= endPeriod <= 8; 1 <= duration <= 60
     */
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration){
        for(int i=startPeriod; i <= endPeriod; i++)
        int freeBlock = findFreeBlock(i, duration);
        if (freeBlock >-1)
        {
            reserveBlock(i, freeBlock, duration);
            return true;
        }
        return false;
        
     /* to be implemented in part (b) */ }

    // There may be instance variables, constructors, and methods that are not shown.

}