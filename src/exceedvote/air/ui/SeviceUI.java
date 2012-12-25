package exceedvote.air.ui;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Service class for store all UI class
 * 
 * @author Air Team
 * @version (a version number or a date)
 */
public class SeviceUI
{
    private static Map<String,RunUI> mp = new HashMap<String,RunUI>();
   
    /**
     * Constructor for objects of class SeviceUI
     */
    public SeviceUI()
    {
       
    }
    
    /**
     *add UI class in map
     *@param ui's name , class ui that implement RunUI
     *
     */
    public void addUI(String name,RunUI ui)
    {
        mp.put(name,ui);
    }
    /**
     *run UI in map by name
     *@param ui's name
     */
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
     /**
     *run UI in map by name and type
     *@param ui's name,ui's type
     */
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
