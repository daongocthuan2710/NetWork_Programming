import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
public class Practise2 {
    public static void main(String[] args) {
        String data = " Dai hoc sai gon la mot trong nhung truong dai hoc lau doi nhat sai  gon";
        Map<String,String> map = new LinkedHashMap<String,String>();
        StringTokenizer st = new StringTokenizer (data, " ");
        
        while ( st.hasMoreTokens())
        {
            String tmp = st.nextToken();
            map.putIfAbsent(tmp.toLowerCase(), tmp);
        }
        Set<String> set = map.keySet();
        for (String key : set) {
            System.out.print(map.get(key) + " ");
        }
    }
}
