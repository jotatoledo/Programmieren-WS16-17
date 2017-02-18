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
        final CommandHandler<?, ICommand<ILiteraturSystemService>> h = Command.handler();
        ICommand<?> c = null;
        do {
            try {
                c = h.accept(Terminal.readLine());
                if (c.printOkMessage())
                    Terminal.printLine("Ok");
            } catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
                Terminal.printError(e.getMessage());
            }
        } while (c == null || !c.isQuit());
    }
}
