package edu.kit.informatik.calendar;

/**
 * 
 * @author JoseNote
 * @version 1.0, 2016/11/26
 */
public class Appointment {
    private String name;
    private DateTime from;
    private DateTime to;

    /**
     * Initializes a new object instance with values for all its attributes
     * @param name The value for {@link #name}
     * @param from A {@linkplain DateTime} value for {@link #from}
     * @param to A {@linkplain DateTime} value for {@link #to}
     */
    public Appointment(String name, DateTime from, DateTime to) {
        this.name  =  name;
        this.from = from;
        this.to = to;
    }

    /**
     * Initializes a new object instance with values for all its attributes
     * @param name The value for {@link #name}
     * @param from A {@linkplain DateTime} value for {@link #from}
     * @param duration A {@linkplain Time} value to calculate {@link #from} from the parameter {@code from}
     */
    public Appointment(String name, DateTime from, Time duration) {
        this.name = name;
        this.from = from;
        to = from.plus(duration);
    }

    //region Encapsulation A.5 

    /**
     * Gets the value of {@link #name}
     * @return A string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of {@link #name}
     * @param name The value for {@link #name}
     */
    public void setName(String name) {
        this.name  =  name;
    }

    /**
     * Gets the value of {@link #from}
     * @return A {@linkplain DateTime} instance
     */
    public DateTime getFrom() {
        return from;
    }

    /**
     * Gets the value of {@link #to}
     * @return A {@linkplain DateTime} instance
     */
    public DateTime getTo() {
        return to;
    }

    /**
     * Sets the value of {@link #to}
     * @param to The value for {@link #to}
     */
    public void setTo(DateTime to) {
        this.to  =  to;
    }

    //endregion

    //region Text representation A.6

    /**
     * Generates the string representation of the object
     * @return The string representation of the object
     */
    public String toString() {
        return name.concat("").concat(from.toString()).concat("").concat(to.toString());
    }

    //endregion
}
