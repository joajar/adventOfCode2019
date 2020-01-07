package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.Arrays;

public class Day04 {
    public static void printData() {
        System.out.println(Arrays.toString(getData()));
    }

    private static int[] getData() {
        BufferedReader bufferedReader;
        try {

            bufferedReader = new BufferedReader(new FileReader(new File("src/main/java/pl/joajar/aoc2019/inputs/Day04.txt")));
            int[] workingArray = Arrays.stream(bufferedReader.readLine().split("-", 0)).mapToInt(Integer::parseInt).toArray();
            bufferedReader.close();
            return workingArray;

        } catch (FileNotFoundException e1) {
            System.out.println("Do not found a file!");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("IO error!");
            e2.printStackTrace();
        }
        return new int[0];
    }
}
