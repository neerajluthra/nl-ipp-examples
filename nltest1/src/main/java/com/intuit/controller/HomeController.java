package com.intuit.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import users.nluthra.customer.CustomerFactory;

import com.intuit.ds.qb.qbd.QBDCustomerService;
import com.intuit.ds.qb.QBCustomer;
import com.intuit.ds.qb.QBCustomerService;
import com.intuit.ds.qb.QBEmployee;
import com.intuit.ds.qb.QBEmployeeService;
import com.intuit.ds.qb.QBIdType;
import com.intuit.ds.qb.QBInvalidContextException;
import com.intuit.ds.qb.QBMoney;
import com.intuit.ds.qb.QBObjectFactory;
import com.intuit.ds.qb.QBPaymentService;
import com.intuit.ds.qb.QBServiceFactory;
import com.intuit.ds.qb.QBSyncStatusRequest;
import com.intuit.ds.qb.QBSyncStatusRequestService;
import com.intuit.ds.qb.QBSyncStatusResponse;
import com.intuit.ds.qb.QBVendor;
import com.intuit.ds.qb.QBVendorService;
import com.intuit.platform.client.PlatformServiceType;
import com.intuit.platform.client.PlatformSessionContext;
import com.intuit.platform.client.security.OAuthCredentials;
import com.intuit.sb.cdm.IdDomainEnum;
import com.intuit.utils.WebUtils;
import com.intuit.ds.qb.QBInvoiceService;

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

		final String accesstoken = (String) session.getAttribute("accessToken");
		final String accessstokensecret = (String) session
				.getAttribute("accessTokenSecret");
		final String realmID = (String) session.getAttribute("realmId");
		final String dataSource = (String) session.getAttribute("dataSource");
		final PlatformSessionContext context = getPlatformContext(accesstoken,
				accessstokensecret, realmID, dataSource);

		if (context == null) {
			LOG.error("Error: PlatformSessionContext is null.");
		}

		QBCustomerService vService = null;
		List<QBCustomer> result = new ArrayList<QBCustomer>();
		try {
			vService = QBServiceFactory.getService(context,
					QBCustomerService.class);
			List<QBCustomer> customers = vService.findAll(context, 1, 100);
			for (QBCustomer c : customers) {
				BigDecimal amount = c.getOpenBalance().getAmount();
				if (amount != null && amount.compareTo(BigDecimal.ZERO) == 1) {
					result.add(c);
				}
			}
		} catch (QBInvalidContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("customerList", result);
		return "home";
	}

	/*
	 * This method is called when the user clicks on 'Get All QuickBooks
	 * Customers' Link on Home Page.
	 */
	@RequestMapping(value = "/vendors.htm", method = RequestMethod.GET)
	public String getVendors(final HttpServletRequest request,
			final Model model) {
		LOG.info("HomeController -> getVendors()");

		final HttpSession session = request.getSession();

		final String accesstoken = (String) session.getAttribute("accessToken");
		final String accessstokensecret = (String) session
				.getAttribute("accessTokenSecret");
		final String realmID = (String) session.getAttribute("realmId");
		final String dataSource = (String) session.getAttribute("dataSource");
		final PlatformSessionContext context = getPlatformContext(accesstoken,
				accessstokensecret, realmID, dataSource);

		if (context == null) {
			LOG.error("Error: PlatformSessionContext is null.");
		}

		QBVendorService vService = null;
		List<QBVendor> result = new ArrayList<QBVendor>();
		try {
			vService = QBServiceFactory.getService(context,
					QBVendorService.class);
			List<QBVendor> vendors = vService.findAll(context, 1, 100);
			for (QBVendor v : vendors) {
				BigDecimal amount = v.getOpenBalance().getAmount();
				if (amount != null && amount.compareTo(BigDecimal.ZERO) == 1) {
					result.add(v);
				}
			}
		} catch (QBInvalidContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("vendorList", result);
		return "home";
	}
	/*
	 * This method a helper to get the Context from SDK.
	 */
	public PlatformSessionContext getPlatformContext(final String accessToken,
			final String accessTokenSecret, final String realmID,
			final String dataSource) {

		PlatformServiceType serviceType;
		if (dataSource.equalsIgnoreCase("QBO")) {
			serviceType = PlatformServiceType.QBO;
		} else {
			serviceType = PlatformServiceType.QBD;
		}

		final OAuthCredentials oauthcredentials = new OAuthCredentials(
				WebUtils.OAUTH_CONSUMER_KEY, WebUtils.OAUTH_CONSUMER_SECRET,
				accessToken, accessTokenSecret);

		final PlatformSessionContext context = new PlatformSessionContext(
				oauthcredentials, WebUtils.APP_TOKEN, serviceType, realmID);

		return context;
	}
}
