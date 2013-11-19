package users.nluthra;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;

public class NLTest01 extends TestCase {

	String consumerKey = "qyprdYgsxpbVFafQaNuTww12rSuUnb";
	String consumerSecret = "qrpIsSCspgDIIT3CqZuGWnq9axauwBHbqoBA71oH";
	String accessToken = "";
	String accessTokenSecret = "";

	String appToken = "fbf8209fb24ceb4bbbba2a4b198a3a189cf6";

	public void test() {
		OAuthAuthorizer oauth = new OAuthAuthorizer(consumerKey,
				consumerSecret, accessToken, accessTokenSecret);
		String companyID = "817240805";

		try {
			Context context = new Context(oauth, appToken, ServiceType.QBO,
					companyID);
			DataService service = new DataService(context);

//			List<Customer> customers = service.findAll(new Customer());
//			Iterator itr = customers.iterator();
//			while (itr.hasNext()) {
//				Customer customer = (Customer) itr.next();
//				String customerName = customer.getDisplayName();
//				System.out.println(customerName);
//			}
//
//			Customer customer = new Customer();
//			customer.setDisplayName("NL API Customer 1");
//
//			// Customer resultCustomer = service.add(customer);

		} catch (FMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
