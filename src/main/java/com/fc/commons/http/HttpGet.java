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

public class HttpGet {

	private HttpGet() {
		throw new IllegalStateException("Cannot init this class.");
	}
	
	/**
	 * Perform HTTP GET with only URL and no data and return response.
	 * @param url
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doGetWithResponse(String url) throws HttpException, IOException {
		try {
			URL urlObj = new URL(url);
			return doGetWithResponse(urlObj, "");
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Perform HTTP GET with URL and data and return response.
	 * @param url
	 * @param data
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doGetWithResponse(String url, String data) throws HttpException, IOException {
		try {
			URL urlObj = new URL(url);
			return doGetWithResponse(urlObj, data);
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Perform HTTP GET with URL, no data and provided parameters and return response.
	 * @param url
	 * @param data
	 * @param parameters
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doGetWithResponse(String url, Map<String, String> parameters) throws HttpException, IOException {
		try {
			StringBuilder urlBuilder = new StringBuilder(url);
			urlBuilder.append(HttpConfig.parameterAppender).append(parameters.entrySet().stream().map(Object::toString).collect(Collectors.joining("&")));
			URL urlObj = new URL(urlBuilder.toString());
			return doGetWithResponse(urlObj, "");
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Perform HTTP GET with URL, data and provided parameters and return response.
	 * @param url
	 * @param data
	 * @param parameters
	 * @return String
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String doGetWithResponse(String url, String data, Map<String, String> parameters) throws HttpException, IOException {
		try {
			StringBuilder urlBuilder = new StringBuilder(url);
			urlBuilder.append(HttpConfig.parameterAppender).append(parameters.entrySet().stream().map(Object::toString).collect(Collectors.joining("&")));
			URL urlObj = new URL(urlBuilder.toString());
			return doGetWithResponse(urlObj, data);
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Perform HTTP GET with URL and no data.
	 * @param url
	 * @throws HttpException
	 * @throws IOException
	 */
	public static void doGet(String url) throws HttpException, IOException {
		try {
			URL urlObj = new URL(url);
			doGet(urlObj, "");
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Perform HTTP GET with URL and data.
	 * @param url
	 * @param data
	 * @throws HttpException
	 * @throws IOException
	 */
	public static void doGet(String url, String data) throws HttpException, IOException {
		try {
			URL urlObj = new URL(url);
			doGet(urlObj, data);
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 *  Perform HTTP GET with URL, data and provided parameters.
	 * @param url
	 * @param data
	 * @param parameters
	 * @throws HttpException
	 * @throws IOException
	 */
	public static void doGet(String url, String data, Map<String, String> parameters) throws HttpException, IOException {
		try {
			StringBuilder urlBuilder = new StringBuilder(url);
			urlBuilder.append(HttpConfig.parameterAppender).append(parameters.entrySet().stream().map(Object::toString).collect(Collectors.joining("&")));
			URL urlObj = new URL(urlBuilder.toString());
			doGet(urlObj, data);
		} catch (MalformedURLException e) {
			HttpConfig.LOGGER.severe("Provided URL is malformed");
			throw new HttpException("Provided URL is malformed", url, e);
		}
	}
	
	/**
	 * Core method that performs HTTP GET and returns data as String
	 * @param url
	 * @param data
	 * @return String
	 * @throws IOException
	 */
	protected static String doGetWithResponse(URL url, String data) throws IOException {
		StringBuilder response = new StringBuilder();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
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
	
	/**
	 * Core method that performs HTTP GET and returns data as String
	 * @param url
	 * @param data
	 * @return String
	 * @throws IOException
	 */
	protected static void doGet(URL url, String data) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", Integer.toString(data.length()));
		if(!StringUtils.isEmpty(data)) {
			connection.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				wr.write(data.getBytes());
			}
		}
	}
}
