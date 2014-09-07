package com.DaveNFS.medicine.DataDownload;

import java.util.ArrayList;

public interface Scrape {

	// contains API for web page source gathering, parsing and saving
	
	// get the raw page source (diseases and conditions listed alphabetically
	public String getPageSource(String letter);
	
	// get Diseases and conditions list on that page
	public ArrayList<String> getDiseasesAndConditionsLinks(String letter);
	
	// iterate through the disease-conditions list and save data to file
	public void saveDiseaseAndConditions(String letter);
	
}
