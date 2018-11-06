package com.genetic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenomeTest {

    @Test
    public void TestingCompareTo(){
        String answer = "unicorn";
        Genome big = Genome.createGenome(answer,"unijorm");
        Genome small = Genome.createGenome(answer,"popcorn");
        assertEquals(1,big.compareTo(small));
        assertEquals(-1,small.compareTo(big));
        assertEquals(0,big.compareTo(big));
    }

}