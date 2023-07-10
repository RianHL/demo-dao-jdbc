package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department dpt = new Department(1, "Books");
		Seller sl = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, dpt);
		System.out.println(sl);
		System.out.println(dpt);
	}

}
