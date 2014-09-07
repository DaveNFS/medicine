package com.DaveNFS.medicine.DataDownload;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scraper implements Scrape {

	// This web scrapers relies on the mayoclinic.org 
	// Structure of the site may change over time, this implementation works as of 09/07/2014
	
	
	public String getPageSource(String letter) {
		String output = null; 
		
		
		return output;
	}

	public ArrayList<String> getDiseasesAndConditionsLinks(String letter) {
		// find all the links which contain http://www.mayoclinic.org/diseases-conditions/
		String url = "http://www.mayoclinic.org/diseases-conditions/index?letter="+letter; 
		ArrayList<String> output = new ArrayList<String>();
		
		try{
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			for(Element link : links){
				// System.out.println(link.toString());
				if(link.toString().startsWith("<a href=\"/diseases-conditions/"))
				{
					//System.out.println(link.toString());
					output.add(link.toString());
				}
			}
			
		}catch(Exception e){e.printStackTrace();}
		
		return output; 
	}

	
	public void saveDiseaseAndConditions(String letter) {
		
		ArrayList<String> list = this.getDiseasesAndConditionsLinks(letter);
		
		try{
			for(String link:list){
				
			}
			
		}catch(Exception e){e.printStackTrace();}
		
	}

	
	
}
