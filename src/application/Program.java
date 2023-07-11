package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ====");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		for(Seller seller1 : list) {
			System.out.println(seller1);
		}
		
		System.out.println("\n=== TEST 3: seller findAll ====");
		list = sellerDao.findAll();
		for(Seller seller1 : list) {
			System.out.println(seller1);
		}
		
		System.out.println("\n=== TEST 4: seller INSERT ====");
		Seller newseller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
		sellerDao.insert(newseller);
		list = sellerDao.findAll();
		System.out.println("Inserted! New id = " + newseller.getId());
		
		System.out.println("\n=== TEST 5: seller Update ====");
		seller= sellerDao.findById(1);
		seller.setName("Martha Wayne");
		seller.setEmail("mariawayne10@gmail.com");
		sellerDao.update(seller);
		System.out.println("UPDATE SUCESS!");
		
		System.out.println("\n=== TEST 6: seller Delete ====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete Completed");
		
		sc.close();
	}
}
