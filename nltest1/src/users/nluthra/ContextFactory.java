package users.nluthra;

import com.intuit.platform.client.PlatformServiceType;
import com.intuit.platform.client.PlatformSessionContext;
import com.intuit.platform.client.security.OAuthCredentials;

public class ContextFactory {

	public static PlatformSessionContext getContext() {
		String consumerkey = "qyprd0iDIIFPokUn7reIOPkr7KuJLA";
		String consumersecret = "jWVfGu3yF6AlHrApPVmLnp37dCYlJTcJS3FarOAz";

		String accesstoken = "qyprdrCZCRF9sZZlXUR7arXqYfFVcrxn08mo2s7v5BAeeNtR";
		String accesstokensecret = "iec5XoQI0KZZtOCnVLGWtdSF2kj1icz8VQxucezn";

		OAuthCredentials oauthcredentials = new OAuthCredentials(consumerkey,
				consumersecret, accesstoken, accesstokensecret);
		String apptoken = "d6068e13b6fc4b4621bb338be8d05e2be3f9";
		String companyID = "741524470";
		PlatformSessionContext context = new PlatformSessionContext(
				oauthcredentials, apptoken, PlatformServiceType.QBO, companyID);
		return context;
	}

}
