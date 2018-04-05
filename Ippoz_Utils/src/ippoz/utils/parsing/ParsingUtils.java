/**
 *
 */
package ippoz.utils.parsing;

import ippoz.utils.logging.AppLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Tommy
 *
 */
public class ParsingUtils {
	
	public static boolean occursIn(int[] array, int item){
		for(int i=0;i<array.length;i++){
			if(array[i] == item)
				return true;
		}
		return false;
	}
	
	public static int countOccurrencesOf(String bigStr, String smallStr){
		int lastIndex = 0;
		int count = 0;
		while(lastIndex != -1){
		    lastIndex = bigStr.indexOf(smallStr,lastIndex);
		    if(lastIndex != -1){
		        count ++;
		        lastIndex += smallStr.length();
		    }
		}
		return count;
	}
	
	public static HashMap<String, String> readPairsFromCSV(String filename) {
		HashMap<String, String> pairMap = new HashMap<String, String>();
		BufferedReader reader;
		String readed;
		try {
			reader = new BufferedReader(new FileReader(new File(filename)));
			while(reader.ready()){
				readed = reader.readLine().trim();
				if(readed.length() > 0 && readed.contains(",")){
					pairMap.put(readed.split(",")[0].trim().toUpperCase(), readed.split(",")[1].trim());
				}
			}
			reader.close();
		} catch(Exception ex){
			AppLogger.logException(ParsingUtils.class, ex, "Unable to read pair CSV: '" + filename + "'");
		}
		return pairMap;
	}

	public static long parseStringTime(String stringData, int hourOffset) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(format.parse(stringData));
			cal.add(Calendar.HOUR, hourOffset);
			return cal.getTimeInMillis();
		} catch (ParseException ex) {
			AppLogger.logException(ParsingUtils.class, ex, "Unable to convert data");
		}
		return 0;
	}
	
	public static String formatMillis(long dateMillis){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date(dateMillis));
	}
	
	public static Date getDateFromString(String dateString){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			AppLogger.logException(ParsingUtils.class, e, "Unable to parse date '" + dateString + "'");
			return null;
		}
	}

}
