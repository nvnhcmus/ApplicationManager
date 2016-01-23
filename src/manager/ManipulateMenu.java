/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author 10122_000
 */
public class ManipulateMenu {
    
    public JPopupMenu popupMenu;
    public ManipulateMenu()
    {
        //self contrustor!
    }
    
    public void setupMenu()
    {
        popupMenu = new JPopupMenu();
        JMenuItem menuItemAdd = new JMenuItem("Edit");
        JMenuItem menuItemRemove = new JMenuItem("Remove");
        JMenuItem menuItemRemoveAll = new JMenuItem("Remove all");
        
        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
    }
    
    public JPopupMenu getPopupMenu()
    {
        return popupMenu;
    }
}
