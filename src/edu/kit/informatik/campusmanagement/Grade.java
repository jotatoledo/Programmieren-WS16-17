
package edu.kit.informatik.campusmanagement;

import static java.util.function.Function.identity;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * A grade of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
public final class Grade {
    
    private static final double TOLERANCE = 1e14;
    
    private static final double LOWER_BOUND = 1;
    private static final double UPPER_BOUND = 5;
    
    private static final Grade NONE = new Grade();
    
    private final double value;
    private String string;
    
    /**
     * Creates a new {@code Grade}
     * 
     * @param value the value of the grade, has to be between {@code 1} and {@code 5}
     */
    public Grade(
            final double value) {
        ////
        if (value < LOWER_BOUND || value > UPPER_BOUND) {
            throw new IllegalArgumentException("value out of bounds " + value);
        }
        
        this.value = value;
    }
    
    /**
     * Used to create the grade {@link #NONE}.
     */
    private Grade() {
        ////
        assert NONE == null;
        value = -1;
        string = "none";
    }
    
    /**
     * Returns the average grade for the provided collection.
     * 
     * @param  values a collection of grades
     * @return the average grade
     * @see    #average(Collection, Function, ToIntFunction)
     */
    public static Grade average(
            final Collection<Grade> values) {
        ////
        return average(values, identity(), grade -> 1);
    }
    
    /**
     * Returns the average grade for the provided arguments.
     * 
     * <p>If the weighting function returns non-positive values, the result of this method is undefined.
     * 
     * @param  <T> type parameter of the values
     * @param  values a collection of values
     * @param  function a function to convert a value to a grade
     * @param  weighting a function to convert a value to a weighting
     * @return the average grade
     */
    public static <T> Grade average(
            final Collection<T> values,
            final Function<T, Grade> function,
            final ToIntFunction<T> weighting) {
        ////
        return average(values.stream(), function, weighting);
    }
    
    /**
     * Returns the average grade for the provided arguments.
     * 
     * <p>If the weighting function returns non-positive values, the result of this method is undefined.
     * 
     * @param  <T> type parameter of the values
     * @param  values a stream of values
     * @param  function a function to convert a value to a grade
     * @param  weighting a function to convert a value to a weighting
     * @return the average grade
     */
    public static <T> Grade average(
            final Stream<T> values,
            final Function<T, Grade> function,
            final ToIntFunction<T> weighting) {
        ////
        Objects.requireNonNull(function);
        Objects.requireNonNull(weighting);
        
        return values.map(t -> Node.of(function.apply(t), weighting.applyAsInt(t)))
                .reduce(Node.IDENTITY, Node::add).asGrade();
    }
    
    /**
     * Returns whether {@code this} is {@link #NONE}.
     * 
     * <p>While there should be only one instance of {@link #NONE}, this returns {@code true} for all grades created by
     * the {@link #Grade()} constructor.
     * 
     * @return {@code true} if {@code this} is {@link #NONE}, {@code false} otherwise
     */
    private boolean isNone() {
        ////
        return equals(NONE);
    }
    
    @Override
    public boolean equals(
            final Object obj) {
        ////
        return obj instanceof Grade && value == ((Grade) obj).value;
    }
    
    @Override
    public int hashCode() {
        ////
        return Double.hashCode(value);
    }
    
    @Override
    public String toString() {
        ////
        if (string == null) {
            // string = String.format(Locale.ROOT, "%.2f", Math.ceil(value * 1e2) * 1e-2)
            final int rounded = (int) Math.ceil(value * 1e2);
            string = (rounded / 100) + "." + (rounded % 100 / 10) + (rounded % 10);
        }
        return string;
    }
    
    private static final strictfp class Node {
        
        static final Node IDENTITY = new Node(0, 0);
        
        private final double value;
        private final int weight;
        
        private Node(
                final double value,
                final int weight) {
            ////
            this.value  = value;
            this.weight = weight;
        }
        
        static Node of(
                final Grade grade,
                final int weight) {
            ////
            return grade.isNone() ? IDENTITY : new Node(grade.value * weight, weight);
        }
        
        Grade asGrade() {
            ////
            return weight == 0 ? NONE : new Grade(Math.round(value / weight * TOLERANCE) / TOLERANCE);
        }
        
        Node add(
                final Node other) {
            ////
            return this  == IDENTITY ? other
                 : other == IDENTITY ? this
                 : new Node(value + other.value, weight + other.weight);
        }
    }
}
