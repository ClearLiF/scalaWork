package com.cuit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //ReadCsvTest.getMovieInfo("tess");
        String movieInfo = ReadCsvTest.getMovieInfo("Popcorn (2007)");
        System.out.println(movieInfo);

    }
}
