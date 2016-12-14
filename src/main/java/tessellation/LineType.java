package main.java.tessellation;

/**
 * Represents all possible types of lines that can be present on
 * tiles.
 * 
 * <p>The type contains values for three colors ({@link #RED},
 * {@link #GREEN} and {@link #YELLOW}) and one value
 * that represents the absence of a line ({@link #NONE}).
 * 
 * <p>Each value has an <em>abbreviation</em> that can be
 * obtained using the {@link LineType#getAbbreviation()} method.
 */
public enum LineType {
    /** Non-existent line. */
    NONE('-'),
    /** Line with color red. */
    RED('R'),
    /** Line with color green. */
    GREEN('G'),
    /** Line with color yellow. */
    YELLOW('Y');

    private final char abbreviation;

    LineType(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the abbreviation of the line type.
     * 
     * <p>Each color is represented by the first character of its {@linkplain #name() name}
     * ({@linkplain #RED R}, {@linkplain #GREEN G} or {@linkplain #YELLOW Y}), the line type
     * {@linkplain LineType#NONE NONE} is represented by an ASCII minus sign '-' ('\u002D').
     * 
     * @return the abbreviation
     */
    public char getAbbreviation() {
        return abbreviation;
    }

    /**
     * Returns whether this is a color.
     * @return {@code true} if this is a color, {@code false} if this is {@link #NONE}
     */
    public boolean isColor() {
        return this != NONE;
    }

    /**
     * Checks if the current instance fits a given instance.
     * Two {@linkplain LineType} instances don't fit, only if they are both
     * not {@link #NONE} and different.
     * @param otherColor The other instance to fit
     * @return {@code True} if the current instance first
     */
    public boolean fitsTo(LineType otherColor) {
        boolean fits = true;

        if (this.isColor() && otherColor.isColor()) {
            //true: only when to colors are being compared, they could not match
            if (this != otherColor)fits = false;
        }
        return fits;
    }
    
    public String toString() {
        return String.valueOf(abbreviation);
    }
}