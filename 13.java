import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Solution13 {
  public static void main(String[] args) {
    partTwo();
  }

  public static void partOne() {
    try (Scanner sc = new Scanner(new File("input13.txt"))) {
      Set<Point> points = new HashSet<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.isEmpty()) {
          break;
        }
        String[] coords = s.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        Point pt = new Point(x, y);
        points.add(pt);
      }
      while (sc.hasNextLine()) {
        String instruction = sc.nextLine();
        int line = Integer.parseInt(instruction.split("=")[1]);
        Set<Point> finalPoints = new HashSet<>();
        for (Point pt : points) {
          int change = 2 * ((int) pt.getX() - 655);
          if (change > 0) {
            pt.translate(-change, 0);
          }
          finalPoints.add(pt);
        }
        System.out.println(finalPoints.size());
        break;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void partTwo() {
    try (Scanner sc = new Scanner(new File("input13.txt"))) {
      Set<Point> points = new HashSet<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.isEmpty()) {
          break;
        }
        String[] coords = s.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        Point pt = new Point(x, y);
        points.add(pt);
      }
      while (sc.hasNextLine()) {
        String instruction = sc.nextLine();
        int line = Integer.parseInt(instruction.split("=")[1]);
        Set<Point> finalPoints = new HashSet<>();
        for (Point pt : points) {
          if (instruction.contains("x")) {
            int change = 2 * ((int) pt.getX() - line);
            if (change > 0)
              pt.translate(-change, 0);
          } else {
            int change = 2 * ((int) pt.getY() - line);
            if (change > 0)
              pt.translate(0, -change);
          }
          finalPoints.add(pt);
        }
        points = finalPoints;
      }
      char[][] cmap = new char[50][50];
      for (Point pt : points) {
        cmap[pt.x][pt.y] = '*';
      }
      for (int i = 0; i < cmap.length; i++) {
        for (int j = 0; j < cmap[0].length; j++) {
          char c = cmap[j][i];
          if (c == '*') {
            System.out.print(c);
          } else {
            System.out.print(' ');
          }
        }
        System.out.println();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}