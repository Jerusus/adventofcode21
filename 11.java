import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Solution11 {

  static class Octopus {
    int row;
    int col;
    int energy;
    boolean flashed;

    Octopus(int row, int col, int energy) {
      this.row = row;
      this.col = col;
      this.energy = energy;
      this.flashed = false;
    }

    List<Octopus> process(List<List<Octopus>> map) {
      energy++;
      if (energy == 10) {
        flashed = true;
        return this.getNeighbors(map);
      }
      return new ArrayList<>();
    }

    List<Octopus> getNeighbors(List<List<Octopus>> map) {
      List<Octopus> neighbors = new ArrayList<>();
      int i = this.row;
      int j = this.col;
      if (j != 0) {
        neighbors.add(map.get(i).get(j - 1));
      }
      if (j != map.get(0).size() - 1) {
        neighbors.add(map.get(i).get(j + 1));
      }
      if (i != 0) {
        neighbors.add(map.get(i - 1).get(j));
      }
      if (i != map.size() - 1) {
        neighbors.add(map.get(i + 1).get(j));
      }
      if (i != 0 && j != 0) {
        neighbors.add(map.get(i - 1).get(j - 1));
      }
      if (i != 0 && j != map.get(0).size() - 1) {
        neighbors.add(map.get(i - 1).get(j + 1));
      }
      if (i != map.size() - 1 && j != 0) {
        neighbors.add(map.get(i + 1).get(j - 1));
      }
      if (i != map.size() - 1 && j != map.get(0).size() - 1) {
        neighbors.add(map.get(i + 1).get(j + 1));
      }
      return neighbors;
    }
  }

  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input11.txt"))) {
      List<List<Octopus>> map = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        List<Octopus> row = new ArrayList<>();
        for (int j = 0; j < s.length(); j++) {
          row.add(new Octopus(map.size(), j, Integer.parseInt("" + s.charAt(j))));
        }
        map.add(row);
      }
      int r = map.size();
      int c = map.get(0).size();
      int flash_count = 0;
      for (int times = 0; times < 100; times++) {
        Queue<Octopus> stk = new LinkedList<>();
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            stk.add(map.get(i).get(j));
          }
        }
        while (!stk.isEmpty()) {
          List<Octopus> adds = stk.poll().process(map);
          stk.addAll(adds);
        }
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            Octopus cur = map.get(i).get(j);
            if (cur.flashed) {
              cur.energy = 0;
              cur.flashed = false;
              flash_count++;
            }
          }
        }
      }
      System.out.println(flash_count);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input11.txt"))) {
      List<List<Octopus>> map = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        List<Octopus> row = new ArrayList<>();
        for (int j = 0; j < s.length(); j++) {
          row.add(new Octopus(map.size(), j, Integer.parseInt("" + s.charAt(j))));
        }
        map.add(row);
      }
      int r = map.size();
      int c = map.get(0).size();
      int time = 0;
      while (true) {
        time++;
        int flash_count = 0;
        Queue<Octopus> stk = new LinkedList<>();
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            stk.add(map.get(i).get(j));
          }
        }
        while (!stk.isEmpty()) {
          List<Octopus> adds = stk.poll().process(map);
          stk.addAll(adds);
        }
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            Octopus cur = map.get(i).get(j);
            if (cur.flashed) {
              cur.energy = 0;
              cur.flashed = false;
              flash_count++;
            }
          }
        }
        if (flash_count == r * c) {
          System.out.println(time);
          break;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}