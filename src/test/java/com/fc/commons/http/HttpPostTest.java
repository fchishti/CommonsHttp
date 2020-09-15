package com.fc.commons.http;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class HttpPostTest {

	private static String mockPostServiceUrl = "https://jsonplaceholder.typicode.com/posts";
	private static String data = "{'title' : 'foo', 'body' : 'bar', 'userId' : 1}";
	
	@Test
	public void doPostTestWithResponse() {
		try {
			String response = HttpPost.doPost(mockPostServiceUrl, data);
			HttpConfig.LOGGER.info("***** RESPONSE *****");
			HttpConfig.LOGGER.info(response);
			assertTrue(!StringUtils.isEmpty(response));
		} catch (HttpException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void doPostTestWithIncorrectUrl() {
		try {
			String response = HttpPost.doPost("test.test", data);
			HttpConfig.LOGGER.info("***** RESPONSE *****");
			HttpConfig.LOGGER.info(response);
			assertTrue(StringUtils.isEmpty(response));
		} catch (HttpException e) {
			e.printStackTrace();
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
}
