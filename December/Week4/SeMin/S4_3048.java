import java.util.*;
import java.io.*;

class Main {
	static class Ant{
    	String ch;
    	int num;
    	
    	Ant(String ch,int num) {
    		this.ch = ch;
    		this.num = num;
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]);
        int n2 = Integer.parseInt(s[1]);        

        ArrayList<Ant> list = new ArrayList<>();
        
        s = br.readLine().split("");
        for (int i = n1 - 1; i >= 0; i--) list.add(new Ant(s[i], 1));
        s = br.readLine().split("");
        for (int i = 0; i < n2; i++ ) list.add(new Ant(s[i], 2));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
			for(int i = 0; i < list.size()-1; i++) {
				Ant now = list.get(i);
				Ant next = list.get(i+1);
				if(now.num != 2 && now.num != next.num) {
					list.set(i,next);
					list.set(i+1, now);
					i++;
				}
			}
		}

		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).ch);
		}
    }
}