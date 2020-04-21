package com.deepika.testProjects;

import java.util.List;

public interface CallTaxiService {

	String makeBooking(int custId, String pickPoint, String dropPoint, int time);

	List<BookTaxi> getTripHistory();

	List<User> getUserHistory();

	
}
