package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map.Entry;

 
public class Cont2 {
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
		String search="ansip";
		int relevance=0;
		HashMap<String, Integer> map1 = new HashMap<>();
		HashMap<String, Integer> map2 = new HashMap<>();
		HashMap<String, Integer> map3 = new HashMap<>();
		
		File folder = new File("/javacode/pages/");
		ArrayList<String> files=listFilesForFolder(folder);
		
		for( String oneItem : files ) {
			try{
				BufferedReader br = new BufferedReader(new FileReader("/javacode/text/"+oneItem));
				int foo=99;
			    StringBuilder builder = new StringBuilder();
			    for (int i = 0; i < oneItem.length(); i++) {
			    	char c = oneItem.charAt(i);
			        if (Character.isDigit(c)) {
			            builder.append(c);
			        }
			    }
			    String filenr=builder.toString();
			    if(filenr.length()>0){foo = Integer.parseInt(filenr);}
			    System.out.println("FILENR:"+filenr);
			    String str = br.readLine();
				while (str != null) {
						String[] words =  str.split(" ");
						

						for(int i=0;i<words.length;i++){
								String word=words[i];
								int distance=99;
								for(int r=0;r<words.length;r++){
									if(words[r].equals(search)){
										if(Math.abs(r-i)<distance){
											distance=Math.abs(r-i);
										}
									}
								}
						
								if (map1.containsKey(word)) {
									map1.put(word, map1.get(word) + 1);
									if(map3.get(word)>distance){
										map3.put(word, distance);
									}
								} else {
									map1.put(word, 0);
									map2.put(word, foo);
									map3.put(word, distance);
								}
								}
						str = br.readLine();
				}
        br.close();}catch (IOException e) {	e.printStackTrace();}
		}
		
		try{
			String fileName = "/javacode/words.txt";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<Entry<String, Integer>> it = map1.entrySet().iterator();
   
		    while (it.hasNext()) {
		        Entry<String, Integer> pairs = it.next();
		        System.out.println(pairs.getValue());
		        if(pairs.getValue()>relevance){		        bw.write(pairs.getKey()+" ||| "+pairs.getValue() + " ||| " 
		        +map2.get(pairs.getKey())+ " ||| "+map3.get(pairs.getKey()) 
		        +"\n");
		        }
       		    }
			
			bw.close();
			System.out.println("Done");
			}catch(IOException e) {
			e.printStackTrace();}
		System.out.println("SuperDone");
		}
	
	}

