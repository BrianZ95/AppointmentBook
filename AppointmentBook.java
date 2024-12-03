import javax.xml.stream.events.StartDocument;

public class AppointmentBook {
    private boolean[][] schedule;

    /**
     * Constructor for AppointmentBook
     */
    public AppointmentBook(boolean[][] schedule) {
        this.schedule = schedule;
    }

    /**
     * Returns true if a minute in a period is free for an appointment, false otherwise.
     * Preconditions: 1 <= period <= 8; 0 <= minute <= 59
     */
    private boolean isMinuteFree(int period, int minute) {
        return schedule[period - 1][minute];
    }

    /**
     * Marks the block of minutes that starts at startMinute in period and
     * is duration minutes long as reserved for an appointment.
     * Preconditions: 1 <= period <= 8; 0 <= startMinute <= 59;
     * 1 <= duration <= 60
     */
    private void reserveBlock(int period, int startMinute, int duration) {
        for (int i = startMinute; i < startMinute + duration; i++) {
            schedule[period - 1][i] = false;
        }
    }

    /**
     * Searches for the first block of duration free minutes during period.
     * Returns the first minute in the block if such a block is found or
     * returns -1 if no such block is found.
     * Preconditions: 1 <= period <= 8; 1 <= duration <= 60
     */
    public int findFreeBlock(int period, int duration) {
        int block = 0;

        for (int i = 0; i <= 60 - duration; i++) { // Ensure no out-of-bounds access
            if (isMinuteFree(period, i)) {
                block++;
                if (block == duration) {
                    return i - duration + 1;
                }
            } else {
                block = 0;
            }
        }
        return -1;
    }

    /**
     * Searches periods from startPeriod to endPeriod for a block of free minutes.
     * Reserves the block if found and returns true, otherwise returns false.
     * Preconditions: 1 <= startPeriod <= endPeriod <= 8; 1 <= duration <= 60
     */
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        for (int i = startPeriod; i <= endPeriod; i++) {
            int freeBlock = findFreeBlock(i, duration);
            if (freeBlock >= 0) {
                reserveBlock(i, freeBlock, duration);
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the availability of a given period.
     */
    public void printPeriod(int period) {
        for (int i = 0; i < schedule[period - 1].length; i++) {
            System.out.println("Minute " + i + ": " + (schedule[period - 1][i] ? "Free" : "Reserved"));
        }
    }

    /**
     * Provides a string representation of the schedule
