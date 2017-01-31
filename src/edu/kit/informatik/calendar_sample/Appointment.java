
package edu.kit.informatik.calendar_sample;

/**
 * Represents an appointment with a specific name.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/10/26
 */
public final class Appointment implements Comparable<Appointment> {

    private       String   name;
    private final DateTime from;
    private       DateTime to;

    /**
     * Constructs an {@code Appointment} with the specified arguments.
     * 
     * @param name the name of the appointment
     * @param from the start time
     * @param to the end time
     */
    public Appointment(
            final String   name,
            final DateTime from,
            final DateTime to) {
        ////
        this.name = name;
        this.from = from;
        this.to   = to;
    }

    /**
     * Constructs an {@code Appointment} with the specified arguments.
     * 
     * @param name the name of the appointment
     * @param from the start time
     * @param duration the duration
     */
    public Appointment(
            final String   name,
            final DateTime from,
            final Time     duration) {
        ////
        this(name, from, from.plus(duration));
    }

    @Override
    public boolean equals(
            final Object obj) {
        ////
        return obj instanceof Appointment && compareTo((Appointment) obj) == 0;
    }

    @Override
    public int hashCode() {
        ////
        return ((31 + name.hashCode())
                * 31 + from.hashCode())
                * 31 + to.hashCode();
    }

    /**
     * Returns a string representation of this appointment.
     * 
     * <p>The returned string has the format
     * <blockquote><pre>
     * (name) (from) (to)</pre>
     * </blockquote>
     */
    @Override
    public String toString() {
        ////
        return appendTo(new StringBuilder(40 + name.length())).toString();
    }

    /**
     * Appends the string representation of this to the specified string builder.
     * 
     * <p>The string representation is appended as per invoking
     * <blockquote><pre>
     * sb.append({@linkplain #toString()});</pre>
     * </blockquote>
     * 
     * @param  sb the string builder to append to
     * @return a reference to {@code sb}
     */
    /*pkg*/ StringBuilder appendTo(
            final StringBuilder sb) {
        ////
        sb   .append(name).append(' ');
        from .appendTo(sb).append(' ');
        to   .appendTo(sb);
        return sb;
    }

    //==================================================================================================================

    /**
     * Returns the name of this.
     * 
     * @return the name
     */
    public String getName() {
        ////
        return name;
    }

    /**
     * Returns the start time of this.
     * 
     * @return the start time
     */
    public DateTime getFrom() {
        ////
        return from;
    }

    /**
     * Returns the end time of this.
     * 
     * @return the end time
     */
    public DateTime getTo() {
        ////
        return to;
    }

    /**
     * Sets the name of this appointment to the specified name.
     * 
     * @param name the name
     */
    public void setName(
            final String name) {
        ////
        this.name = name;
    }

    /**
     * Sets the end time of this appointment to the specified datetime.
     * 
     * @param to the end time
     */
    public void setTo(
            final DateTime to) {
        ////
        this.to = to;
    }

    @Override
    public int compareTo(Appointment o) {
        int fromComparision = from.compareTo(o.getFrom());

        if (fromComparision < 0) 
            return -1;
        else if (fromComparision == 0) {
            int toComparision = to.compareTo(o.getTo());

            if (toComparision < 0) 
                return -1;
            else if (toComparision > 0) 
                return 1;
            else {
                int nameComparision = name.compareTo(o.getName());

                if (nameComparision < 0) 
                    return -1;
                else if (nameComparision > 0) 
                    return 1;
                else 
                    return 0;
            }
        } else {
            return 1;
        }
    }
}
