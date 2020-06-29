package com.cuit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void tets1(){
        String movieInfo = ReadCsvTest.getMovieInfo("Po");
        System.out.println(movieInfo);

    }
}
