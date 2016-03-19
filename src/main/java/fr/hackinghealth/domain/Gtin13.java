package fr.hackinghealth.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Gtin13 {

	
	
	private Map<GTIN_AI, String> mapData = new HashMap<>();
	
	
	
	public enum GTIN_AI {
		AI_01_GTIN,
		AI_02_LOGISTIC_UNIT,
		AI_10_BATCH_LOT_NUMBER,
		AI_11_PRODUCTION_DATE,
		AI_13_PACKAGING_DATE,
		AI_15_BEST_BEFORE_DATE,
		AI_17_EXPIRATION_DATE,
		AI_21_SERIAL_NUMBER;
	}
	
	
	
	public void set(GTIN_AI applicationIdent, String data) {
		mapData.put(applicationIdent, data);
	}
	
	
	public String get(GTIN_AI applicationIdent) {
		return mapData.get(applicationIdent);
	}

	
	public  String toString() {
		final StringBuffer strBuf = new StringBuffer();
		for( Entry<GTIN_AI, String> entry : mapData.entrySet()) {
			strBuf.append(String.format("%s %s\n", entry.getKey().toString(), entry.getValue()));
		}
		
		return strBuf.toString();
	}
}
