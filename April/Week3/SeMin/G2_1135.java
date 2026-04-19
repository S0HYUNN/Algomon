import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adjList;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int myParent = Integer.parseInt(stringTokenizer.nextToken());
            if (myParent == -1) continue;
            adjList.get(myParent).add(i);
        }

        System.out.println(dfs(0));

    }

    private static int dfs(int parent) {
        List<Integer> children = adjList.get(parent);
        List<Integer> times = new ArrayList<>();

        if (children.isEmpty()) return 0;

        for (int child : children) {
            times.add(dfs(child));
        }

        times.sort(Collections.reverseOrder());

        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + i + 1);
        }

        return maxTime;
    }
}