package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.*;

public class Day03 {
    public static int part1result() {
        return calculateResult(false);
    }

    public static int part2result() {
        return calculateResult(true);
    }

    private static int calculateResult(Boolean is2partResult) {
        Map<Integer, String[]> ourData = getData();

        /*  I've used the data structure shown below as a data set corresponding to the particular wire.
         *  In brackets you may find the meaning of particular variables set used.
         *
         *  Map<Integer (x variable i.e. the first coordinate of the current grid point),
         *      Map<Integer (y variable i.e. the second coordinate of the point),
         *          Map<Boolean (always FALSE in case of calculation for the 1st wire, used only for calculation conducted for the 2nd wire:
         *                              TRUE if the current grid point is a two wires intersection point and FALSE in the other case),
         *              Integer(number of steps needed to reach this grid point)
         *              >
         *          >
         *      >
         */

        Map<Integer, Map<Integer, Map<Boolean, Integer>>> mapOf1stWirePositions =
                findPositionsForWire(new HashMap<>(), ourData.get(0), false);

        Map<Integer, Map<Integer, Map<Boolean, Integer>>> mapOf2ndWirePositions =
                findPositionsForWire(mapOf1stWirePositions, ourData.get(1), true);


        int minimalDistance = Integer.MAX_VALUE, minimalPathLength = Integer.MAX_VALUE;

        for (Integer int1 : mapOf2ndWirePositions.keySet()) {
            for (Integer int2 : mapOf2ndWirePositions.get(int1).keySet()) {
                if (mapOf2ndWirePositions.get(int1).get(int2).containsKey(true)) {

                    minimalDistance = Math.min(minimalDistance, Math.abs(int1) + Math.abs(int2));

                    minimalPathLength = Math.min(minimalPathLength,
                            mapOf2ndWirePositions.get(int1).get(int2).get(true) + mapOf1stWirePositions.get(int1).get(int2).get(false));

                }
            }
        }

        return is2partResult ? minimalPathLength : minimalDistance;
    }

    private static Map<Integer, Map<Integer, Map<Boolean, Integer>>> findPositionsForWire(
            Map<Integer, Map<Integer, Map<Boolean, Integer>>> inputMap, String[] arrayVariable, boolean is2ndWire) {

        int x = 0, y = 0, stepsNumber = 0;

        // building MUTABLE ( <- HashMap) data structure to return
        Map<Integer, Map<Integer, Map<Boolean, Integer>>> workingMap = new HashMap<>(
                Map.of(x, new HashMap<>(
                        Map.of(y, new HashMap<>(
                                Map.of(false, stepsNumber)
                        ))
                ))
        );

        // feeding data structure with data
        for (String workingString : arrayVariable) {
            for (int i = 0; i < Integer.parseInt(workingString.substring(1)); i++) {

                switch (workingString.substring(0, 1)) {
                    case "R":
                        x++; break;
                    case "U":
                        y++; break;
                    case "L":
                        x--; break;
                    case "D":
                        y--; break;
                    default:
                        throw new IllegalStateException("Something went wrong!");
                }

                addPoint(workingMap,
                        x,
                        y,
                        is2ndWire && !inputMap.isEmpty() && inputMap.containsKey(x) && inputMap.get(x).containsKey(y),
                        ++stepsNumber);

            }
        }
        return workingMap;
    }

    private static void addPoint(Map<Integer, Map<Integer, Map<Boolean, Integer>>> positionsMap, Integer x, Integer y, boolean isCrossing, Integer distance) {
        if (positionsMap.isEmpty()) {
            throw new IllegalStateException("Something went wrong!");
        } else if (!positionsMap.containsKey(x)) {
            positionsMap.put(x, new HashMap<>(Map.of(y, new HashMap<>(Map.of(isCrossing, distance)))));
        } else if (!positionsMap.get(x).containsKey(y)) {
            positionsMap.get(x).put(y, new HashMap<>(Map.of(isCrossing, distance)));
        }
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
