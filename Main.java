public class Main {
    public static void main(String[] args) {
        // Initialize an 8-period schedule with 60 minutes each, all reserved (false).
        boolean[][] schedule = new boolean[8][60];

        // Create the AppointmentBook object.
        AppointmentBook appointmentBook = new AppointmentBook(schedule);

        // Print initial state of the schedule.
        System.out.println("Initial Schedule:");
        System.out.println(appointmentBook);

        // Set up specific availability (true means free).
        for (int i = 25; i < 30; i++) schedule[0][i] = true;
        for (int i = 0; i < 15; i++) schedule[1][i] = true;
        for (int i = 41; i < 60; i++) schedule[1][i] = true;

        // Print the modified schedule for periods 1 and 2.
        System.out.println("Modified Schedule:");
        System.out.println(appointmentBook);

        // Test appointment booking
        int period = 2; // Period starts at 1-based index
        System.out.println("Checking availability in period " + period);
        appointmentBook.printPeriod(period);

        // Try to make an appointment
        int startPeriod = 1, endPeriod = 3, duration = 5;
        System.out.println("Attempting to make an appointment from period " + startPeriod + " to " + endPeriod + " for " + duration + " minutes.");
        boolean appointmentMade = appointmentBook.makeAppointment(startPeriod, endPeriod, duration);
        System.out.println("Appointment made: " + appointmentMade);

        // Print schedule after attempting to make the appointment
        System.out.println("Schedule after attempting appointment:");
        System.out.println(appointmentBook);
    }
}
