package users.nluthra;


public class NLTest1 {

	public static void main(String[] args) {
		CustomerFactory.printCustomers();
		CustomerFactory.deleteCustomerById("1");
		CustomerFactory.printCustomers();
	}

}
