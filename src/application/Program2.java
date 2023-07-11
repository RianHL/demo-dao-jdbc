package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("===TEST 1: findById =======");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: department findAll ====");
		List<Department>list = departmentDao.findAll();
		for(Department dep1 : list) {
			System.out.println(dep1);
		}

		System.out.println("\n=== TEST 3: department Delete ====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete Completed");
		
		System.out.println("\n=== TEST 4: department INSERT ====");
		Department newdep = new Department(5, "Food");
		departmentDao.insert(newdep);
		list = departmentDao.findAll();
		System.out.println("Inserted! New id = " + newdep.getId());
		
		System.out.println("\n=== TEST 5: department Update ====");
		dep = departmentDao.findById(1);
		dep.setName("Computers");
		departmentDao.update(dep);
		System.out.println("UPDATE SUCESS!");
	
		
	}

}
