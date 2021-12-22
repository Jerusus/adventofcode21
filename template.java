import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution { // TODO
  public static void main(String[] args) {
    partOne();
  }

  public static void partOne() {
    try (Scanner sc = new Scanner(new File("input.txt"))) { // TODO
      while (sc.hasNextLine()) {
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void partTwo() {
    try (Scanner sc = new Scanner(new File("input.txt"))) { // TODO
      while (sc.hasNextLine()) {
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}