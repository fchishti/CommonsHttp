package com.fc.commons.http;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class HttpGetTest {

	private static String mockGetServiceUrl = "https://jsonplaceholder.typicode.com/posts";
	private static String mockGetServiceUrlParameters = "https://jsonplaceholder.typicode.com/comments";
	
	@Test
	public void doGetTest() {
		try {
			HttpGet.doGet(mockGetServiceUrl);
		} catch (HttpException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void doGetWithParametersTest() {
		try {
			HttpGet.doGetWithResponse(mockGetServiceUrlParameters, Collections.singletonMap("postId", "2"));
		} catch (HttpException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void doGetTestWithResponse() {
		try {
			String response = HttpGet.doGetWithResponse(mockGetServiceUrl);
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
	public void doGetWithParametersTestWithResponse() {
		try {
			String response = HttpGet.doGetWithResponse(mockGetServiceUrlParameters, Collections.singletonMap("postId", "2"));
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
	public void doGetTestWithIncorrectUrl() {
		try {
			HttpGet.doGet("test.test");
		} catch (HttpException e) {
			e.printStackTrace();
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
}
