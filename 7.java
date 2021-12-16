import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Solution7 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input7.txt")).useDelimiter("\\D")) {
      Queue<Integer> hi_values = new PriorityQueue<>();
      int lo_count = 0;
      int lo_sum = 0;
      int hi_sum = 0;

      while (sc.hasNext()) {
        int input = sc.nextInt();
        hi_sum += input;
        hi_values.add(input);
      }

      int min = hi_sum;
      while (true) {
        int moving = hi_values.remove();
        lo_count++;
        lo_sum += moving;
        hi_sum -= moving;
        int cur = (lo_count * moving - lo_sum) + (hi_sum - hi_values.size() * moving);
        if (cur > min) {
          System.out.println(min);
          break;
        } else {
          min = cur;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input7.txt")).useDelimiter("\\D")) {
      List<Integer> vals = new ArrayList<>();
      int hi_sum = 0;
      int count = 0;
      while (sc.hasNext()) {
        int input = sc.nextInt();
        hi_sum += input;
        vals.add(input);
        count++;
      }
      int goal = hi_sum / count;
      int result = 0;
      for (int val : vals) {
        result += cost(val, goal);
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static int cost(int input, int goal) {
    int distance = Math.abs(input - goal);
    return distance * (distance + 1) / 2;
  }
}