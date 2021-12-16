import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution4 {
  public static void main(String[] args) {
    part_two();
  }

  static class Board {
    int[][] board;
    boolean[][] hit;

    Board(String[] lines) {
      board = new int[5][5];
      hit = new boolean[5][5];
      for (int i = 0; i < lines.length; i++) {
        String[] nums = lines[i].trim().split("\\s+");
        for (int j = 0; j < nums.length; j++) {
          board[i][j] = Integer.parseInt(nums[j]);
        }
      }
    }

    void mark(int num) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          if (board[i][j] == num) {
            hit[i][j] = true;
          }
        }
      }
    }

    boolean won() {
      int[][] wins = {
          { 0, 1, 2, 3, 4 },
          { 5, 6, 7, 8, 9 },
          { 10, 11, 12, 13, 14 },
          { 15, 16, 17, 18, 19 },
          { 20, 21, 22, 23, 24 },
          { 0, 5, 10, 15, 20 },
          { 1, 6, 11, 16, 21 },
          { 2, 7, 12, 17, 22 },
          { 3, 8, 13, 18, 23 },
          { 4, 9, 14, 19, 24 },
      };

      for (int[] win : wins) {
        int count = 0;
        for (int num : win) {
          int i = num % 5;
          int j = num / 5;
          if (hit[i][j]) {
            count++;
          }
        }
        if (count == 5) {
          return true;
        }
      }
      return false;
    }

    int score(int x) {
      int sum = 0;
      for (int i = 0; i < hit.length; i++) {
        for (int j = 0; j < hit[0].length; j++) {
          if (!hit[i][j]) {
            sum += board[i][j];
          }
        }
      }
      return x * sum;
    }
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input4.txt"))) {
      String[] nums = sc.nextLine().split(",");
      List<Board> boards = new ArrayList<>();
      while (sc.hasNextLine()) {
        String[] lines = new String[5];
        String s = sc.nextLine();
        if (s.isEmpty())
          continue;
        lines[0] = s;
        for (int i = 1; i < 5; i++) {
          s = sc.nextLine();
          if (s.isEmpty()) {
            i--;
            continue;
          }
          lines[i] = s;

        }
        Board b = new Board(lines);
        boards.add(b);
      }
      for (int i = 0; i < nums.length; i++) {
        int num = Integer.parseInt(nums[i]);
        for (Board b : boards) {
          b.mark(num);
          if (i >= 4 && b.won()) {
            int result = b.score(num);
            System.out.println(result);
            return;
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input4.txt"))) {
      String[] nums = sc.nextLine().split(",");
      List<Board> boards = new ArrayList<>();
      while (sc.hasNextLine()) {
        String[] lines = new String[5];
        String s = sc.nextLine();
        if (s.isEmpty())
          continue;
        lines[0] = s;
        for (int i = 1; i < 5; i++) {
          s = sc.nextLine();
          if (s.isEmpty()) {
            i--;
            continue;
          }
          lines[i] = s;

        }
        Board b = new Board(lines);
        boards.add(b);
      }
      for (int i = 0; i < nums.length; i++) {
        int num = Integer.parseInt(nums[i]);
        for (Board b : boards) {
          b.mark(num);
          if (boards.size() == 1 && b.won()) {
            int result = b.score(num);
            System.out.println(result);
            return;
          }
        }
        boards.removeIf(Board::won);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}