import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution1 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    File file = new File("input.txt");
    Scanner sc;
    int result = 0;
    int prev = Integer.MAX_VALUE;
    try {
      sc = new Scanner(file);
      while (sc.hasNextLine()) {
        int cur = Integer.parseInt(sc.nextLine());
        if (cur > prev)
          result++;
        prev = cur;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }

  public static void part_two() {
    File file = new File("input.txt");
    Scanner sc;
    int result = 0;
    int prev_sum = Integer.MAX_VALUE;
    Queue<Integer> window = new LinkedList<>();
    int window_sum = 0;
    try {
      sc = new Scanner(file);
      while (sc.hasNextLine()) {
        int cur = Integer.parseInt(sc.nextLine());
        window.add(cur);
        window_sum += cur;
        if (window.size() <= 3) {
          prev_sum = window_sum;
          continue;
        }
        int popped = window.poll();
        window_sum -= popped;

        if (window_sum > prev_sum)
          result++;
        prev_sum = window_sum;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }
}