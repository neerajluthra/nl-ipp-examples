package users.nluthra;

import java.util.List;

import com.intuit.ds.qb.PartyType;
import com.intuit.ds.qb.QBCustomer;
import com.intuit.ds.qb.QBCustomerService;
import com.intuit.ds.qb.QBObjectFactory;
import com.intuit.ds.qb.QBServiceFactory;
import com.intuit.platform.client.PlatformSessionContext;

public class CustomerFactory {
	public static void addCustomer(String name) {
		if (name == null || name.trim().length() <= 0)
			return;
		PlatformSessionContext context = ContextFactory.getContext();
		try {
			QBCustomer customer = QBObjectFactory.getQBObject(context,
					QBCustomer.class);
			customer.setName(name);
			customer.setTypeOf(PartyType.PERSON);
			QBCustomerService iCustomerSer = QBServiceFactory.getService(
					context, QBCustomerService.class);
			QBCustomer newCustomer = iCustomerSer
					.addCustomer(context, customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static QBCustomer getCustomerById(String id) {
		for (QBCustomer c : getCustomers()) {
			if (c.getId().getValue().equals(id)) {
				return c;
			}
		}
		throw new IllegalArgumentException("customer not found for id=" + id);
	}

	public static void deleteCustomerById(String id) {
		deleteCustomer(getCustomerById(id));
	}

	public static void deleteCustomer(QBCustomer c) {
		PlatformSessionContext context = ContextFactory.getContext();
		try {
			QBCustomerService service = QBServiceFactory.getService(context,
					QBCustomerService.class);
			service.deleteCustomer(context, c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printCustomers() {
		for (QBCustomer c : getCustomers()) {
			System.out.println(c.getId().getValue() + ":" + c.getName());
		}
	}

	public static List<QBCustomer> getCustomers() {
		PlatformSessionContext context = ContextFactory.getContext();
		List<QBCustomer> customers = null;
		try {
			QBCustomerService service = QBServiceFactory.getService(context,
					QBCustomerService.class);
			customers = service.findAll(context, 1, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

}
