package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.Arrays;

public class Day04 {
    public static int part1result() {
        return calculateResult(false);
    }

    public static int part2result() {
        return calculateResult(true);
    }

    public static int calculateResult(boolean is2partResult) {
        int[] dataArray = getData();
        int resultCounter = 0;

        for (int subsequentNumber = dataArray[0]; subsequentNumber <= dataArray[1]; subsequentNumber++) {

            String[] workingArray = String.valueOf(subsequentNumber).split("");
            int sameDigitCounters = 0;
            boolean additionalChecker = false;

            for (int i = 0; i < workingArray.length - 1; i++) {
                if (Integer.parseInt(workingArray[i]) > Integer.parseInt(workingArray[i+1])) {
                    sameDigitCounters = 0;
                    break;
                }
                if (Integer.parseInt(workingArray[i]) == Integer.parseInt(workingArray[i+1])) {
                    sameDigitCounters++;
                }
            }

            if ((!is2partResult)
                    || ((Integer.parseInt(workingArray[0]) == Integer.parseInt(workingArray[1]))
                    && (Integer.parseInt(workingArray[1]) != Integer.parseInt(workingArray[2])))
                    || ((Integer.parseInt(workingArray[4]) == Integer.parseInt(workingArray[5]))
                    && (Integer.parseInt(workingArray[3]) != Integer.parseInt(workingArray[4])))
            ) {
                additionalChecker = true;
            }

            for (int i = 0; i < workingArray.length - 3; i++) {
                if (
                        (Integer.parseInt(workingArray[i]) != Integer.parseInt(workingArray[i+1]))
                                && (Integer.parseInt(workingArray[i+1]) == Integer.parseInt(workingArray[i+2]))
                                && (Integer.parseInt(workingArray[i+2]) != Integer.parseInt(workingArray[i+3]))
                ) {
                    additionalChecker = true;
                }
            }

            if ((sameDigitCounters > 0) && additionalChecker) resultCounter++;

        }

        return resultCounter;
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
