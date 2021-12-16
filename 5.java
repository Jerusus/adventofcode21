import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution5 {
  static class Line {
    int x1, x2, y1, y2;

    Line(String s) {
      String[] points = s.split(" -> ");
      String[] a = points[0].split(",");
      String[] b = points[1].split(",");
      x1 = Integer.parseInt(a[0]);
      x2 = Integer.parseInt(b[0]);
      y1 = Integer.parseInt(a[1]);
      y2 = Integer.parseInt(b[1]);
    }

    boolean isOrthogonal() {
      return (x1 == x2) || (y1 == y2);
    }

    boolean isNegativeSlope() {
      if (isOrthogonal())
        return false;
      if (x1 < x2)
        return y2 < y1;
      else
        return y1 < y2;
    }

    boolean hit(int x, int y) {
      if (isOrthogonal() && hitOrthogonal(x, y)) {
        return true;
      } else if (inbetween(y, y1, y2) && inbetween(x, x1, x2)) {
        if (isNegativeSlope()) {
          return x - x1 == y1 - y;
        } else {
          return x - x1 == y - y1;
        }
      }
      return false;
    }

    boolean hitOrthogonal(int x, int y) {
      return (x == x1 && x1 == x2 && inbetween(y, y1, y2)) || (y == y1 && y1 == y2 && inbetween(x, x1, x2));
    }

    private boolean inbetween(int value, int a, int b) {
      int[] array = { value, a, b };
      Arrays.sort(array);
      return array[1] == value;
    }

  }

  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input5.txt"))) {
      // read input into collection of lines
      List<Line> lines = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        Line l = new Line(s);
        if (l.isOrthogonal()) {
          lines.add(l);
        }
      }
      // loop through 1000x1000 points
      int result = 0;
      for (int i = 0; i < 1000; i++) {
        for (int j = 0; j < 1000; j++) {
          // check line collection if this point is included, return early if there are
          // two lines found
          int count = 0;
          for (Line line : lines) {
            if (line.hit(i, j)) {
              count++;
            }
            if (count >= 2) {
              result++;
              break;
            }
          }
        }
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input5.txt"))) {
      // read input into collection of lines
      List<Line> lines = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        lines.add(new Line(s));
      }
      // loop through 1000x1000 points
      int result = 0;
      for (int i = 0; i < 1000; i++) {
        for (int j = 0; j < 1000; j++) {
          // check line collection if this point is included, return early if there are
          // two lines found
          int count = 0;
          for (Line line : lines) {
            if (line.hit(i, j)) {
              count++;
            }
            if (count >= 2) {
              result++;
              break;
            }
          }
        }
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}