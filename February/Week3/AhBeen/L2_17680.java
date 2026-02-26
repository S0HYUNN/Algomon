import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        LinkedList<String> list = new LinkedList<>();
        int time = 0;

        for (String city : cities) {
            city = city.toUpperCase();

            if (list.contains(city)) {
                list.remove(city);
                list.addLast(city);
                time += 1;
            } else {
                if (list.size() == cacheSize) {
                    list.removeFirst();
                }
                list.addLast(city);
                time += 5;
            }
        }
        return time;
    }
}
