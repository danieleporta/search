package nl.xs4all.banaan.tst8.web.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

/** Maps name of menu item to page to be visited */
public class MenuList {
    private static Map<String, Class<? extends Panel>> map;
    
    static {
        map = new HashMap<String, Class<? extends Panel>>();
        map.put("jndi", JndiPanel.class);
        map.put("letter", LetterPanel.class);
        map.put("param", ParamPanel.class);
        map.put("property", PropertyPanel.class);
        map.put("notificator", NotificationPanel.class);
        map.put("upload", UploadPanel.class);
        map.put("onchange", OnchangePanel.class);
        map.put("subclass", SubClassPanel.class);
        map.put("fcp", FcpPanel.class); 
    };
    
    public static List<Entry<String,Class<? extends Panel>>> getList() {
        List<Entry<String, Class<? extends Panel>>> list = 
            new ArrayList<Entry<String, Class<? extends Panel>>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Class<? extends Panel>>> () {
            public int compare(Entry<String, Class<? extends Panel>> o1,
                    Entry<String, Class<? extends Panel>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return list;
    }
    
    public static Class<? extends Panel> lookup(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        throw new IllegalArgumentException("Key not found: " + key);        
    }
}
