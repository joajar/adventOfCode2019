package pl.joajar.aoc2019.solutions;

import java.io.*;
import java.util.Arrays;

public class Day02 {
    public static int part1result(){
        return calculateFirstInteger(12, 2);
    }

    public static int part2result() {
        int minValue = 0, maxValue = 99, noun = minValue-1, verb = minValue, desiredResult = 19690720, result;

        do {
            if (noun == maxValue) {
                noun = minValue; verb++;
            } else noun++;

            result = calculateFirstInteger(noun, verb);

        } while (result != desiredResult && (verb != maxValue || noun != maxValue));

        if (result == desiredResult) return 100 * noun + verb;
        return -1;
    }

    public static int calculateFirstInteger(int noun, int verb) {
        int[] workingArray = getData();
        workingArray[1] = noun;
        workingArray[2] = verb;

        for (int index = 0; index <= workingArray.length / 4; index++) {
            switch (workingArray[4*index]) {
                case 1:
                    workingArray[workingArray[4*index + 3]] = workingArray[workingArray[4*index + 1]] + workingArray[workingArray[4*index + 2]];
                    break;
                case 2:
                    workingArray[workingArray[4*index + 3]] = workingArray[workingArray[4*index + 1]] * workingArray[workingArray[4*index + 2]];
                    break;
                case 99:
                    return workingArray[0];
                default:
                    System.out.println("Unknown opcode - something went wrong!");
                    return -1;
            }
        }
        return workingArray[0];
    }

    private static int[] getData() {
        BufferedReader bufferedReader;
        try {

            bufferedReader = new BufferedReader(new FileReader(new File("src/main/java/pl/joajar/aoc2019/inputs/Day02.txt")));
            int[] workingArray = Arrays.stream(bufferedReader.readLine().split(",", 0)).mapToInt(Integer::parseInt).toArray();
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
