package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map.Entry;

 
public class Cont3 {
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
								
								if( Arrays.asList(words).contains(search)){
									int r=Arrays.asList(words).indexOf(search);
									int newdist=Math.abs(r-i);
								        if(newdist<distance){
											distance=newdist;
										}
								}
						
								if (map1.containsKey(word)) {
									map1.put(word, map1.get(word) + 1);
								//	if( Arrays.asList(words).contains(search)){
									if(map3.get(word)>distance){
										map3.put(word, distance);
								//	}
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
			String fileName = "/javacode/listwords.txt";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader("/javacode/list.txt"));
		    String str = br.readLine();
		    
			while (str != null) {
				    str=str.toLowerCase();
					String[] words =  str.split(" ");
					for(int i=0;i<words.length;i++){
						String word=words[i];
						if(map1.containsKey(word)){
						if(map1.get(word)>relevance){
							bw.write(word+" ||| "+map1.get(word) + " ||| "+map2.get(word)+ " ||| "+map3.get(word)+"\n");
						}
			        }}
					str = br.readLine();
					
			}
			bw.close();
			System.out.println("Done");
			}catch(IOException e) {
			e.printStackTrace();}
		System.out.println("SuperDone");
		}
	
	}

