package com.am;

import com.am.model.ExternalUser;
import com.am.service.ApplicationService;
import com.am.service.ServiceUtil;
import com.am.service.user.ExternalUserService;

public class MainDemo {

	public static void main(String[] args) {
		ServiceUtil.initContext(args);

		ApplicationService applicationService = ServiceUtil.getApplicationService();

		ExternalUserService externalUserService = applicationService.getExternalUserService();

		externalUserService.deleteAll();
		ExternalUser externalUser= new ExternalUser();
		
		externalUser.setFirstName("Amany");
		externalUser.setLastName("Ali");
		externalUser.setEmail("amany@mail.com");
		
		externalUserService.saveOrUpdate(externalUser);

	}

}
