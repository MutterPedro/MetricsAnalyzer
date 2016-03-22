package br.com.metricsanalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RetrieveFromHtml {
	
	public static List<Metric> getMetrics(File file) throws IOException{
		List<Metric> list = new ArrayList<Metric>();
		Document doc = Jsoup.parse(file,"UTF-8","");
		
		Elements tables = doc.getElementsByClass("bodyTable");
		Elements rows = tables.get(0).getElementsByTag("td");
		for (int i=0;i<rows.size();i+=2) {
		  String name = rows.get(i).text();
		  String value = rows.get(i+1).text();
		  Metric m = new Metric(name,value,"X","X");
		  list.add(m);		  
		}
		
		return list;
	}
	
}
