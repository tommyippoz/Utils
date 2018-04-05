/**
 * 
 */
package ippoz.utils.preferences;

import ippoz.utils.logging.AppLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Tommy
 *
 */
public class PreferencesManager {
	
	public static final String PREF_INPUT_FOLDER = "INPUT_FOLDER";
	
	public static final String PREF_OUTPUT_FOLDER = "OUTPUT_FOLDER";
	
	public static final String PREF_SETTINGS_FOLDER = "SETTINGS_FOLDER";
	
	public static final String PREF_DATABASE_TYPE = "DATABASE_ENGINE";
	
	public static final String PREF_DATABASE_NAME = "DATABASE_NAME";
	
	public static final String PREF_DATABASE_USER = "DATABASE_USERNAME";
	
	public static final String PREF_DATABASE_PSW = "DATABASE_PASSWORD";
	
	public static final String PREF_DATABASE_TABLE = "DATABASE_TABLE";
	
	private HashMap<String, String> preferences;
	
	public PreferencesManager(){
		preferences = new HashMap<String, String>();
	}
	
	public PreferencesManager(File prefFile){
		try {
			preferences = loadPreferences(prefFile, null);
		} catch (IOException ex) {
			AppLogger.logException(getClass(), ex, "Unable to load main preferences file");
		}
	}
	
	public PreferencesManager(String defaultPreferencesFile) {
		this(new File(defaultPreferencesFile));
	}

	public String getPreference(String tag){
		if(preferences.containsKey(tag))
			return preferences.get(tag);
		else {
			AppLogger.logError(getClass(), "KeyNotFound", "Key '" + tag + "' is not a preference");
			return null;
		}
	}
	
	public void addPreference(String tag, String value){
		preferences.put(tag, value);
	}
	
	public boolean hasPreference(String loaderPrefFile) {
		return preferences.containsKey(loaderPrefFile);
	}
	
	public static HashMap<String, String> loadPreferences(File prefFile, String[] tags) throws IOException {
		String readed, tag, value;
		BufferedReader reader;
		HashMap<String, String> map = new HashMap<String, String>();
		if(prefFile.exists()){
			reader = new BufferedReader(new FileReader(prefFile));
			while(reader.ready()){
				readed = reader.readLine();
				if(readed.length() > 0) {
					if(readed.contains("=") && readed.split("=").length == 2){
						tag = readed.split("=")[0];
						value = readed.split("=")[1];
						if(tags != null && tags.length > 0){
							for(String current : tags){
								if(current.toUpperCase().equals(tag.toUpperCase())){
									map.put(tag.trim(), value.trim());
									break;
								}
							}
						} else map.put(tag.trim(), value.trim());
					}
				}
			}
			reader.close();
		} else {
			AppLogger.logInfo(PreferencesManager.class, "Unexisting preference file: " + prefFile.getAbsolutePath());
		}
		return map;
	}

}
