package br.com.metricsanalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RetrieveFromXml {
	
	public static List<Metric> getMetrics(File file){
		List<Metric> list = new ArrayList<Metric>();
		
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(file);
			
			//get the root element
			Element docEle = dom.getDocumentElement();

			//get a nodelist of elements
			NodeList nl = docEle.getElementsByTagName("Metric");
			if(nl != null && nl.getLength() > 0) {
				for(int j = 0 ; j < nl.getLength();j++) {

					Element el = (Element)nl.item(j);
					Element values;
					values =  (Element) el.getElementsByTagName("Values").item(0);
					boolean once = false;
					if(values == null){
						values = (Element) el.getElementsByTagName("Value").item(0);
						once = true;
					}
							
					Metric metric = getMetricFromElement(el,values,once);

					list.add(metric);
				}
			}


		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return list;
	}
	
	private static Metric getMetricFromElement(Element el,Element values,boolean once){

		String name = el.getAttribute("description");
		String avg ,stddev = "X",max="X";
		if(!once){
			avg = values.getAttribute("avg");
			stddev = values.getAttribute("stddev");
			max= values.getAttribute("max");
		} else{
			avg = values.getAttribute("value");
		}
		
		Metric metric = new Metric(name,avg,stddev,max);

		return metric;
	}

}
