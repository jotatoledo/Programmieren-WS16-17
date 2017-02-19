package edu.kit.informatik.literatur_system;

import edu.kit.informatik.Terminal;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public class Console {
    /**
     * FIXME add doc add doc
     * @param args FIXME add doc add doc
     */
    public static void main(final String[] args) {
        final CommandHandler<?, ICommand<ILiteraturSystemService>> h = Command.handler();
        ICommand<?> c = null;
        do {
            try {
                c = h.accept(Terminal.readLine());
                if (c.okMessage())
                    Terminal.printLine("Ok");
            } catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
                Terminal.printError(e.getMessage());
            }
        } while (c == null || !c.isQuit());
    }
}
