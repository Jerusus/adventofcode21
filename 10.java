import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Solution10 {
  public static void main(String[] args) {
    part_two();
  }

  public static void part_one() {
    try (Scanner sc = new Scanner(new File("input10.txt"))) {
      int score = 0;
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        LinkedList<Character> ll = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          boolean found = false;
          switch (c) {
            case ')':
              if (ll.peek() != '(') {
                score += 3;
                found = true;
              } else {
                ll.pop();
              }
              break;
            case ']':
              if (ll.peek() != '[') {
                score += 57;
                found = true;
              } else {
                ll.pop();
              }
              break;
            case '}':
              if (ll.peek() != '{') {
                score += 1197;
                found = true;
              } else {
                ll.pop();
              }
              break;
            case '>':
              if (ll.peek() != '<') {
                score += 25137;
                found = true;
              } else {
                ll.pop();
              }
              break;
            default:
              ll.push(c);
          }
          if (found)
            break;
        }
      }
      System.out.println(score);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void part_two() {
    try (Scanner sc = new Scanner(new File("input10.txt"))) {
      List<Long> scores = new ArrayList<>();
      while (sc.hasNextLine()) {
        long score = 0;
        String s = sc.nextLine();
        LinkedList<Character> ll = new LinkedList<>();
        boolean corrupted = false;
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if (!isOp(c)) {
            if (ll.isEmpty() || c != getOpp(ll.peek())) {
              corrupted = true;
              break;
            } else {
              ll.pop();
            }
          } else {
            ll.push(c);
          }
        }
        if (corrupted) {
          continue;
        }
        while (!ll.isEmpty()) {
          char c = ll.pop();
          score *= 5;
          score += getScore(c);
        }
        scores.add(score);
      }
      Collections.sort(scores);
      System.out.println(scores.get(scores.size() / 2));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static int getScore(char c) {
    if (c == '(') {
      return 1;
    }
    if (c == '[') {
      return 2;
    }
    if (c == '{') {
      return 3;
    }
    if (c == '<') {
      return 4;
    }
    return 0;
  }

  static boolean isOp(char c) {
    return "([{<".contains("" + c);
  }

  static char getOpp(char c) {
    if (c == '(') {
      return ')';
    }
    if (c == '[') {
      return ']';
    }
    if (c == '{') {
      return '}';
    }
    if (c == '<') {
      return '>';
    }
    return ' ';
  }
}