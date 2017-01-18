package edu.kit.informatik.student_portal.professorial_chair;

import java.util.HashSet;
import java.util.Set;
import edu.kit.informatik.student_portal.user.Professor;

public final class ProfessorialChair implements Comparable<ProfessorialChair> {
    private final String name;
    private final Set<Professor> professors;
    
    /**
     * 
     * @param name
     * @throws IllegalArgumentException
     */
    public ProfessorialChair(final String name) throws IllegalArgumentException {
        if (!name.matches("\\p{javaLowerCase}*"))
            //TODO add exception message
            throw new IllegalArgumentException();
        this.name = name;
        this.professors = new HashSet<Professor>();
    }
    
    /**
     * 
     * @param professor 
     */
    public void addProfessor(final Professor professor) {
        if (!professors.add(professor))
            throw new IllegalArgumentException();
    }

    @Override
    public int compareTo(ProfessorialChair o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
