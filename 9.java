import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Solution9 {
  static class Node {
    int r;
    int c;
    int val;
    boolean visited;

    Node(int r, int c, int val) {
      this.r = r;
      this.c = c;
      this.val = val;
      visited = false;
    }

    List<Node> getBasinNeighbors(List<List<Node>> map) {
      List<Node> neighbors = new ArrayList<>();
      int i = this.r;
      int j = this.c;
      if (j != 0 && this.val < map.get(i).get(j - 1).val) {
        neighbors.add(map.get(i).get(j - 1));
      }
      if (j != map.get(0).size() - 1 && this.val < map.get(i).get(j + 1).val) {
        neighbors.add(map.get(i).get(j + 1));
      }
      if (i != 0 && this.val < map.get(i - 1).get(j).val) {
        neighbors.add(map.get(i - 1).get(j));
      }
      if (i != map.size() - 1 && this.val < map.get(i + 1).get(j).val) {
        neighbors.add(map.get(i + 1).get(j));
      }
      return neighbors;
    }
  }

  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input9.txt"))) {
      List<List<Integer>> map = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
          row.add(Integer.parseInt("" + s.charAt(i)));
        }
        map.add(row);
      }
      int result = 0;
      int r = map.size();
      int c = map.get(0).size();
      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          int cur = map.get(i).get(j);
          boolean left = j == 0 || cur < map.get(i).get(j - 1);
          boolean right = j == c - 1 || cur < map.get(i).get(j + 1);
          boolean up = i == 0 || cur < map.get(i - 1).get(j);
          boolean down = i == r - 1 || cur < map.get(i + 1).get(j);
          if (left && right && up && down) {
            // found a basin
          }
        }
      }
      System.out.println(result);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input9.txt"))) {
      List<List<Node>> map = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        List<Node> row = new ArrayList<>();
        for (int j = 0; j < s.length(); j++) {
          row.add(new Node(map.size(), j, Integer.parseInt("" + s.charAt(j))));
        }
        map.add(row);
      }
      int r = map.size();
      int c = map.get(0).size();

      PriorityQueue<Integer> maxes = new PriorityQueue<>(Collections.reverseOrder());
      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          Node cur = map.get(i).get(j);
          boolean left = j == 0 || cur.val < map.get(i).get(j - 1).val;
          boolean right = j == c - 1 || cur.val < map.get(i).get(j + 1).val;
          boolean up = i == 0 || cur.val < map.get(i - 1).get(j).val;
          boolean down = i == r - 1 || cur.val < map.get(i + 1).get(j).val;
          if (left && right && up && down) {
            // found min
            Queue<Node> stk = new LinkedList<>();
            stk.add(cur);
            int count = 0;
            while (!stk.isEmpty()) {
              Node n = stk.poll();
              if (n.visited || n.val == 9) {
                continue;
              }
              n.visited = true;
              count++;
              stk.addAll(n.getBasinNeighbors(map));
            }
            maxes.add(count);
          }
        }
      }
      System.out.println(maxes.poll() * maxes.poll() * maxes.poll());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}