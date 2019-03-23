package seedu.address.model.pdf;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a Pdf's address in the address book.
 * Guarantees: immutable;
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS = "Deadline can take any valid date, "
            + "and it should not be blank";
    private static final String PROPERTY_SEPARATOR_PREFIX = "/";
    private static final int PROPERTY_DATE_INDEX = 0;
    private static final int PROPERTY_ISMET_INDEX = 0;

    private final LocalDate date;
    private final boolean isMet;

    /**
     * Constructs a valid {@code Deadline}.
     *
     */
    public Deadline() {
        this.date = LocalDate.MIN;
        this.isMet = false;
    }


    /**
     * Constructs a valid {@code Deadline}. Specifically used for Json reading.
     * Interprets a deadline from its #toString() method.
     *
     */
    public Deadline(String jsonFormat) {
        this.date = LocalDate.parse(jsonFormat.split(Deadline.PROPERTY_SEPARATOR_PREFIX)[Deadline.PROPERTY_DATE_INDEX]);
        this.isMet = Boolean.parseBoolean(jsonFormat
                .split(Deadline.PROPERTY_SEPARATOR_PREFIX)[Deadline.PROPERTY_ISMET_INDEX]);
    }

    /**
     * Constructs a valid {@code Deadline}.
     *
     * @param date - Date of deadline
     * @param month - Month of Deadline
     * @param year - Year of Deadline
     * @throws DateTimeException - If Invalid input is detected (Invalid Date)
     */
    public Deadline(int date, int month, int year) throws DateTimeException {
        this.date = LocalDate.of(year, month, date);
        this.isMet = false;
    }

    /**
     * Constructs a valid {@code Deadline}.
     *
     * @param date - Date of deadline
     * @param month - Month of Deadline
     * @param year - Year of Deadline
     * @param isMet - Specifying if Deadline has been met.
     * @throws DateTimeException - If invalid input is detected
     */
    public Deadline(int date, int month, int year, boolean isMet) throws DateTimeException {
        this.date = LocalDate.of(year, month, date);
        this.isMet = isMet;
    }

    /**
     * Takes an existing deadline and parses its values while replacing its status with
     * user input.
     * @param existingDeadline - Existing Deadline whose status you want to change.
     * @param isMet - Status of the deadline
     */
    public Deadline(Deadline existingDeadline, boolean isMet) {
        this(existingDeadline.date.getDayOfMonth(), existingDeadline.date.getMonthValue(),
                existingDeadline.date.getYear(), isMet);
    }

    /**
     * Returns the LocalDate object that represents
     * a pdf's deadline.
     *
     * @return date
     */
    public LocalDate getValue() {
        return this.date;
    }

    /**
     * Calculates the number of days to a particular deadline.
     *
     * @return - Number of Days to Deadline as long.
     */
    public long getDaysToDeadline() {
        return LocalDate.now().datesUntil(this.date).count();
    }

    /**
     * Returns the state of the Deadline.
     *
     * @return true or false depending on this.isMet
     */
    public boolean isMet() {
        return this.isMet;
    }

    /**
     * Returns true or false based on the existence of a deadline.
     *
     * @return - existence of localdate.
     */
    public boolean exists() {
        return !(this.date == LocalDate.MIN);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.date.toString())
                .append(Deadline.PROPERTY_SEPARATOR_PREFIX)
                .append(this.isMet)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline); // instanceof handles nulls;
    }
}
