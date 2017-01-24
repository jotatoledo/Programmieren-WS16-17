package edu.kit.informatik.student_portal;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.student_portal.bll.PortalService;
import edu.kit.informatik.student_portal.bll.contracts.IPortalService;

public class Portal {
    private final IPortalService portalService;
    
    /**
     * 
     */
    public Portal() {
        portalService = new PortalService();
    }
    
    /**
     * 
     * @param args TODO
     */
    public static void main(String args[]) {
        Portal portalData = new Portal();
        do {
            String input = Terminal.readLine();
        } while(true);
    }
}
