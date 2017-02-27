package edu.kit.informatik.literatur_system;

import edu.kit.informatik.Terminal;

/**
 * Console class used for the user - system interaction
 * @author JoseNote
 * @version %I%, %G%
 */
public class Console {
    /**
     * Main method
     * @param args generic console input
     */
    public static void main(final String[] args) {
        final CommandHandler<?, ICommand<ILiteraturSystemService>> h = Command.handler();
        ICommand<?> c = null;
        do {
            try {
                c = h.accept(Terminal.readLine());
                if (c.okMessage())
                    Terminal.printLine("Ok");
            } catch (IllegalStateException | IllegalArgumentException | NullPointerException  e) {
                Terminal.printError(e.getMessage());
            }
        } while (c == null || !c.isQuit());
    }
}