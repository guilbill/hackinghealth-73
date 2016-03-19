package fr.hackinghealth.domain.util;

import java.nio.ByteBuffer;
import java.util.function.Function;   

import fr.hackinghealth.domain.Gtin13;
import fr.hackinghealth.domain.Gtin13.GTIN_AI;

public class ByteGTIN13Function implements Function<byte[],Gtin13>{

	@Override
	public Gtin13 apply(byte[] input) {
		final Gtin13 result = new Gtin13();
		ByteBuffer buffer = ByteBuffer.wrap(input);
		
		while ( buffer.hasRemaining()) {
			
			
			switch ( readAI(buffer)) {
			case 1:
				byte [] code = new byte[14];
				buffer.get(code);
				result.set(GTIN_AI.AI_01_GTIN, new String(code));
				break;
			case 10:
				result.set(GTIN_AI.AI_10_BATCH_LOT_NUMBER, readVariableString(buffer,20));
				break;
			case 17:
				result.set(GTIN_AI.AI_17_EXPIRATION_DATE, readVariableString(buffer,6));
				break;
			case 21:				
				result.set(GTIN_AI.AI_21_SERIAL_NUMBER, readVariableString(buffer,20));				
				break;
			}
		}
		
		return result;
	}

	
	private int readAI(ByteBuffer buffer) {
		byte high = buffer.get();
		byte low = buffer.get();
		int res =  (high - '0') *10 + (low -'0');
		
		
		return res;
	}
	
	private String readVariableString(final ByteBuffer buffer, int max) {
		final ByteBuffer bufferLot = ByteBuffer.allocate(max);
		byte digit;
		while (  bufferLot.position() < bufferLot.capacity() && buffer.hasRemaining() ) {
			digit = buffer.get();
			if ( digit == 29 ) {
				break;
			}
			bufferLot.put(digit);
		}
		bufferLot.flip();
		final byte[] res = new byte[bufferLot.limit()];
		bufferLot.get(res);
		return new String(res);
		
	}
	
}
