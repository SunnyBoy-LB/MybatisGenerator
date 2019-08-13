package com.lgb.MybatisGenerator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Http工具类
 * Http工具类，为系统提供通用Http访问操作方法：
 * 1、发送GET请求； 2、发送POST请求。
 * 
 */
public class HttpUtilTest
{

	/**
	 * 发送GET请求
	 * @param url GET请求地址
	 * @return 与当前请求对应的响应内容字节数组
	 * 
	 */
	public static byte[] doGet(String url)
	{
		return HttpUtilTest.doGet(url, null, null, 0);
	}

	/**
	 * 发送GET请求
	 * @param url GET请求地址
	 * @param headerMap GET请求头参数容器
	 * @return 与当前请求对应的响应内容字节数组
	 * 
	 */
	public static byte[] doGet(String url, Map<String, Object> headerMap)
	{
		return HttpUtilTest.doGet(url, headerMap, null, 0);
	}

	/**
	 * 发送GET请求
	 * @param url GET请求地址
	 * @param proxyUrl 代理服务器地址
	 * @param proxyPort 代理服务器端口号
	 * @return 与当前请求对应的响应内容字节数组
	 * @modify 2012-03-19
	 */
	public static byte[] doGet(String url, String proxyUrl, int proxyPort)
	{
		return HttpUtilTest.doGet(url, null, proxyUrl, proxyPort);
	}

	/**
	 * 发送GET请求
	 * @param url GET请求地址
	 * @param headerMap GET请求头参数容器
	 * @param proxyUrl 代理服务器地址
	 * @param proxyPort 代理服务器端口号
	 * @return 与当前请求对应的响应内容字节数组
	 * @modify 2012-03-19
	 */
	public static byte[] doGet(String url, Map<String, Object> headerMap, String proxyUrl, int proxyPort)
	{
		byte[] content = null;
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		if (headerMap != null)
		{
			// 头部请求信息
			if (headerMap != null)
			{
				Iterator iterator = headerMap.entrySet().iterator();
				while (iterator.hasNext())
				{
					Entry entry = (Entry) iterator.next();
					getMethod.addRequestHeader(entry.getKey().toString(), entry.getValue().toString());
				}
			}
		}
		if (StringUtils.isNotBlank(proxyUrl))
		{
			httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);
		}
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
		// postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER , new
		// DefaultHttpMethodRetryHandler());
		InputStream inputStream = null;
		try
		{
			if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK)
			{
				// 读取内容
				inputStream = getMethod.getResponseBodyAsStream();
				content = IOUtils.toByteArray(inputStream);
			}
			else
			{
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(inputStream);
			getMethod.releaseConnection();
		}
		return content;
	}

	/**
	 * 发送POST请求
	 * @param url POST请求地址
	 * @param parameterMap POST请求参数容器
	 * @return 与当前请求对应的响应内容字节数组
	 * 
	 */
	public static byte[] doPost(String url, Map<String, Object> parameterMap)
	{
		return HttpUtilTest.doPost(url, null, parameterMap, null, null, 0);
	}

	/**
	 * 发送POST请求
	 * @param url POST请求地址
	 * @param parameterMap POST请求参数容器
	 * @param paramCharset 参数字符集名称
	 * @return 与当前请求对应的响应内容字节数组
	 * @modify 2012-05-21
	 */
	public static byte[] doPost(String url, Map<String, Object> parameterMap, String paramCharset)
	{
		return HttpUtilTest.doPost(url, null, parameterMap, paramCharset, null, 0);
	}

	/**
	 * 发送POST请求
	 * @param url POST请求地址
	 * @param headerMap POST请求头参数容器
	 * @param parameterMap POST请求参数容器
	 * @param paramCharset 参数字符集名称
	 * @return 与当前请求对应的响应内容字节数组
	 * @modify 2012-05-21
	 */
	public static byte[] doPost(String url, Map<String, Object> headerMap, Map<String, Object> parameterMap, String paramCharset)
	{
		return HttpUtilTest.doPost(url, headerMap, parameterMap, paramCharset, null, 0);
	}

	/**
	 * 发送POST请求
	 * @param url POST请求地址
	 * @param parameterMap POST请求参数容器
	 * @param paramCharset 参数字符集名称
	 * @param proxyUrl 代理服务器地址
	 * @param proxyPort 代理服务器端口号
	 * @return 与当前请求对应的响应内容字节数组
	 * 
	 */
	public static byte[] doPost(String url, Map<String, Object> parameterMap, String paramCharset, String proxyUrl, int proxyPort)
	{
		return HttpUtilTest.doPost(url, null, parameterMap, paramCharset, proxyUrl, proxyPort);
	}

	/**
	 * 发送POST请求
	 * @param url POST请求地址
	 * @param headerMap POST请求头参数容器
	 * @param parameterMap POST请求参数容器
	 * @param paramCharset 参数字符集名称
	 * @param proxyUrl 代理服务器地址
	 * @param proxyPort 代理服务器端口号
	 * @return 与当前请求对应的响应内容字节数组
	 * @modify 2012-05-21
	 */
	public static byte[] doPost(String url, Map<String, Object> headerMap, Map<String, Object> parameterMap, String paramCharset, String proxyUrl, int proxyPort)
	{
		byte[] content = null;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		if (StringUtils.isNotBlank(paramCharset))
		{
			postMethod.getParams().setContentCharset(paramCharset);
			postMethod.getParams().setHttpElementCharset(paramCharset);
		}
		if (headerMap != null)
		{
			// 头部请求信息
			if (headerMap != null)
			{
				Iterator iterator = headerMap.entrySet().iterator();
				while (iterator.hasNext())
				{
					Entry entry = (Entry) iterator.next();
					postMethod.addRequestHeader(entry.getKey().toString(), entry.getValue().toString());
				}
			}
		}
		Iterator iterator = parameterMap.keySet().iterator();
		while (iterator.hasNext())
		{
			String key = (String) iterator.next();
			postMethod.addParameter(key, (String) parameterMap.get(key));
		}
		if (StringUtils.isNotBlank(proxyUrl))
		{
			httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);
		}
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
		// postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER , new
		// DefaultHttpMethodRetryHandler());
		InputStream inputStream = null;
		try
		{
			if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK)
			{
				// 读取内容
				inputStream = postMethod.getResponseBodyAsStream();
				content = IOUtils.toByteArray(inputStream);
			}
			else
			{
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(inputStream);
			postMethod.releaseConnection();
		}
		return content;
	}

	public static void main(String[] args)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("platform", "web");
		map.put("data", "{nodata: 1}");
		// map.put("sign", "739d5499559c43449582705e254551d8");
		// map.put("timestamp", "1535958900197");
		byte[] b = doPost("http://testdp1.rjs.com/japi/index/getIndexInfoList.json", map);
		System.out.println("-------------------" + new String(b));
	}
}