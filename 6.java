import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution6 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input6.txt"))) {
      int[] countsAtEachAge = new int[9];
      while (sc.hasNextLine()) {
        String[] ages = sc.nextLine().split(",");
        for (String age : ages) {
          countsAtEachAge[Integer.parseInt(age)]++;
        }
      }
      for (int i = 0; i < 80; i++) {
        int newCount = countsAtEachAge[0];
        for (int j = 0; j < countsAtEachAge.length - 1; j++) {
          countsAtEachAge[j] = countsAtEachAge[j + 1];
        }
        countsAtEachAge[6] += newCount;
        countsAtEachAge[8] = newCount;
      }
      int result = 0;
      for (int i = 0; i < countsAtEachAge.length; i++) {
        result += countsAtEachAge[i];
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input6.txt"))) {
      long[] countsAtEachAge = new long[9];
      while (sc.hasNextLine()) {
        String[] ages = sc.nextLine().split(",");
        for (String age : ages) {
          countsAtEachAge[Integer.parseInt(age)]++;
        }
      }
      for (int i = 0; i < 256; i++) {
        long newCount = countsAtEachAge[0];
        for (int j = 0; j < countsAtEachAge.length - 1; j++) {
          countsAtEachAge[j] = countsAtEachAge[j + 1];
        }
        countsAtEachAge[6] += newCount;
        countsAtEachAge[8] = newCount;
      }
      long result = 0;
      for (int i = 0; i < countsAtEachAge.length; i++) {
        result += countsAtEachAge[i];
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}