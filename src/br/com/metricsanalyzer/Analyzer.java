package br.com.metricsanalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Analyzer {

	public static void main(String[] args) throws IOException {
		
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		List<File> listOfXml = new ArrayList<File>();
		List<File> listOfHtml = new ArrayList<File>();
		
		if(listOfFiles != null){
			for(int i=0 ;i< listOfFiles.length;i++){
				if(listOfFiles[i].getName().endsWith(".xml") || listOfFiles[i].getName().endsWith(".XML")){
					listOfXml.add(listOfFiles[i]);					
				} else if(listOfFiles[i].getName().endsWith(".html") || listOfFiles[i].getName().endsWith(".html")){
					listOfHtml.add(listOfFiles[i]);					
				}
			}
		} else {
			System.out.println("Nenhum arquivo encontrado");
			return;
		}
		
		if(listOfXml.size() > 0){
			System.out.println("Analisando arquivos .xml");
			for(int i=0;i<listOfXml.size();i++){
				System.out.println("Analisando o arquivo "+listOfXml.get(i).getName()+" ...");
				List<Metric> list = RetrieveFromXml.getMetrics(listOfXml.get(i));			
				String name = listOfXml.get(i).getName().split(".xml")[0]+"_Analise.csv";
				
				FileWriter arq = new FileWriter(folder.getPath()+"/"+name); 
				PrintWriter gravarArq = new PrintWriter(arq); 
				gravarArq.printf("Arquivo texto resultante do processamento do "+listOfXml.get(i).getName() +" gerado pelo plugin Metrics\nCAMPOS;avg/value;stddev;max\n\n");
				for(int j=0;j<list.size();j++){
					gravarArq.printf("%s;\"%s\";\"%s\";\"%s\"\n",list.get(j).getName(),list.get(j).getAvg(),list.get(j).getStddev(),list.get(j).getMax());
				}		
				arq.close();
			}
		} else {
			System.out.println("Nenhum arquivo .xml encontrado");
		}
		
		if(listOfHtml.size() > 0){
			System.out.println("Analisando os arquivos .html");
			for(int i=0;i<listOfHtml.size();i++){
				System.out.println("Analisando o arquivo "+listOfHtml.get(i).getName()+" ...");
				List<Metric> list = RetrieveFromHtml.getMetrics(listOfHtml.get(i));
				String name = listOfHtml.get(i).getName().split(".html")[0]+"_Analise.csv";
				
				FileWriter arq = new FileWriter(folder.getPath()+"/"+name); 
				PrintWriter gravarArq = new PrintWriter(arq); 
				gravarArq.printf("Arquivo texto resultante do processamento do "+listOfHtml.get(i).getName() +" gerado pelo plugin CodePro\nCAMPOS;value\n\n");
				for(int j=0;j<list.size();j++){
					gravarArq.printf("%s;\"%s\"\n",list.get(j).getName(),list.get(j).getAvg());
				}		
				arq.close();
			}
		} else {
			System.out.println("Nenhum arquivo .html encontrado");
		}

	}

}
