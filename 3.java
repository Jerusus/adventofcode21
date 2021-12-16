import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution3 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input3.txt"))) {
      int l = 0;
      int[] z = null;
      int count = 0;
      while (sc.hasNextLine()) {
        count++;
        String s = sc.nextLine();
        if (z == null) {
          l = s.length();
          z = new int[l];
        }
        for (int i = 0; i < l; i++) {
          char c = s.charAt(i);
          if (c == '1') {
            z[i]++;
          }
        }
      }
      String a = "";
      String b = "";
      for (int v : z) {
        if (v > count / 2) {
          a += "1";
          b += "0";
        } else {
          a += "0";
          b += "1";
        }
      }
      System.out.println(Integer.parseInt(a, 2) * Integer.parseInt(b, 2));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input3.txt"))) {
      List<String> ones = new ArrayList<>();
      List<String> zeros = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) == '1') {
          ones.add(s);
        } else {
          zeros.add(s);
        }
      }
      List<String> most = null;
      List<String> least = null;
      if (ones.size() >= zeros.size()) {
        most = ones;
        least = zeros;
      } else {
        most = zeros;
        least = ones;
      }

      int string_index = 1;
      int array_index = 0;
      char del = ' ';
      int one_count = 0;
      while (most.size() > 1) {
        if (array_index >= most.size()) {
          string_index++;
          array_index = 0;
          if (one_count >= most.size() / 2.0) {
            del = '0';
          } else {
            del = '1';
          }
          one_count = 0;
          continue;
        }
        if (most.get(array_index).charAt(string_index - 1) == del) {
          most.remove(array_index);
          continue;
        }
        if (most.get(array_index).charAt(string_index) == '1') {
          one_count++;
        }
        array_index++;
      }

      string_index = 1;
      array_index = 0;
      del = ' ';
      int zero_count = 0;
      while (least.size() > 1) {
        if (array_index >= least.size()) {
          string_index++;
          array_index = 0;
          if (zero_count <= least.size() / 2.0) {
            del = '1';
          } else {
            del = '0';
          }
          zero_count = 0;
          continue;
        }
        if (least.get(array_index).charAt(string_index - 1) == del) {
          least.remove(array_index);
          continue;
        }
        if (least.get(array_index).charAt(string_index) == '0') {
          zero_count++;
        }
        array_index++;
      }
      System.out.println(Integer.parseInt(most.get(0), 2) * Integer.parseInt(least.get(0), 2));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}