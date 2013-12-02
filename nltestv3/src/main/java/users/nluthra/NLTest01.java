package users.nluthra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;

public class NLTest01 extends TestCase {

	// neeraj_luthra@intuit.com: NLIntuitApp1
	final String consumerKey = "qyprdYgsxpbVFafQaNuTww12rSuUnb";
	final String consumerSecret = "qrpIsSCspgDIIT3CqZuGWnq9axauwBHbqoBA71oH";
	final String appToken = "fbf8209fb24ceb4bbbba2a4b198a3a189cf6";

	// neeraj_luthra@intuit.com: AU Company 1
	final String accessToken = "qyprdcEAMr7pbzxsx60p7JPvnrOLpAzOQC5HztvOvTwZYvUz";
	final String accessTokenSecret = "ldu0Z2ZvHSQ5xEXZQbxTapuFnPu3Zw6wDaOZgM0a";
	final String companyID = "817240805";

	public void test() {
		OAuthAuthorizer oauth = new OAuthAuthorizer(consumerKey,
				consumerSecret, accessToken, accessTokenSecret);

		try {
			Context context = new Context(oauth, appToken, ServiceType.QBO,
					companyID);
			DataService service = new DataService(context);

			List<Customer> customers = service.findAll(new Customer());
			Iterator itr = customers.iterator();
			while (itr.hasNext()) {
				Customer customer = (Customer) itr.next();
				String customerName = customer.getDisplayName();
				System.out.println(customerName);
			}

			Customer customer = new Customer();
			customer.setDisplayName("NL API Customer 1");

			// Customer resultCustomer = service.add(customer);

		} catch (FMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testBAS() {
		String URL = "https://quickbooks.api.intuit.com/v3/company/817240805/report/BAS?startdate=2012-01-01&enddate=2013-12-31&accountingmethod=AccrualBasis";

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey,
				consumerSecret);
		consumer.setTokenWithSecret(accessToken, accessTokenSecret);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL);
		try {
			consumer.sign(request);
			HttpResponse response = httpClient.execute(request);
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
