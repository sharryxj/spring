package cs;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;

public class GetLocation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String getLocationUrl(String url) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(10000)
				.setSocketTimeout(50000).setRedirectsEnabled(false).build();// 不允许重定向
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
		String location = null;
		int responseCode = 0;

		HttpResponse response;
		try {
			response = httpClient.execute(new HttpGet(url));
			responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == 302) {
				Header locationHeader = response.getFirstHeader("Location");
				location = locationHeader.getValue();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return location;
	}
}

//    private static String getPageContent(String tastKeyword)
//    {
//        String htmlcontent = "";
//        HttpRequest request = null;
//        HttpResponse response = null;
//        String gethost = String.Empty;
//        CookieHolder cc = new CookieHolder();
//        String Cookiesstr = String.Empty;
//
//        try
//        {
//
//            //第一次POST请求
//            String post = @"formhash=59f68caf&srchtxt={0}&searchsubmit=yes";//模拟请求数据
//
//            String postdata = String.Format(post, tastKeyword);
//            String LoginUrl = "http://bbs.hc360.com/search.php?mod=forum";
//            request = (HttpWebRequest)WebRequest.Create(LoginUrl);//实例化web访问类
//            request.Method = "POST";//数据提交方式为POST
//            //模拟头
//            request.ContentType = "application/x-www-form-urlencoded";
//            byte[] postdatabytes = Encoding.GetEncoding("gbk").GetBytes(postdata);
//            request.ContentLength = postdatabytes.Length;
//            request.Referer = "http://bbs.hc360.com/search.php?mod=forum";
//            request.AllowAutoRedirect = false;
//            request.CookieContainer = cc;
//            request.KeepAlive = true;
//            
//            //提交请求
//            Stream stream;
//            stream = request.GetRequestStream();
//            stream.Write(postdatabytes, 0, postdatabytes.Length);
//            stream.Close();
//            //接收响应
//            response = (HttpWebResponse)request.GetResponse();
//            //保存返回cookie
//            response.Cookies = request.CookieContainer.GetCookies(request.RequestUri);
//            CookieCollection cook = response.Cookies;
//            String strcrook = request.CookieContainer.GetCookieHeader(request.RequestUri);
//            Cookiesstr = strcrook;
//            //取第一次GET跳转地址
//            //StreamReader sr = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding("gb2312"));
//            //String content = sr.ReadToEnd();
//            response.Close();
//        }
//        catch (Exception)
//        {
//            //第一次POST出错；
//        }
//        try
//        {
//            String loction = response.Headers["location"];
//            gethost = "http://bbs.hc360.com/" + loction; //第一次GET地址
//            request = (HttpWebRequest)WebRequest.Create(gethost);
//            request.Method = "GET";
//            request.KeepAlive = true;
//            request.Headers.Add("Cookie:" + Cookiesstr);
//            request.CookieContainer = cc;
//            request.AllowAutoRedirect = true;
//            response = (HttpWebResponse)request.GetResponse();
//            //设置cookie   
//            Cookiesstr = request.CookieContainer.GetCookieHeader(request.RequestUri);
//            //取再次跳转链接   
//            StreamReader sr = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding("gbk"));
//            htmlcontent = sr.ReadToEnd();
//            request.Abort();
//            sr.Close();
//            response.Close();
//        }
//        catch (Exception)
//        {
//            //第二次POST出错   
//        }
//        return htmlcontent;
//    }
