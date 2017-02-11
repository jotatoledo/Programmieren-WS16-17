package edu.kit.informatik.literatur_system;

import edu.kit.informatik.Terminal;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public class Console {
    /**
     * TODO add doc
     * @param args TODO add doc
     */
    public static void main(
            final String[] args) {
        ////
        final CommandHandler<ILiteraturSystemService, Command> h = Command.handler();
        Command c;
        do {
            c = h.accept(Terminal.readLine(), s -> Terminal.printLine("Error, no such command: '" + s + "'"));
        } while (c == null || !c.isQuit());
    }
}
