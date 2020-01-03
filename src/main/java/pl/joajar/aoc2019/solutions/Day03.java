package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day03 {
    public static void printData() {
        Map<Integer, String[]> ourData = getData();
        System.out.println("First wire data: " + Arrays.toString(ourData.get(0)));
        System.out.println("Second wire data: " + Arrays.toString(ourData.get(1)));
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
