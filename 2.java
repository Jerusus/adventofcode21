import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution2 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    int f = 0;
    int d = 0;
    try (Scanner sc = new Scanner(new File("input2.txt"))) {
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        String[] line = s.split(" ");
        if (line[0].equals("forward")) {
          f += Integer.parseInt(line[1]);
        }
        if (line[0].equals("down")) {
          d += Integer.parseInt(line[1]);
        }
        if (line[0].equals("up")) {
          d -= Integer.parseInt(line[1]);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(f * d);
  }

  public static void part_two() {
    int f = 0;
    int d = 0;
    int aim = 0;
    try (Scanner sc = new Scanner(new File("input2.txt"))) {
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        String[] line = s.split(" ");
        if (line[0].equals("forward")) {
          f += Integer.parseInt(line[1]);
          d += aim * Integer.parseInt(line[1]);
        }
        if (line[0].equals("down")) {
          aim += Integer.parseInt(line[1]);
        }
        if (line[0].equals("up")) {
          aim -= Integer.parseInt(line[1]);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(f * d);
  }
}