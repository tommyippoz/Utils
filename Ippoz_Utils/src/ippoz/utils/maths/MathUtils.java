/**
 * 
 */
package ippoz.utils.maths;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Tommy
 *
 */
public class MathUtils {
	
	public static Double calcAvg(LinkedList<Double> values){
		if(values != null && values.size() > 0)
			return calcAvg(values.toArray(new Double[values.size()]));
		else return 0.0;
	}
	
	public static Double calcAvg(Double[] values){
		double mean = 0;
		for(Double d : values){
			mean = mean + d;
		}
		return mean / values.length;
	}
	
	public static Double calcMedian(LinkedList<Double> values){
		if(values != null && values.size() > 0)
			return calcMedian(values.toArray(new Double[values.size()]));
		else return 0.0;
	}
	
	public static Double calcMedian(Double[] values){
		Arrays.sort(values);
		return values[(int)(values.length/2)];
	}
	
	public static Double calcMode(Double[] values){
		int freq = 0, modeFreq = 0;
		double mode = 0;
		Arrays.sort(values);
		for(int i=0;i<values.length;i++){
			if(i > 0){
				if(values[i] == values[i-1])
					freq++;
				else {
					if(freq >= modeFreq){
						mode = values[i-1];
						modeFreq = freq;
						freq = 1;
					}
				}
			} else freq++;
		}
		return mode;
	}
	
	public static Double calcStd(LinkedList<Double> values){
		if(values != null && values.size() > 0)
			return calcStd(values.toArray(new Double[values.size()]), calcAvg(values.toArray(new Double[values.size()])));
		else return 0.0;
	}
	
	public static Double calcStd(Double[] values, Double mean){
		double std = 0;
		for(Double d : values){
			std = std + Math.pow(d-mean, 2);
		}
		return std / values.length;
	}

	public static Double max(Collection<Double> values) {
		return Collections.max(values);
	}

}
