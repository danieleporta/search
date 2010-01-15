package nl.xs4all.banaan.tst8.web.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.xs4all.banaan.tst8.util.Assoc;
import nl.xs4all.banaan.tst8.web.fcp.FcpPanel;
import nl.xs4all.banaan.tst8.web.jndi.JndiPanel;
import nl.xs4all.banaan.tst8.web.letter.LetterPanel;
import nl.xs4all.banaan.tst8.web.notificator.NotificationPanel;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePanel;
import nl.xs4all.banaan.tst8.web.param.ParamPanel;
import nl.xs4all.banaan.tst8.web.property.PropertyPanel;
import nl.xs4all.banaan.tst8.web.subclass.SubClassPanel;
import nl.xs4all.banaan.tst8.web.upload.UploadPanel;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Menulist map name of menu item to page to be visited
 * @author konijn
 *
 */
public class MenuList {

    private static void add(List<Assoc<Class<? extends Panel>>> result, String key,
            Class<? extends Panel> value) {
        result.add(new Assoc<Class<? extends Panel>>(key, value));
    }

   
    public static List<Assoc<Class<? extends Panel>>> getList() {
        List<Assoc<Class<? extends Panel>>> list = makeList();
        Collections.sort(list, Assoc.<Class<? extends Panel>>comparator());
        return list;
    }
    
    private static List<Assoc<Class< ? extends Panel>>> makeList() {
        List<Assoc<Class<? extends Panel>>> result = 
            new ArrayList<Assoc<Class<? extends Panel>>>();
        add(result, "jndi", JndiPanel.class);
        add(result, "letter", LetterPanel.class);
        add(result, "param", ParamPanel.class);
        add(result, "property", PropertyPanel.class);
        add(result, "notificator", NotificationPanel.class);
        add(result, "upload", UploadPanel.class);
        add(result, "onchange", OnchangePanel.class);
        add(result, "subclass", SubClassPanel.class);
        add(result, "fcp", FcpPanel.class); 
        return result ;
    }
    
   
    public static Class<? extends Panel> lookup(String wanted) {
        for (Assoc<Class<? extends Panel>> binding : makeList()) {
            String key = binding.getKey();
            if (key.equals(wanted)) {
                return binding.getValue();
            }
        }
        throw new IllegalArgumentException("Key not found: " + wanted);
    }

    
}
