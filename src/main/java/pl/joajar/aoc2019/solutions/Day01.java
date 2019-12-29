package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day01 {
    public static int part1solution() {
        return prepareResult(getData());
    }

    private static int findFuel(int fuel) {
        return Math.max(fuel/3-2, 0);
    }

    private static int prepareResult(List<Integer> integerList) {
        return integerList.stream().mapToInt(Day01::findFuel).sum();
    }

    private static List<Integer> getData() {
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new FileReader(new File("src/main/java/pl/joajar/aoc2019/inputs/Day01.txt")));
            String row;
            List<Integer> workingList = new ArrayList<>();

            while ((row = bufferedReader.readLine()) != null) workingList.add(Integer.parseInt(row));

            return workingList;

        } catch (FileNotFoundException e1) {
            System.out.println("Do not found a file!");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("IO error!");
            e2.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e3) {
                System.out.println("IO error!");
                e3.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
