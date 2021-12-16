import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution8 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input8.txt"))) {
      int result = 0;
      while (sc.hasNextLine()) {
        String[] s = sc.nextLine().split(" \\| ");
        String[] output = s[1].split(" ");
        for (String val : output) {
          int l = val.length();
          if (l == 2 || l == 3 || l == 4 || l == 7) {
            result++;
          }
        }
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input8.txt"))) {
      int result = 0;
      while (sc.hasNextLine()) {
        Map<Integer, String> numToLetters = new HashMap<>();
        String[] s = sc.nextLine().split(" \\| ");
        String[] both = s[0].split(" ");
        String[] output = s[1].split(" ");
        boolean[] used = new boolean[both.length];
        // find 1, 4, 7, 8
        for (int i = 0; i < both.length; i++) {
          char[] temp = both[i].toCharArray();
          Arrays.sort(temp);
          String val = new String(temp);
          both[i] = val;
          int l = val.length();
          switch (l) {
            case 2:
              numToLetters.put(1, val);
              used[i] = true;
              break;
            case 3:
              numToLetters.put(7, val);
              used[i] = true;
              break;
            case 4:
              numToLetters.put(4, val);
              used[i] = true;
              break;
            case 7:
              numToLetters.put(8, val);
              used[i] = true;
              break;
            default:
              break;
          }
        }
        // find 3
        for (int i = 0; i < both.length; i++) {
          if (!used[i] && both[i].length() == 5 && has(both[i], numToLetters.get(1))) {
            numToLetters.put(3, both[i]);
            used[i] = true;
            break;
          }
        }
        // find 5
        for (int i = 0; i < both.length; i++) {
          if (!used[i] && both[i].length() == 5 && partialHas(both[i], numToLetters.get(4))) {
            numToLetters.put(5, both[i]);
            used[i] = true;
            break;
          }
        }
        // find 2
        for (int i = 0; i < both.length; i++) {
          if (!used[i] && both[i].length() == 5) {
            numToLetters.put(2, both[i]);
            used[i] = true;
            break;
          }
        }
        // find 9
        for (int i = 0; i < both.length; i++) {
          if (!used[i] && has(both[i], numToLetters.get(3))) {
            numToLetters.put(9, both[i]);
            used[i] = true;
            break;
          }
        }
        // find 0
        for (int i = 0; i < both.length; i++) {
          if (!used[i] && has(both[i], numToLetters.get(7))) {
            numToLetters.put(0, both[i]);
            used[i] = true;
            break;
          }
        }
        // find 6
        for (int i = 0; i < both.length; i++) {
          if (!used[i]) {
            numToLetters.put(6, both[i]);
            used[i] = true;
            break;
          }
        }
        String val = "";
        for (int i = 0; i < output.length; i++) {
          char[] temp = output[i].toCharArray();
          Arrays.sort(temp);
          String str = new String(temp);
          for (int j : numToLetters.keySet()) {
            if (numToLetters.get(j).equals(str)) {
              val += j;
              break;
            }
          }
        }
        result += Integer.parseInt(val);
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static boolean has(String a, String b) {
    for (int i = 0; i < b.length(); i++) {
      if (!a.contains("" + b.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  static boolean partialHas(String a, String b) {
    // specifically 3/4
    int count = 0;
    for (int i = 0; i < b.length(); i++) {
      if (a.contains("" + b.charAt(i))) {
        count++;
      }
    }
    return count >= 3;
  }
}