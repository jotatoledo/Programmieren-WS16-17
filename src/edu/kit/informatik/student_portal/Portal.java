package edu.kit.informatik.student_portal;

import edu.kit.informatik.Terminal;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 * TODO add error message on display of empty list
 */
public final class Portal {
    private final IPortalService portalService;
    
    /**
     * TODO add doc
     */
    public Portal() {
        portalService = new PortalService();
    }
    
    /**
     * TODO add doc
     * @param args TODO add doc
     */
    public static void main(String args[]) {
        Portal console = new Portal();
        
        do {
            try {
                String input = Terminal.readLine();
                if (!input.isEmpty()) {
                    if (input.startsWith(Command.QUIT.getCommandText()))
                        break;
                    if (input.startsWith(Command.RESET.getCommandText())) {
                        console = new Portal();
                        Terminal.printLine("ok");
                        continue;
                    }
                    if (input.startsWith(Command.EXAMINATION_MARKING.getCommandText())) {
                        console.examinationMarking(getParameters(input, Command.EXAMINATION_MARKING));
                        Terminal.printLine("ok");
                        continue;
                    }
                    if (input.startsWith(Command.ADD_STUDENT.getCommandText())) {
                        console.addStudent(getParameters(input, Command.ADD_STUDENT));
                        Terminal.printLine("ok");
                        continue;
                    }  
                    if (input.startsWith(Command.LIST_STUDENT.getCommandText())) {
                        console.listStudent();
                        continue;
                    }
                    if (input.startsWith(Command.SUMMARY_STUDENT.getCommandText())) {
                        console.summaryStudent(getParameters(input, Command.SUMMARY_STUDENT));
                        continue;
                    }
                    if (input.startsWith(Command.ADD_MODULE.getCommandText())) {
                        console.addModule(getParameters(input, Command.ADD_MODULE));
                        Terminal.printLine("ok");
                        continue;
                    }
                    if (input.startsWith(Command.LIST_MODULE.getCommandText())) {
                        console.listModule();
                        continue;
                    }
                    if (input.startsWith(Command.SUMMARY_MODULE.getCommandText())) {
                        console.summaryModule(getParameters(input, Command.SUMMARY_MODULE));
                        continue;
                    }
                    if (input.startsWith(Command.ADD_LECTURE.getCommandText())) {
                        console.addLecture(getParameters(input, Command.ADD_LECTURE));
                        Terminal.printLine("ok");
                        continue;
                    }
                    if (input.startsWith(Command.LIST_LECTURE.getCommandText())) {
                       console.listLecture();
                       continue;
                    }
                    if (input.startsWith(Command.SUMMARY_LECTURE.getCommandText())) {
                        console.summaryLecture(getParameters(input, Command.SUMMARY_LECTURE));
                        continue;
                    }
                    if (input.startsWith(Command.ADD_PROFESSOR.getCommandText())) {                    
                        console.addProfessor(getParameters(input, Command.ADD_PROFESSOR));
                        Terminal.printLine("ok");
                        continue;
                    }
                    if (input.startsWith(Command.LIST_PROFESSOR.getCommandText())) {
                        console.listProfessors();
                        continue;
                    }
                    if (input.startsWith(Command.SUMMARY_PROFESSOR.getCommandText())) {
                        console.summaryProfessor(getParameters(input, Command.SUMMARY_PROFESSOR));
                        continue;
                    }
                }                
                throw new IllegalArgumentException("invalid command");
            } catch (IllegalArgumentException e1) {
                Terminal.printError(e1.getMessage());
            } catch (IndexOutOfBoundsException e2) {
                Terminal.printError(e2.getMessage());
            }            
        } while(true);
    }

    private void addLecture(final String[] input) {
        portalService.addLecture(input[0], Integer.parseInt(input[1]), 
                input[2], input[3], input[4], Integer.parseInt(input[5]));
        
    }
    
    private void listLecture() {
        for (Lecture item : portalService.getLecture()) {
            Terminal.printLine(item.toString());
        }
    }

    private void summaryLecture(final String[] input) {
        Lecture lec = portalService.getLecture(Integer.parseInt(input[0]));
        
        for (ExaminationMark mark : lec.getMarks()) {
            Terminal.printLine(mark.infoStudent());
        }
    }
    
    private static String[] getParameters(final String input, final Command command) {
        //TODO validate number of parameters
        //TODO refactor to trim?
        return input.substring(command.getLength() + 1).split(";");
    }
    
    private void listProfessors() {
        for (Professor item : portalService.getProfessor()) {
            Terminal.printLine(item.getChair().getName().concat(" ")
                    .concat(item.getFirstName()).concat(" ")
                    .concat(item.getLastName().concat(" ")
                    .concat(item.average())));
        }
    }
    
    private void addProfessor(final String[] input) {
        portalService.addProfesor(input[0], input[1], input[2]);
    }
    
    private void summaryProfessor(final String[] input) {
        Professor item = portalService.getProfessor(input[0], input[1], portalService.getChair(input[2]));
        
        for (Lecture litem : item.getLectures()) {
            Terminal.printLine(litem.toStringNoCredit());
        }
    }
    
    private void addStudent(final String[] input) {
        portalService.addStudent(input[0], input[1], Integer.parseInt(input[2]));
    }
    
    private void listStudent() {
        for (Student item : portalService.getStudent()) {
            Terminal.printLine(item.toString());
        }
    }
    
    private void summaryStudent(final String[] input) {
        Student stu = portalService.getStudent(Integer.parseInt(input[2]));
        
        for (ExaminationMark mark : stu.getMarks()) {
            Terminal.printLine(mark.infoLecture());
        }
    }
    
    private void addModule(final String[] input) {
        //TODO refactor to use a single string input?
        portalService.addModule(input[0]);
    }
    
    private void listModule() {
        for (Module item : portalService.getModule()) {
            Terminal.printLine(item.toString());
        }
    }
    
    private void summaryModule(final String[] input) {
        Module mod = portalService.getModule(Integer.parseInt(input[0]));
        
        for (Lecture lect : mod.getLectures()) {
            Terminal.printLine(lect.toString());
        }
    }
    
    private void examinationMarking(final String[] input) {
        portalService.addMark(portalService.getLecture(Integer.parseInt(input[0])), 
                portalService.getStudent(Integer.parseInt(input[1])), 
                Double.parseDouble(input[2]));
    }
}
