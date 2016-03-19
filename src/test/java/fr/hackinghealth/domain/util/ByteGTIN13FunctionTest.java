package fr.hackinghealth.domain.util;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import fr.hackinghealth.domain.Gtin13;

public class ByteGTIN13FunctionTest {

	@Test
	public void test_decodage() {
		final byte[] data = "01034009391694601715090021220211029577760633811027438".getBytes();
		
		ByteGTIN13Function fixture = new ByteGTIN13Function();
		Gtin13 result = fixture.apply(data);
		
		Assertions.assertThat(result).isNotNull();
		
		
	}

}
