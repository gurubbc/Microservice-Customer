package com.ofss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	RemoteStockRepository remoteStockRepository;


	Address a1=new Address(100, "MG ROAD", "Bangalore", 560102);
	int sid1[]= {1,2,3};
	Customer c1=new Customer(10, "Guru", a1, sid1);
	
	Address a2=new Address(200, "KORAMANGALA", "Mangalore", 860102);
	int sid2[]= {2,3,4};
	Customer c2=new Customer(11, "Aanya", a2, sid2);

	Address a3=new Address(300, "Chandni Chowk", "Pune", 860102);
	int sid3[]= {4,5,2};
	Customer c3=new Customer(12, "Deepak", a3, sid3);

	Address a4=new Address(400, "Town Hall", "Mumbai", 360102);
	int sid4[]= {1,2,3};
	Customer c4=new Customer(13, "Narayan", a4, sid4);

	Address a5=new Address(500, "T Nagar", "Chennai", 660102);
	int sid5[]= {3,5};
	Customer c5=new Customer(14, "Vidya", a5, sid5);

	
	

	ArrayList<Customer> allCustomers=new ArrayList(Arrays.asList(c1,c2,c3,c4,c5));

	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ArrayList<Customer> getCustomersList()
	{
		return allCustomers;
	}

	@RequestMapping(value="/customers/{customerid}/stocks",method=RequestMethod.GET)
	public List<Stock> getCustomerStocks(@PathVariable(value="customerid") int customerid)
	{
		System.out.println("Entering getCustomerStocks");
		List<Stock> allStocks=new ArrayList();
		Customer cust=null;
		
		for (Customer c:allCustomers)
		{
			if (c.getCustomerId()==customerid)
			{
				cust=c; // this customer obj will be returned if there is a match
			}
				
		}
		int stockIds[]=cust.getStockIds(); // fetches all stock ids for this customer id
		
		
		for (int i=0;i<stockIds.length;i++)
		{
		System.out.println("stock id: "+stockIds[i]);	
		Stock stock=remoteStockRepository.getStock(stockIds[i]);
		allStocks.add(stock);
		}
		return allStocks;
		
		
	}

	@RequestMapping(value="/customers/{customerid}",method=RequestMethod.DELETE)
	public String deleteACustomer(@PathVariable(value="customerid") int customerid)
	{
		String msg="No such customer exists with id "+customerid;
		// Iterating the arraylist and searching the customer details for a given id
		Customer cust=null;
		for (Customer c:allCustomers)
		{
			if (c.getCustomerId()==customerid)
			{
				cust=c;
				msg="The customer has been removed successfully from the list";
			}
		}
		allCustomers.remove(cust);
		return msg;
		
	}

	@RequestMapping(value="/customers/{customerid}/stockids", method=RequestMethod.GET)
	public int[] getStockIds(@PathVariable(value="customerid") int customerid)
	{
		System.out.println("invoking stockidsmethod "+customerid);
		Customer cust=null;
		for (Customer c:allCustomers)
		{
			if (c.getCustomerId()==customerid)
			{
				cust=c;
			}
		}
		return cust.getStockIds();
	
	}

	
	
	@RequestMapping(value="/customers/{customerid}",method=RequestMethod.GET)
	public Customer getCustomer(@PathVariable(value="customerid") int customerid)
	{
		// Iterating the arraylist and searching the customer details for a given id
		System.out.println("Inside customer controller");
		for (Customer c:allCustomers)
		{
			if (c.getCustomerId()==customerid)
			{
				return c;
			}
		}
		return null;
	}


	// Incomplete code
	public Customer addACustomer() 
	{
		return null;
	}	
	


	
}
