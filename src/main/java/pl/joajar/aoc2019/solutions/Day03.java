package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {
    public static int part1result() {
        Map<Integer, String[]> ourData = getData();

        Map<Integer, Set<Integer>> commonPositions
                = findPositionsForWire(
                findPositionsForWire(new HashMap<>(), ourData.get(0), true),
                ourData.get(1), false);

        TreeSet<Integer> distances = new TreeSet<>();

        for (Integer workingX : commonPositions.keySet()) {
            for (Integer workingY : commonPositions.get(workingX)) {
                distances.add(Math.abs(workingX) + Math.abs(workingY));
            }
        }

        int toReturn = -1;
        try {
            toReturn = distances.higher(0);
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception!");
            e.printStackTrace();
        }
        return toReturn;
    }

    private static Map<Integer, Set<Integer>> findPositionsForWire(Map<Integer, Set<Integer>> mapVariable, String[] arrayVariable, boolean booleanVariable) {
        int x = 0, y = 0;

        Map<Integer, Set<Integer>> pointsMap = new HashMap<>();
        pointsMap.put(x, Stream.of(y).collect(Collectors.toSet()));

        for (String workingString : arrayVariable) {
            for (int i = 0; i < Integer.parseInt(workingString.substring(1)); i++) {

                switch (workingString.substring(0, 1)) {
                    case "R":
                        x++;
                        break;
                    case "U":
                        y++;
                        break;
                    case "L":
                        x--;
                        break;
                    case "D":
                        y--;
                        break;
                    default:
                        System.out.println("Unknown code " + workingString.substring(0, 1) + " - something went wrong! ");
                        throw new IllegalStateException("Something went wrong!");
                }

                if (booleanVariable) {
                    addPoint(pointsMap, x, y);
                } else if (mapVariable.get(x) != null && (!mapVariable.get(x).isEmpty()) && mapVariable.get(x).contains(y)) {
                    addPoint(pointsMap, x, y);
                }
            }
        }
        return pointsMap;
    }

    private static void addPoint(Map<Integer, Set<Integer>> positionsMap, Integer x, Integer y) {
        if (positionsMap.isEmpty()) {
            throw new IllegalStateException("Something went wrong!");
        } else if (positionsMap.get(x) == null) {
            positionsMap.put(x, Stream.of(y).collect(Collectors.toSet()));
        } else positionsMap.get(x).add(y);
    }

    private static Map<Integer, String[]> getData() {
        BufferedReader bufferedReader;
        String[] path0;
        String[] path1;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("src/main/java/pl/joajar/aoc2019/inputs/Day03.txt")));
            path0 = Arrays.stream(bufferedReader.readLine().split(",", 0)).toArray(String[]::new);
            path1 = Arrays.stream(bufferedReader.readLine().split(",", 0)).toArray(String[]::new);
            bufferedReader.close();
            Map<Integer, String[]> toReturn = new HashMap<>();
            toReturn.put(0, path0);
            toReturn.put(1, path1);
            return toReturn;

        } catch (FileNotFoundException e1) {
            System.out.println("Do not found a file!");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("IO error!");
            e2.printStackTrace();
        }
        return new HashMap<>();
    }
}
