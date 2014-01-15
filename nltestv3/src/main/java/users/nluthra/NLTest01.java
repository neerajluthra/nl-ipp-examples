package users.nluthra;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.ReferenceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;

public class NLTest01 extends TestCase {

	// neeraj_luthra@intuit.com: NLIntuitApp1
	public static final String CONSUMER_KEY = "qyprdYgsxpbVFafQaNuTww12rSuUnb";
	public static final String CONSUMER_SECRET = "qrpIsSCspgDIIT3CqZuGWnq9axauwBHbqoBA71oH";
	public static final String APP_TOKEN = "fbf8209fb24ceb4bbbba2a4b198a3a189cf6";

	// neeraj_luthra@intuit.com: AU Company 1
	public static final String ACCESS_TOKEN = "qyprdQPQLIrkPhnxes4p1UP93qZJOfMwe7L5s23dlTvKpao5";
	public static final String ACCESS_TOKEN_SECRET = "NGZcN6SWwbeJ1Zmns2gKDfDwLWgAAByGWilCJoKd";
	public static final String COMPANY_ID = "817240805";

	public void test() {
		OAuthAuthorizer oauth = new OAuthAuthorizer(CONSUMER_KEY,
				CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

		try {
			Context context = new Context(oauth, APP_TOKEN, ServiceType.QBO,
					COMPANY_ID);
			DataService service = new DataService(context);

			List<Customer> customers = service.findAll(new Customer());
			Iterator itr = customers.iterator();
			while (itr.hasNext()) {
				Customer customer = (Customer) itr.next();
				String customerName = customer.getDisplayName();
				System.out.println(customerName);
			}

			Customer customer = new Customer();
			customer.setDisplayName("NL Customer 1");
//			ReferenceType rf = new ReferenceType();
//			rf.setValue("JHJ");
//			customer.setCurrencyRef(rf);
			service.add(customer);

			// Customer resultCustomer = service.add(customer);

		} catch (FMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testFathom() throws Exception {
		OAuthConsumer consumer = new DefaultOAuthConsumer(
				"qye2eRD9BBIUSdGgEXjgZBy9wx3hiZ", // Consumer Key
				"bs6L1fFmgeG4eP264Fp4kTevOirYaJcFKLuMn0XT"); // Consumer Secret
		consumer.setTokenWithSecret(
				"qye2e635wQXyRERuebQq88mGU8eWOYcXRdesFlOZlOK4EGmS",
				"tqTIDDhX00fQZZqPcPHtywzvDBUPIj2xNoBm8Ohd");

		String endpoint = "https://qbo.qa.sbfinance.stage.intuit.com/v3/company/1022042734/qboreport/ProfitAndLossSummaryReport?startdate=2013-01-01&enddate=2015-12-31&accountingmethod=accrualbasis";

		URL url = new URL(endpoint);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.setRequestMethod("GET");

		request.setRequestProperty("Content-type", "application/json");

		HttpRequest req = consumer.sign(request);

		request.connect();

		InputStream responseStream = request.getInputStream();
		responseStream = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				responseStream));
		String myline = null;
		System.out.println(br.readLine());
		while ((myline = br.readLine()) != null) {
			System.out.println(myline);
		}
		br.close();
		responseStream.close();
		request.disconnect();

	}

	public void testBAS() {
		callURL("https://quickbooks.api.intuit.com/v3/company/817240805/report/BAS?startdate=2012-01-01&enddate=2013-12-31&accountingmethod=accrualbasis");
	}

	public void testTrialBalance() {
		callURL("https://qb.sbfinance.intuit.com/v3/company/817240805/report/TrialBalance?startdate=2012-01-01&enddate=2013-12-23&accountingmethod=AccrualBasis&STARTPOSITION=1&MAXRESULTS=500");
	}

	public void testPLReport() {
		callURL("https://quickbooks.api.intuit.com/v3/company/817240805/qboreport/ProfitAndLossSummaryReport?startdate=2013-01-01&enddate=2015-12-31&accountingmethod=accrualbasis");
	}

	public void testCompanyInfo() {
		callURL("https://quickbooks.api.intuit.com/v3/company/817240805/companyinfo/817240805");

	}

	private void callURL(String url) {
		callURL(url, CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
	}

	private void callURL(String url, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey,
				consumerSecret);
		consumer.setTokenWithSecret(accessToken, accessTokenSecret);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try {
			consumer.sign(request);
			org.apache.http.HttpResponse response = httpClient.execute(request);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}
	}
}
