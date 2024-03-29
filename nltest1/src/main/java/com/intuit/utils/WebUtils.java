/*
 * Copyright (c) 2011 Intuit, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * Contributors:
 *
 *    Intuit Partner Platform - initial contribution 
 *
 */

package com.intuit.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.intuit.platform.client.PlatformServiceType;
import com.intuit.platform.client.PlatformSessionContext;
import com.intuit.platform.client.security.OAuthCredentials;

/*
 * A utility class for this sample web app.
 */

public class WebUtils {
	
	public static final Logger LOG = Logger.getLogger(WebUtils.class);

	public static Properties propConfig = null;
	public static String PROP_FILE = "nltest1.properties";

	public static String APP_TOKEN;
	public static String OAUTH_CONSUMER_KEY;
	public static String OAUTH_CONSUMER_SECRET;

	public static String OPENID_PROVIDER_URL;
	public static String OAUTH_URL;
	public static String APPCENTER_URL;

	public static String OPENID_RETURN_URL;
	public static String OAUTH_CALLBACK_URL;
	
	public static String APP_URL;

	static {
		try {
			propConfig = PropertiesLoaderUtils
					.loadProperties(new ClassPathResource(PROP_FILE));

			APP_TOKEN = propConfig.getProperty("appToken");
			OAUTH_CONSUMER_KEY = propConfig.getProperty("oauth_consumer_key");
			OAUTH_CONSUMER_SECRET = propConfig
					.getProperty("oauth_consumer_secret");

			OPENID_PROVIDER_URL = propConfig.getProperty("openid_provider_url");
			OAUTH_URL = propConfig.getProperty("oauth_url");
			APPCENTER_URL = propConfig.getProperty("appcenter_url");

			OPENID_RETURN_URL = propConfig.getProperty("openid_return_url");
			OAUTH_CALLBACK_URL = propConfig.getProperty("oauth_callback_url");
			
			APP_URL = propConfig.getProperty("app_url");

		} catch (IOException e) {
			LOG.error("Properties File can not be loaded!!! "+e.getLocalizedMessage());
		}

	}
	
	private String getProps(final String key) {
		String value = "";
		if (propConfig != null) {
			value = propConfig.getProperty(key);
		}
		return value;
	}
	
	public String getAppcenterUrl() {
		return getProps("appcenter_url");
	}
	
	public String getAppUrl() {
		return getProps("app_url");
	}
	
	

	public PlatformSessionContext getPlatformContext(final String accesstoken,
			final String accesstokensecret, final String realmID, final String dataSource) {

		LOG.info("apptoken inside getPlatformSessionContext: "
				+ APP_TOKEN);
		LOG.info("realmID inside getPlatformSessionContext: "
				+ realmID);
		LOG.info("OAuth acccess token inside getPlatformSessionContext: "
						+ accesstoken);

		PlatformServiceType platformServiceType;
		if (dataSource.equalsIgnoreCase("QBO")) {
			platformServiceType = PlatformServiceType.QBO;
		} else {
			platformServiceType = PlatformServiceType.QBD;
		}

		final OAuthCredentials oauthcredentials = new OAuthCredentials(
				OAUTH_CONSUMER_KEY, OAUTH_CONSUMER_SECRET, accesstoken,
				accesstokensecret);
		final PlatformSessionContext context = new PlatformSessionContext(
				oauthcredentials, APP_TOKEN, platformServiceType, realmID);

		return context;
	}
}
