import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution { // TODO
  public static void main(String[] args) {
    part_one();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input.txt"))) { // TODO
      while (sc.hasNextLine()) {
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input.txt"))) { // TODO
      while (sc.hasNextLine()) {
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}