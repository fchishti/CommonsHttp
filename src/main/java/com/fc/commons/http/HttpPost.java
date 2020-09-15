package com.fc.commons.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * Simple HttpPost client
 * @author faaez
 *
 */
public class HttpPost {

	private HttpPost() {
		throw new IllegalStateException("Cannot init this class.");
	}
	
	/**
	 * Perform HTTP POST with only URL and no data.
	 * @param url
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doPost(String url) throws HttpException, IOException {
		try {
			URL urlObj = new URL(url);
			return doPost(urlObj, "");
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed ", url, e);
		}
	}
	
	/**
	 * Perform HTTP POST with URL and some data.
	 * @param url
	 * @param data
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doPost(String url, String data) throws HttpException, IOException {
		try {
			URL urlObj = new URL(url);
			return doPost(urlObj, data);
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Perform HTTP POST with URL and parameters to be appended to the URL.
	 * @param url
	 * @param parameters
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doPost(String url, Map<String, String> parameters) throws HttpException, IOException {
		try {
			StringBuilder urlBuilder = new StringBuilder(url);
			urlBuilder.append(HttpConfig.parameterAppender).append(parameters.entrySet().stream().map(Object::toString).collect(Collectors.joining("&")));
			URL urlObj = new URL(urlBuilder.toString());
			return doPost(urlObj, "");
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}

	/**
	 *  Perform HTTP POST with URL, some data and parameters to be appended to the URL.
	 * @param url
	 * @param parameters
	 * @param data
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doPost(String url, Map<String, String> parameters, String data) throws HttpException, IOException{
		try {
			StringBuilder urlBuilder = new StringBuilder(url);
			urlBuilder.append(HttpConfig.parameterAppender).append(parameters.entrySet().stream().map(Object::toString).collect(Collectors.joining("&")));
			URL urlObj = new URL(urlBuilder.toString());
			return doPost(urlObj, data);
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}

	/**
	 * Core method that performs HTTP POST and returns data as String
	 * @param url
	 * @param data
	 * @return String
	 * @throws IOException
	 */
	protected static String doPost(URL url, String data) throws IOException {
		StringBuilder response = new StringBuilder();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", Integer.toString(data.length()));
		if(!StringUtils.isEmpty(data)) {
			connection.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				wr.write(data.getBytes());
			}
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		}
		return response.toString();
	}

}
