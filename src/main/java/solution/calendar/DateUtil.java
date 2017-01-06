
package main.java.solution.calendar;

/**
 * Utility class for date classes.
 * 
 * @author  Tobias Bachert
 * @version 1.03, 2016/11/15
 */
final class DateUtil {
    
    private DateUtil() {
        ////
    }
    
    /**
     * Appends the specified value to the specified string builder, padded with leading zeroes to at least {@code
     * digits} length.
     * 
     * <p>If {@code value} is negative, then an ASCII minus sign '-' ('\u002D') is appended in front of the padded
     * absolute value.
     * 
     * @param  sb the string builder to append to
     * @param  value the value to append
     * @param  digits the count of digits to append
     * @return a reference to {@code sb}
     */
    public static StringBuilder append(
            final StringBuilder sb,
            final int value,
            final int digits) {
        ////
        final int start = value < 0 ? sb.length() + 1 : sb.length();
        final int count = sb.append(value).length() - start;
        if (count < digits)
            sb.insert(start, nTimes('0', digits - count));
        return sb;
    }
    
    /**
     * Returns a char sequence that contains the specified character {@code n} times.
     * 
     * <p>The type of the returned char sequence is undefined but ensured to be serializable.
     * 
     * @param  ch the character
     * @param  n the length of the returned sequence
     * @return the char sequence
     * @throws IllegalArgumentException if {@code n} is negative
     */
    private static CharSequence nTimes(
            final char ch,
            final int n) {
        ////
        return new RepeatedCharacter(ch, n);
    }
    
    /**
     * Returns the positive modulus of the specified arguments.
     * 
     * <p>The returned value will be element of the least residue system modulo {@code m}. Let {@code a} be the result
     * of {@code mod(n, m)}, then {@code a} is determined as by the formula
     * <blockquote><code>
     * a &equiv;<sub>m</sub> n &and; 0 &le; a &lt; |m|</code>
     * </blockquote>
     * 
     * <p><br>
     * Examples (taken from {@linkplain Math#floorMod(int, int)}):
     * <ul>
     *   <li>If the signs of dividend and divisor are positive, the results of {@code mod}, {@code floorMod} and the
     *       {@code %} operator are the same.<br>
     *       <ul>
     *         <li>{@code mod(+4, +3) == +1}, &nbsp; {@code floorMod(+4, +3) == +1}, &nbsp; {@code (+4 % +3) == +1}</li>
     *       </ul>
     *   </li>
     *   <li>If at least one of the arguments is negative, the results differ from {@code floorMod} and the {@code %}
     *       operator.<br>
     *       <ul>
     *         <li>{@code mod(+4, -3) == +1}, &nbsp; {@code floorMod(+4, -3) == -2}, &nbsp; {@code (+4 % -3) == +1}</li>
     *         <li>{@code mod(-4, +3) == +2}, &nbsp; {@code floorMod(-4, +3) == +2}, &nbsp; {@code (-4 % +3) == -1}</li>
     *         <li>{@code mod(-4, -3) == +2}, &nbsp; {@code floorMod(-4, -3) == -1}, &nbsp; {@code (-4 % -3) == -1}</li>
     *       </ul>
     *   </li>
     * </ul>
     * 
     * <p><br>
     * This method behaves exactly like the following code snippet (while providing better performance characteristics):
     * <blockquote><pre>
     * import static java.lang.Math.*;
     * ...
     * int mod = n % m;
     * if (mod &lt; 0)
     *     mod += abs(m);
     * return mod;</pre>
     * </blockquote>
     * 
     * <p>Note that the solution described in the <i>JavaDoc</i> of {@linkplain Math#floorMod(int, int)} is not overflow
     * safe ({@code floorMod(n, m) + abs(m)} may overflow if {@code m} is positive) and thus should not be used.
     * <span style="font-size:.7em;">(Besides that it would be significantly slower.)</span>
     * 
     * @param  n the dividend
     * @param  m the divisor
     * @return the positive modulus
     * @throws ArithmeticException if the divisor {@code m} is zero
     */
    public static int mod(
            final int n,
            final int m) {
        ////
        final int mod = n % m;
        return mod < 0 ? mod + Math.abs(m) : mod;
    }
    
    /**
     * Returns the positive modulus of the specified arguments.
     * 
     * <p>The returned value will be element of the least residue system modulo {@code m}. Let {@code a} be the result
     * of {@code mod(n, m)}, then {@code a} is determined as by the formula
     * <blockquote><code>
     * a &equiv;<sub>m</sub> n &and; 0 &le; a &lt; |m|</code>
     * </blockquote>
     * 
     * <p><br>
     * For examples, see {@linkplain #mod(int, int)}.
     * 
     * <p><br>
     * This method behaves exactly like the following code snippet (while providing better performance characteristics):
     * <blockquote><pre>
     * import static java.lang.Math.*;
     * ...
     * long mod = n % m;
     * if (mod &lt; 0)
     *     mod += abs(m);
     * return mod;
     * ...
     * return (floorMod(n, m) + abs(m)) % abs(m);</pre>
     * </blockquote>
     * 
     * <p>Note that the solution described in the <i>JavaDoc</i> of {@linkplain Math#floorMod(int, int)} is not overflow
     * safe ({@code floorMod(n, m) + abs(m)} may overflow if {@code m} is positive) and thus should not be used.
     * <span style="font-size:.7em;">(Besides that it would be significantly slower.)</span>
     * 
     * @param  n the dividend
     * @param  m the divisor
     * @return the positive modulus
     * @throws ArithmeticException if the divisor {@code m} is zero
     */
    public static long mod(
            final long n,
            final long m) {
        ////
        final long mod = n % m;
        return mod < 0 ? mod + Math.abs(m) : mod;
    }
    
    /**
     * Represents a char sequence consisting of a single repeated character.
     * 
     * @author  Tobias Bachert
     * @version 1.02, 2016/11/13
     */
    private static final class RepeatedCharacter implements CharSequence {
        
        private final char ch;
        private final int length;
        
        /**
         * Constructs a {@code RepeatedCharacter}.
         * 
         * @param ch the character to repeat
         * @param length the count of repetitions
         */
        RepeatedCharacter(
                final char ch,
                final int length) {
            ////
            this.ch = ch;
            this.length = length;
        }
        
        @Override
        public int length() {
            ////
            return length;
        }
        
        @Override
        public char charAt(
                final int index) {
            ////
            return ch;
        }
        
        @Override
        public CharSequence subSequence(
                final int start,
                final int end) {
            ////
            return nTimes(ch, end - start);
        }
        
        @Override
        public String toString() {
            ////
            return new StringBuilder(length).append(this).toString();
        }
    }
}
