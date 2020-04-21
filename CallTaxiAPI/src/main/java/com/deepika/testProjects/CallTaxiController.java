package com.deepika.testProjects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallTaxiController {

	@Autowired
	private CallTaxiService objCallTaxiService;
	
	@PostMapping("/bookTaxi/{custId}/{pickPoint}/{dropPoint}/{time}")
	public String bookTaxi(@PathVariable int custId,@PathVariable String pickPoint,@PathVariable String dropPoint, @PathVariable int time) {
		
		String bookingStatus = objCallTaxiService.makeBooking(custId,pickPoint,dropPoint,time);
		
		return bookingStatus;
	}
	
	@GetMapping("/bookTaxi/getTripHistory")
	public List<BookTaxi> getTripHistory(){
		
		List<BookTaxi> tripHistory = objCallTaxiService.getTripHistory();
		return tripHistory;
	}
	
	@GetMapping("/bookTaxi/getUserHistory")
	public List<User> getUserHistory(){
		
		List<User> userHistory = objCallTaxiService.getUserHistory();
		return userHistory;
	}
}
