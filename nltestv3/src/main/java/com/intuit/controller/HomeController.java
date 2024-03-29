package com.intuit.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.AccountBasedExpenseLineDetail;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.data.Line;
import com.intuit.ipp.data.LineDetailTypeEnum;
import com.intuit.ipp.data.PaymentTypeEnum;
import com.intuit.ipp.data.PhysicalAddress;
import com.intuit.ipp.data.Purchase;
import com.intuit.ipp.data.ReferenceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.utils.WebUtils;

/*
 * This class is a controller for the application home page related activities.  
 */
@Controller
public class HomeController {

	public static final Logger LOG = Logger.getLogger(HomeController.class);

	/*
	 * This method is called when the user is redirected from Login Page /
	 * Application Page / Settings Page.
	 */
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String showHomePage(final HttpServletRequest request) {
		LOG.info("HomeController -> showHomePage()");

		String redirectTo;
		if (request.getSession().getAttribute("invalidateOAuth") != null) {
			LOG.info("Invalidate ");
			request.getSession().setAttribute("accessToken", null);
			request.getSession().setAttribute("accessTokenSecret", null);
			request.getSession().setAttribute("connectionStatus",
					"not_authorized");
			
			request.getSession().removeAttribute("invalidateOAuth");
		}

		final HttpSession session = request.getSession();
		if (session.getAttribute("displayUserName") != null
				|| session.getAttribute("firstName") != null
				|| session.getAttribute("lastName") != null
				|| session.getAttribute("connectionStatus") != null) {
			if (session.getAttribute("isLinkingRequired") != null) {
				redirectTo = "redirect:/linking.htm";
			} else {
				redirectTo = "home";
			}
		} else {
			redirectTo = "redirect:/login.htm?isLoggedIn=false";
		}

		return redirectTo;
	}

	/*
	 * This method is called when the user clicks on 'Get All QuickBooks
	 * Customers' Link on Home Page.
	 */
	@RequestMapping(value = "/customers.htm", method = RequestMethod.GET)
	public String getCustomers(final HttpServletRequest request,
			final Model model) {
		LOG.info("HomeController -> getCustomers()");

		final HttpSession session = request.getSession();
		final List<Customer> customerList = new ArrayList<Customer>();

		final String accesstoken = (String) session.getAttribute("accessToken");
		System.out.println("accesstoken:"+accesstoken);
		final String accessstokensecret = (String) session.getAttribute("accessTokenSecret");
		System.out.println("accessTokenSecret:"+accessstokensecret);
		final String realmID = (String) session.getAttribute("realmId");
		final String dataSource = (String) session.getAttribute("dataSource");
		
		OAuthAuthorizer authorizer = new OAuthAuthorizer(WebUtils.OAUTH_CONSUMER_KEY, WebUtils.OAUTH_CONSUMER_SECRET, accesstoken, accessstokensecret);
		Context context = null;
		
		try {
			context = new Context(authorizer, WebUtils.APP_TOKEN, ServiceType.valueOf(dataSource), realmID);
		} catch (FMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (context == null) {
			LOG.error("Error: PlatformSessionContext is null.");
		}

		DataService service = new DataService(context);
		
		try {
			
			// Using the service, retrieve all customers and display their names.
			final List<Invoice> invoices = service.findAll(new Invoice());
			for (Invoice invoice : invoices) {
				System.out.println("InvoiceId:" + invoice.getId());
				System.out.println("InvoiceCurrencyRef:" + invoice.getCurrencyRef().getName());
				System.out.println("InvoiceCurrencyRef:" + invoice.getCurrencyRef().getValue());
				System.out.println("InvoiceCustomerRef:" + invoice.getCustomerRef().getName());
				System.out.println("InvoiceCustomerRef:" + invoice.getCustomerRef().getValue());
			}

			final List<Customer> customers = service.findAll(new Customer());

			for (Customer customer : customers) {
				final String customerName = customer.getDisplayName();
				LOG.info("customerName : " + customerName);
				System.out.println("CustomerId:" + customer.getId());
				customerList.add(customer);

			}
			
			
		} catch (Exception e) {
			LOG.error("Exception thrown by findAll / OAuth tokens are invalidated");
			session.setAttribute("connectionStatus", "not_authorized");
		}

		model.addAttribute("customerList", customerList);
		return "home";
	}
}
