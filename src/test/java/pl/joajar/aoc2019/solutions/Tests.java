package pl.joajar.aoc2019.solutions;

import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void day01_tests() {
        assertEquals(3457681, Day01.part1result());
        assertEquals(5183653, Day01.part2result());
    }

    @Test
    public void day02_tests() {
        assertEquals(3716293, Day02.part1result());
        assertEquals(6429, Day02.part2result());
    }

    @Test
    public void day03_tests() {
        assertEquals(627, Day03.part1result());
        assertEquals(13190, Day03.part2result());
    }

    @Test
    public void day04_tests() {
        assertEquals(1919, Day04.part1result());
        assertEquals(1291, Day04.part2result());
    }
}
