/**
 * 
 */
package ippoz.utils.scripting;

import ippoz.utils.logging.AppLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tommy
 *
 */
public class ScriptUtils {
	
	public static boolean isWindows(){
		return System.getProperty("os.name").toUpperCase().contains("WIN");
	}
	
	public static boolean isUNIX(){
		return System.getProperty("os.name").toUpperCase().contains("UNIX");
	}
	
	public static List<String> runRawScriptInto(String path, String args, boolean setOnFolder) throws IOException {
		Process p;
		LinkedList<String> outList = new LinkedList<String>();
		BufferedReader reader = null;
		String script = buildScript(path);
		if(setOnFolder)
			p = Runtime.getRuntime().exec(script + " " + args, null, new File((new File(path)).getAbsolutePath().replaceAll((new File(path)).getName(), "")));
		else p = Runtime.getRuntime().exec(script + " " + args);
		String readed;
		reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((readed = reader.readLine()) != null) {
            outList.add(readed);
        }
        reader.close();
		return outList;
	}
	
	public static LinkedList<String> runScriptInto(String path, String args, boolean setOnFolder) throws IOException {
		Process p;
		LinkedList<String> outList = new LinkedList<String>();
		BufferedReader reader = null;
		String script = buildScript(path);
		if(setOnFolder)
			p = Runtime.getRuntime().exec(script + " " + args, null, new File((new File(path)).getAbsolutePath().replaceAll((new File(path)).getName(), "")));
		else p = Runtime.getRuntime().exec(script + " " + args);
		String readed;
		reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((readed = reader.readLine()) != null) {
            outList.add(readed);
        }
        reader.close();
		return outList;
	}
	
	public static Process runScript(String path, String args, boolean setOnFolder, boolean viewOutput, boolean waitFor) throws IOException{
		Process p;
		BufferedReader reader = null;
		String script = buildScript(path);
		if(setOnFolder)
			p = Runtime.getRuntime().exec(script + " " + args, null, new File((new File(path)).getAbsolutePath().replaceAll((new File(path)).getName(), "")));
		else if(args != null)
			p = Runtime.getRuntime().exec(script + " " + args);
		else p = Runtime.getRuntime().exec(script);
		if(waitFor){
			String readed;
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((readed = reader.readLine()) != null) {
	            if(viewOutput)
	            	System.out.println(readed);
	        }
	        reader.close();
        }
		AppLogger.logInfo(ScriptUtils.class, "Executed \"" + script + "\"");
		return p;
	}
	
	public static Process runScript(String path, String args, boolean setOnFolder, boolean viewOutput) throws IOException{
		Process p;
		BufferedReader reader = null;
		String script = buildScript(path);
		if(setOnFolder)
			p = Runtime.getRuntime().exec(script + " " + args, null, new File((new File(path)).getAbsolutePath().replaceAll((new File(path)).getName(), "")));
		else p = Runtime.getRuntime().exec(script + " " + args);
		if(viewOutput){
			reader = new BufferedReader(new InputStreamReader(
	        p.getInputStream()));
	        while (reader.ready()) {
	            System.out.println(reader.readLine());
	        }
	        reader.close();
        }
		//AppLogger.logInfo(Probe.class, "Executed \"" + script + "\"");
		return p;
	}
	
	private static String buildScript(String path){
		String script = path;
		if(path.endsWith(".jar"))
			script = "java -jar " + path;
		return script;
	}
	
}
