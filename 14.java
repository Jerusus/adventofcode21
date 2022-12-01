import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution14 {
  public static void main(String[] args) {
    partTwo();
  }

  public static void partOne() {
    try (Scanner sc = new Scanner(new File("input14.txt"))) {
      String polymer = sc.nextLine();
      sc.nextLine();
      Map<String, String> rules = new HashMap<>();
      while (sc.hasNextLine()) {
        String[] rule = sc.nextLine().split(" -> ");
        rules.put(rule[0], rule[1]);
      }
      for (int i = 0; i < 10; i++) {
        StringBuilder builder = new StringBuilder();
        builder.append(polymer.charAt(0));
        for (int j = 0; j < polymer.length() - 1; j++) {
          String pair = polymer.substring(j, j + 2);
          builder.append(rules.getOrDefault(pair, ""));
          builder.append(polymer.charAt(j + 1));
        }
        polymer = builder.toString();
      }
      Map<Character, Integer> countMap = new HashMap<>();
      for (int i = 0; i < polymer.length(); i++) {
        countMap.put(polymer.charAt(i), countMap.getOrDefault(polymer.charAt(i), 0) + 1);
      }
      int max = 0;
      int min = Integer.MAX_VALUE;
      for (Character c : countMap.keySet()) {
        int count = countMap.get(c);
        if (count > max)
          max = count;
        if (count < min)
          min = count;
      }
      System.out.println(max - min);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void partTwo() {
    try (Scanner sc = new Scanner(new File("input14.txt"))) {
      String polymer = sc.nextLine();
      sc.nextLine();
      Map<String, String> rules = new HashMap<>();
      while (sc.hasNextLine()) {
        String[] rule = sc.nextLine().split(" -> ");
        rules.put(rule[0], rule[1]);
      }
      Map<String, Long> pairCount = new HashMap<>();
      for (int i = 0; i < polymer.length() - 1; i++) {
        String pair = polymer.substring(i, i + 2);
        pairCount.put(pair, pairCount.getOrDefault(pair, 0L) + 1);
      }
      for (int i = 0; i < 40; i++) {
        Map<String, Long> newPairCount = new HashMap<>();
        for (String pair : pairCount.keySet()) {
          if (rules.containsKey(pair)) {
            String a = pair.charAt(0) + rules.get(pair);
            newPairCount.put(a, newPairCount.getOrDefault(a, 0L) + pairCount.get(pair));
            String b = rules.get(pair) + pair.charAt(1);
            newPairCount.put(b, newPairCount.getOrDefault(b, 0L) + pairCount.get(pair));
          } else {
            newPairCount.put(pair, newPairCount.getOrDefault(pair, 0L) + pairCount.get(pair));
          }
        }
        pairCount = newPairCount;
      }
      Map<Character, Long> countMap = new HashMap<>();
      for (String pair : pairCount.keySet()) {
        countMap.put(pair.charAt(0), countMap.getOrDefault(pair.charAt(0), 0L) + pairCount.get(pair));
        countMap.put(pair.charAt(1), countMap.getOrDefault(pair.charAt(1), 0L) + pairCount.get(pair));
      }
      countMap.put(polymer.charAt(0), countMap.get(polymer.charAt(0)) + 1);
      countMap.put(polymer.charAt(polymer.length() - 1), countMap.get(polymer.charAt(polymer.length() - 1)) + 1);
      for (Character c : countMap.keySet()) {
        countMap.put(c, countMap.get(c) / 2);
      }
      long max = 0;
      long min = Long.MAX_VALUE;
      for (Character c : countMap.keySet()) {
        long count = countMap.get(c);
        if (count > max)
          max = count;
        if (count < min)
          min = count;
      }
      System.out.println(max - min);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}