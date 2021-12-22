import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Solution12 {

  enum CaveType {
    START,
    END,
    SMALL,
    BIG,
  }

  static class Cave {
    String name;
    CaveType ct;
    Set<Cave> neighbors;

    Cave(String name) {
      this.name = name;
      this.neighbors = new HashSet<>();
      if (name.equals("start")) {
        this.ct = CaveType.START;
      } else if (name.equals("end")) {
        this.ct = CaveType.END;
      } else if (name.toUpperCase().equals(name)) {
        this.ct = CaveType.BIG;
      } else {
        this.ct = CaveType.SMALL;
      }
    }

    void addNeighbor(Cave neighbor) {
      this.neighbors.add(neighbor);
    }
  }

  public static void main(String[] args) {
    partTwo();
  }

  public static void partOne() {
    try (Scanner sc = new Scanner(new File("input12.txt"))) {
      Map<String, Cave> caveMap = new HashMap<>();
      while (sc.hasNextLine()) {
        String[] caves = sc.nextLine().split("-");
        Cave a, b;
        if (caveMap.containsKey(caves[0])) {
          a = caveMap.get(caves[0]);
        } else {
          a = new Cave(caves[0]);
          caveMap.put(caves[0], a);
        }
        if (caveMap.containsKey(caves[1])) {
          b = caveMap.get(caves[1]);
        } else {
          b = new Cave(caves[1]);
          caveMap.put(caves[1], b);
        }
        a.addNeighbor(b);
        b.addNeighbor(a);
      }
      Set<Cave> visited = new HashSet<>();
      int count = recurse(caveMap.get("start"), visited);
      System.out.println(count);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static int recurse(Cave cur, Set<Cave> visited) {
    if (cur.ct == CaveType.END) {
      return 1;
    } else if (cur.ct != CaveType.BIG && visited.contains(cur)) {
      return 0;
    }
    if (cur.ct != CaveType.BIG)
      visited.add(cur);
    int result = 0;
    for (Cave c : cur.neighbors) {
      result += recurse(c, visited);
    }
    visited.remove(cur);
    return result;
  }

  public static void partTwo() {
    try (Scanner sc = new Scanner(new File("input12.txt"))) {
      Map<String, Cave> caveMap = new HashMap<>();
      while (sc.hasNextLine()) {
        String[] caves = sc.nextLine().split("-");
        Cave a, b;
        if (caveMap.containsKey(caves[0])) {
          a = caveMap.get(caves[0]);
        } else {
          a = new Cave(caves[0]);
          caveMap.put(caves[0], a);
        }
        if (caveMap.containsKey(caves[1])) {
          b = caveMap.get(caves[1]);
        } else {
          b = new Cave(caves[1]);
          caveMap.put(caves[1], b);
        }
        a.addNeighbor(b);
        b.addNeighbor(a);
      }
      Set<Cave> visited = new HashSet<>();
      int count = recurseExtra(caveMap.get("start"), visited, false);
      System.out.println(count);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static int recurseExtra(Cave cur, Set<Cave> visited, boolean visitedExtra) {
    boolean shouldRemove = true;
    if (cur.ct == CaveType.END) {
      return 1;
    } else if (cur.ct != CaveType.BIG && visited.contains(cur)) {
      if (!visitedExtra && cur.ct == CaveType.SMALL) {
        visitedExtra = true;
        shouldRemove = false;
      } else {
        return 0;
      }
    }
    if (cur.ct != CaveType.BIG)
      visited.add(cur);
    int result = 0;
    for (Cave c : cur.neighbors) {
      result += recurseExtra(c, visited, visitedExtra);
    }
    if (shouldRemove) {
      visited.remove(cur);
    }
    return result;
  }
}