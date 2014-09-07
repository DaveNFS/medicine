package com.DaveNFS.medicine.DataDownload;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scraper webScraper = new Scraper();
        webScraper.getDiseasesAndConditionsLinks("Z");
    }
}
