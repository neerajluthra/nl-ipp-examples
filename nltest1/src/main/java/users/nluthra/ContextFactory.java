package users.nluthra;

import java.util.List;

import com.intuit.ds.qb.CompaniesMetaData;
import com.intuit.ds.qb.CompanyMetaData;
import com.intuit.ds.qb.QBCompanyService;
import com.intuit.ds.qb.QBCustomer;
import com.intuit.ds.qb.QBCustomerService;
import com.intuit.ds.qb.QBInvalidContextException;
import com.intuit.ds.qb.QBServiceFactory;
import com.intuit.platform.client.PlatformServiceType;
import com.intuit.platform.client.PlatformSessionContext;
import com.intuit.platform.client.security.OAuthCredentials;

public class ContextFactory {

	public static PlatformSessionContext getContext() {
		PlatformSessionContext context = getLarrysContext();
		return getLarrysContext();
	}

	public static PlatformSessionContext getLarrysContext() {
		String consumerkey = "qyprd0iDIIFPokUn7reIOPkr7KuJLA";
		String consumersecret = "jWVfGu3yF6AlHrApPVmLnp37dCYlJTcJS3FarOAz";
		String apptoken = "d6068e13b6fc4b4621bb338be8d05e2be3f9";

		String accesstoken = "qyprdwdSiFLSGjAKvWAgM0aaDLEM8LYEVok96t1SMGoedFzH";
		String accesstokensecret = "6XVTXqdSkroh6UnTOm1mh0EWoGVSHQu4JTRxkpyP";
		String companyID = "757566405"; // Larry's landscaping

		OAuthCredentials oauthcredentials = new OAuthCredentials(consumerkey,
				consumersecret, accesstoken, accesstokensecret);
		PlatformSessionContext context = new PlatformSessionContext(
				oauthcredentials, apptoken, PlatformServiceType.QBO, companyID);
		return context;
	}

	public static PlatformSessionContext getNeerajsContext() {
		String consumerkey = "qyprd0iDIIFPokUn7reIOPkr7KuJLA";
		String consumersecret = "jWVfGu3yF6AlHrApPVmLnp37dCYlJTcJS3FarOAz";

		String accesstoken = "qyprdrCZCRF9sZZlXUR7arXqYfFVcrxn08mo2s7v5BAeeNtR";
		String accesstokensecret = "iec5XoQI0KZZtOCnVLGWtdSF2kj1icz8VQxucezn";

		OAuthCredentials oauthcredentials = new OAuthCredentials(consumerkey,
				consumersecret, accesstoken, accesstokensecret);
		String apptoken = "d6068e13b6fc4b4621bb338be8d05e2be3f9";
		String companyID = "741524470"; // Neeraj's company
		PlatformSessionContext context = new PlatformSessionContext(
				oauthcredentials, apptoken, PlatformServiceType.QBO, companyID);
		return context;
	}

}
