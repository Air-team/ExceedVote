package exceedvote.air.ui;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
/**
 * Write a description of class SeviceUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SeviceUI
{
    private static Map<String,RunUI> mp = new HashMap<String,RunUI>();

    //private static SeviceUI seviceUI = null;
    
    /**
     * Constructor for objects of class SeviceUI
     */
    public SeviceUI()
    {
       
    }
    
    public void addUI(String name,RunUI ui)
    {
        mp.put(name,ui);
    }
    
    public void runByName(String name)
    {   
        Set s=mp.entrySet();
        Iterator it=s.iterator();
        while(it.hasNext())
        {
             Map.Entry m =(Map.Entry)it.next();
             String key = (String)m.getKey();
             if(name.equals(key))
             {
                  RunUI ui =(RunUI)m.getValue();
                  ui.run("");
                  break;
             }

        }
    }
    
    public void runByName(String name,String type)
    {   
        Set s=mp.entrySet();
        Iterator it=s.iterator();
        while(it.hasNext())
        {
             Map.Entry m =(Map.Entry)it.next();
             String key = (String)m.getKey();
             if(name.equals(key))
             {
                  RunUI ui =(RunUI)m.getValue();
                  ui.run(type);
                  break;
             }

        }
    }
}
