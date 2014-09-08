package com.DaveNFS.medicine.DataDownload;


import java.util.ArrayList;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scraper implements Scrape {
										

	// This web scrapers relies on the mayoclinic.org 
	// Structure of the site may change over time, this implementation works as of 09/07/2014
	
	public static final String keyTerms[] = { "definition", "symptoms", "causes", "risk-factors",
												"treatment", "lifestyle-home-remedies", 
												"alternative-medicine", "prevention" };
	
	public HashSet<String> diseasesAndConditions = new HashSet<String>();
	
	public String getPageSource(String letter) {
		String output = null; 
		
		
		return output;
	}

	public ArrayList<String> getDiseasesAndConditionsLinks(String letter) {
		// find all the links which contain http://www.mayoclinic.org/diseases-conditions/
		String url = "http://www.mayoclinic.org/diseases-conditions/index?letter="+letter; 
		ArrayList<String> output = new ArrayList<String>();
		
		try{
			Document doc = Jsoup.connect(url).timeout(10*1000).get();
			Elements links = doc.select("a[href]");
			for(Element link : links){
				// System.out.println(link.toString());
				if(link.toString().startsWith("<a href=\"/diseases-conditions/"))
				{
					System.out.println(link.toString());
					output.add(link.toString());
					diseasesAndConditions.add(link.toString());
				}
			}
			
		}catch(Exception e){e.printStackTrace();}
		
		return output; 
	}

	
	public void saveDiseaseAndConditions(String letter) {
		
		ArrayList<String> list = this.getDiseasesAndConditionsLinks(letter);
		String urlBase = "http://www.mayoclinic.org";
		
		try{
			for(String link:list){
				String temp = link.split("\"")[1];
				String url = urlBase + temp; 
				System.out.println(url);
				Document doc = Jsoup.connect(url).timeout(10*1000).get();
				
				
			}
			
		}catch(Exception e){e.printStackTrace();}
		
	}

	public void test(){
		try{
			String url = "http://www.mayoclinic.org/diseases-conditions/achalasia/basics/symptoms/con-20024482";
			Document doc = Jsoup.connect(url).timeout(10*1000).get();
			// System.out.println(doc.toString());
			String a =  "http://www.mayoclinic.org/diseases-conditions/achalasia/basics/definition/con-20020580";
			String b = "http://www.mayoclinic.org/diseases-conditions/achalasia/basics/symptoms/con-20020580";
			
			Document doc1 = Jsoup.connect(a).timeout(10000).get();
			Document doc2 = Jsoup.connect(b).timeout(10000).get();
			
			System.out.println(doc1.toString().contains("/basics/symptoms/"));
		
			
			
		}catch(Exception e){e.printStackTrace();}
		
	}
	
	
	public void test2(){
		try{
			String url = "http://www.mayoclinic.org/diseases-conditions/acne/basics/risk-factors/con-20020580";
			String name = "acne";
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements divs = doc.select("div");
			for(Element div : divs)
			{
				
				System.out.println(div.toString());
				
			}
			
		}catch(Exception e){e.printStackTrace();}
	}
	
}
