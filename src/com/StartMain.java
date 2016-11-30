/**
 * 
 */
package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL ;
import java.net.URLConnection ;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
/**
 * @author Jens
 *
 */
public class StartMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
	    String search = "Ansip";
	    String charset = "UTF-8";
	    int pages=5;
	    String fileName = "/javacode/urls.txt";
		File filelist = new File(fileName);
		if (!filelist.exists()) {
			filelist.createNewFile();
		}				
		FileWriter lw = new FileWriter(filelist.getAbsoluteFile());
		BufferedWriter blw = new BufferedWriter(lw);
	    for (int i=0; i<pages; i++){
	    	int insert=i*8+1;
	    	String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&rsz=8&start="+insert+"&q=";
	    	URL url = new URL(google + URLEncoder.encode(search, charset));
	    	Reader reader = new InputStreamReader(url.openStream(), charset);
	    	GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
	    	System.out.println(insert);
	    	System.out.println(results.getResponseData().getResults());
			
	    	for(int r=0;r<8;r++){
			try {
				// get URL content
				String toFile=results.getResponseData().getResults().get(r).getUrl()+System.getProperty("line.separator");

		
				blw.write(toFile);
				
				url = new URL(results.getResponseData().getResults().get(r).getUrl());
				URLConnection conn = url.openConnection();
	 
				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(
	                               new InputStreamReader(conn.getInputStream()));
	 
				String inputLine;
	 
				//save to this filename
				fileName = "/javacode/pages/page"+((i*8)+r)+".html";
				File file = new File(fileName);
	 
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				//use FileWriter to write file
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
	 
				while ((inputLine = br.readLine()) != null) {
					bw.write(inputLine);
				}
	 
				bw.close();
				br.close();
	 
				System.out.println("Done");
	 
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}}
	    }blw.close();

	}
	public class GoogleResults {

	    private ResponseData responseData;
	    public ResponseData getResponseData() { return responseData; }
	    public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
	    public String toString() { return "ResponseData[" + responseData + "]"; }



	}
    static class ResponseData {
        private List<Result> results;
        public List<Result> getResults() { return results; }
        public void setResults(List<Result> results) { this.results = results; }
        public String toString() { return "Results[" + results + "]"; }
    }

    static class Result {
        private String url;
        private String title;
        public String getUrl() { return url; }
        public String getTitle() { return title; }
        public void setUrl(String url) { this.url = url; }
        public void setTitle(String title) { this.title = title; }
        public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
    }
}
