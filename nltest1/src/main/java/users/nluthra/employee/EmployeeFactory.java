package users.nluthra.employee;

import java.util.List;

import users.nluthra.ContextFactory;

import com.intuit.ds.qb.QBEmployee;
import com.intuit.ds.qb.QBEmployeeService;
import com.intuit.ds.qb.QBServiceFactory;
import com.intuit.platform.client.PlatformSessionContext;

public class EmployeeFactory {

	public static QBEmployee getEmployeeById(String id) {
		for (QBEmployee e : getEmployees()) {
			if (e.getId().getValue().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("employee not found for id=" + id);
	}

	public static List<QBEmployee> getEmployees() {
		PlatformSessionContext context = ContextFactory.getContext();
		List<QBEmployee> employees = null;
		try {
			QBEmployeeService service = QBServiceFactory.getService(context,
					QBEmployeeService.class);
			employees = service.findAll(context, 1, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}

}
