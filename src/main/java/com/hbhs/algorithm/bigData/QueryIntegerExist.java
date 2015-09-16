package com.hbhs.algorithm.bigData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.BitSet;

public class QueryIntegerExist {
	public static void main(String[] args) {
		init10BillianData();
		BitSet bit = loadIntFromFile();
		int value = 50000;
		boolean isTrue = bit.get(value);
		System.out.println("Number: "+value+", isTrue: "+isTrue);
		
		value = 10000;
		isTrue = bit.get(value);
		System.out.println("Number: "+value+", isTrue: "+isTrue);
		
		System.out.println("@@@");
		System.out.println(bit.cardinality()+bit.size());
		System.out.println(bit.cardinality());
	}
	
	private static BitSet loadIntFromFile(){
		BitSet bit = new BitSet(2000000000);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(QueryIntegerExist.class
					.getClassLoader().getResourceAsStream("com/hbhs/algorithm/bigData/source/TwoBillionIntData.txt")));
			String line = reader.readLine();
			while(line!=null){
				if (line.endsWith("0000000")) {
					System.out.println(line);
				}
				
				Integer value = praseInt(line);
				if (value!=null&&value>0) {
					bit.set(value);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(reader!=null){
				try{reader.close();}catch(Exception e){}
			}
		}
		System.out.println("Load success.");
		return bit;
	}
	
	protected static void init10BillianData(){
		BufferedWriter writer = null;
		try {
			int max = 1000000;
			URL url = QueryIntegerExist.class
					.getClassLoader().getResource("com/hbhs/algorithm/bigData/source/TwoBillionIntData.txt");
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(url.getPath()))));
			int i = 1;
			while(i < max){
				double random = Math.random();
				if (random >0.15) {
					writer.write(i+"\n");
					writer.flush();
				}
				i++;
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(writer!=null){
				try{
					writer.close();
				}catch(Exception e){}
			}
		}
	}
	
	private static Integer praseInt(String arg0){
		try {
			return Integer.valueOf(arg0);
		} catch (Exception e) {
			return null;
		}
	}
}
