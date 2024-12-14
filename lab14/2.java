import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'prims' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY edges
     *  3. INTEGER start
     */

    public static int prims(int n, List<List<Integer>> edges, int start) {
        
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(
                                    (a,b) -> a.get(2) - b.get(2));
        heap.addAll(edges);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int minSum = 0;
        
        while(visited.size() < n) {
            List<List<Integer>> temp = new LinkedList<>();
            while(!heap.isEmpty()) {
                List<Integer> min = heap.poll();
                if (visited.contains(min.get(0)) && visited.contains(min.get(1))) {
                    continue;
                } else if (visited.contains(min.get(0)) && !visited.contains(min.get(1))) {
                    visited.add(min.get(1));
                    minSum += min.get(2);
                    break;
                } else if (visited.contains(min.get(1)) && !visited.contains(min.get(0))) {
                    visited.add(min.get(0));
                    minSum += min.get(2);
                    break;                    
                } else {
                    temp.add(min);
                }
            }
            heap.addAll(temp);
        }
        
        return minSum;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                edges.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int start = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}