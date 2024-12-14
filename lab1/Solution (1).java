import java.io.*;
import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);  // Sort the array first
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through the array, considering each element as the first in the triplet
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicates for the first element

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];
                
                if (total < 0) {
                    left++;
                } else if (total > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Skip duplicates for the second element
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for the third element
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    // Reads the input file and returns the array of integers
    public static int[] readInputFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] numsStr = br.readLine().split(",");
            return Arrays.stream(numsStr).mapToInt(Integer::parseInt).toArray();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            return null;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: Invalid input format.");
            return null;
        }
    }

    public static void main(String[] args) {
        String filePath = "input.txt";  // Specify the input file path
        int[] nums = readInputFile(filePath);

        if (nums != null) {
            Solution solution = new Solution();
            List<List<Integer>> result = solution.threeSum(nums);
            System.out.println("Output: " + result);
        }
    }
}
