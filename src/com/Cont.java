package com;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

 
public class Cont {
	public static ArrayList<String> listFilesForFolder(File folder) {
		ArrayList<String> rry = new ArrayList<String>();
		for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            rry.add(fileEntry.getName());
	        }
	    }
	    return rry;
	}


	public static void main(String[] args) {
		File folder = new File("/javacode/pages/");
		ArrayList<String> files=listFilesForFolder(folder);
		for( String oneItem : files ) {
		try{
        BufferedReader br = new BufferedReader(new FileReader("/javacode/pages/"+oneItem));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        str = str.replace("<" , " ");
        str = str.replace("," , " ");
        str = str.replace(">" , " ");
        str = str.replace(":" , " ");
        str = str.replace("\"" , " ");
        str= str.replace("."," . ");
        str=str.toLowerCase();
        while (str != null) {
            sb.append(str);
            sb.append(System.lineSeparator());
            str = br.readLine();
            if(str !=null){
            	str = str.replace("<" , " ");
            	str = str.replace("," , " ");
            	str = str.replace(">" , " ");
            	str = str.replace(">" , " ");
            	str = str.replace(":" , " ");
            	str = str.replace("\"" , " ");
            	str= str.replace("."," . ");
            	str=str.toLowerCase();
            }
        }
        String everything = sb.toString();
        br.close();
        
 	
		String fileName = "/javacode/text/"+oneItem;
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
 
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(everything);
		bw.close();
		System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		System.out.println("SuperDone");
	}
}

