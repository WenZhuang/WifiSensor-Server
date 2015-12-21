package com.bupt.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import com.bupt.dao.PersonDao;
import com.bupt.model.Person;
	
public class DataProcess{
	public static void main(String[] args) {
		String path = "\\20150301";
		String str = null;
		String mac = null;
		int rssi;
		int timeStamp;
		int limit=300;
		int dwellTime;
		HashMap<String,Person> map=new HashMap<String,Person>();
			
		try {
			File file = new File(path);			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
						
			while ((str = br.readLine()) != null) {
				String[] substr = str.split("\\|");
				mac = substr[0];
				rssi=Integer.parseInt(substr[1]);
				timeStamp=Integer.parseInt(substr[2]);
				
				if(!map.containsKey(mac)){
					Person newPerson=new Person();
					newPerson.setIntime(timeStamp);
					newPerson.setOuttime(timeStamp);
					newPerson.setDwellTime(0);
					map.put(mac, newPerson);
				}else{
					Person oldPerson=map.get(mac);
					if(timeStamp-oldPerson.getOuttime()<=limit){
						oldPerson.setOuttime(timeStamp);
					}else{
						dwellTime=oldPerson.getOuttime()-oldPerson.getIntime()+1;
						PersonDao.insert(mac, oldPerson.getIntime(), oldPerson.getOuttime(), dwellTime);
						dwellTime=0;
						oldPerson.setIntime(timeStamp);
						oldPerson.setOuttime(timeStamp);
					}
				}				
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

