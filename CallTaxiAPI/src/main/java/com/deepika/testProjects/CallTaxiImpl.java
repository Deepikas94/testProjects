package com.deepika.testProjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CallTaxiImpl implements CallTaxiService {

	@Autowired
	private UserRepo objUserRepo;
	
	@Autowired
	private BookTaxiRepo objBookTaxiRepo;
	
	@PostConstruct
	public void createTaxis(){
		System.out.println("POST CONSTRUCT CALLED NOW");
		BookTaxi bookedTaxi = new BookTaxi(1,0,0,"A","A",0,0);
		objBookTaxiRepo.save(bookedTaxi);
		bookedTaxi = new BookTaxi(2,0,0,"A","A",0,0);
		objBookTaxiRepo.save(bookedTaxi);
		bookedTaxi = new BookTaxi(3,0,0,"A","A",0,0);
		objBookTaxiRepo.save(bookedTaxi);
		bookedTaxi = new BookTaxi(4,0,0,"A","A",0,0);
		objBookTaxiRepo.save(bookedTaxi);
	}

	
	public String makeBooking(int custId, String pickPoint, String dropPoint, int time) {
		
		HashMap<String, Point> pointMap = new HashMap<>();
		pointMap.put("A", new Point(0,0));
		pointMap.put("B", new Point(1,15));
		pointMap.put("C", new Point(2,30));
		pointMap.put("D", new Point(3,45));
		pointMap.put("E", new Point(4,60));
		pointMap.put("F", new Point(5,75));
		
		String bookingStatus = "";
		List<BookTaxi> bookTaxis = (List<BookTaxi>) objBookTaxiRepo.findAll();
	
			BookTaxi availabletaxi = getAvailableTaxi( pointMap,bookTaxis,custId,pickPoint,dropPoint,time);
			if(availabletaxi == null) {
				bookingStatus="No taxi available now. Please try later";
			}else {
				int taxiId = confirmTaxiBooking(pointMap,availabletaxi,custId,pickPoint,dropPoint,time);
				bookingStatus = "Taxi can be allotted. Taxi "+taxiId+" is allotted";
			}
		return bookingStatus;	
	}

	private BookTaxi getAvailableTaxi(HashMap<String, Point> pointMap, List<BookTaxi> bookTaxis, int custId, String pickPoint, String dropPoint, int time) {
		
		List<BookTaxi> taxisAtPickUpPoint = new ArrayList<>();
		BookTaxi availableTaxi = new BookTaxi();
		Point pick = pointMap.get(pickPoint);
		String pointname = "";
		pointname = getNearestPoint(pointMap,pick);
		boolean presentAtPickpoint = false;
		if(bookTaxis.size()>0) {
			for(BookTaxi bookTaxi:bookTaxis) {
				if(bookTaxi.getDropPoint().equals(pickPoint) && bookTaxi.getEndTime()<=time) {
					taxisAtPickUpPoint.add(bookTaxi);
					presentAtPickpoint=true;
				}
			}
			if(!presentAtPickpoint) {
				for(BookTaxi bookTaxi:bookTaxis) {		
					if(bookTaxi.getDropPoint().equals(pointname) && bookTaxi.getEndTime()<=time) {
						taxisAtPickUpPoint.add(bookTaxi);
					}
				
				}				
			}
			if(taxisAtPickUpPoint.size() == 0) {
				while(pointMap.size()!=0 && taxisAtPickUpPoint.size() == 0) {
				pointMap.remove(pointname);
				pointname = getNearestPoint(pointMap,pick);
				for(BookTaxi bookTaxi:bookTaxis) {
					if(bookTaxi.getDropPoint().equals(pointname) &&  bookTaxi.getEndTime()<=time) {
						taxisAtPickUpPoint.add(bookTaxi);
					}
					
				}
				}
			}
			if(taxisAtPickUpPoint.size()>0) {
			if(taxisAtPickUpPoint.size() == 1) {
				availableTaxi = taxisAtPickUpPoint.get(0);
			}else{
				HashMap<Integer, Long> revneuemap = new HashMap<>();
				TreeMap<Long, BookTaxi> mapTaxi = new TreeMap<Long, BookTaxi>();
				//List<Object> totalRevenuMap = objBookTaxiRepo.findTotalRevenue();
				long rev = 0;
				BookTaxi objTax = new BookTaxi();
				for(BookTaxi bookTaxi:bookTaxis) {
					
					for(int i=0;i<taxisAtPickUpPoint.size();i++) {
						
						objTax = taxisAtPickUpPoint.get(i);
						rev = objTax.getRevenue();
						if(objTax.getTaxiId()== bookTaxi.getTaxiId())
						{
							
							rev=rev+bookTaxi.getRevenue();
							
							//taxisAtPickUpPoint.get(i).setRevenue(rev);
						}
						revneuemap.put(objTax.getTaxiId(), rev);
					}
				
				}
				for(BookTaxi bookTaxi:taxisAtPickUpPoint) {
					for(Entry<Integer, Long> entryObj:revneuemap.entrySet()) {
					if(entryObj.getKey() == bookTaxi.getTaxiId())
						mapTaxi.put(entryObj.getValue(), bookTaxi);
					}
				}
				availableTaxi = mapTaxi.firstEntry().getValue();
			//	availableTaxi.setId(bookTaxis.size());
			}
			
			if(availableTaxi.getDistance() == 0) {
				objBookTaxiRepo.deleteById(availableTaxi.getId());
			}
			}else {
				availableTaxi = null;
			}
		}
		return availableTaxi;
	}

	private String getNearestPoint(HashMap<String, Point> pointMap, Point pick) {
		
	//	Point nearestPoint = new Point();
		//Point pick = pointMap.get(pickPoint);
		String closePoint = "";
		long dist=75*6;
		
		
		for(Entry<String, Point> point:pointMap.entrySet()) {
			
			long cal = Math.abs(pick.getDistance() - point.getValue().getDistance());
			
			if(cal<dist && cal!=0) {
				dist = cal;
				//nearestPoint = point.getValue();
				closePoint = point.getKey();
			}
		}
		return closePoint;
	}

	private int confirmTaxiBooking(HashMap<String, Point> pointMap, BookTaxi bookTaxi, int custId, String pickPoint, String dropPoint, int time) {
		
		Point pickuppoint =pointMap.get(pickPoint);
		Point droppingpoint =pointMap.get(dropPoint);
		
		long distancetravelled  = Math.abs(droppingpoint.getDistance()-pickuppoint.getDistance());
		long charges = Math.abs(100+((distancetravelled-5)*10));
		int timeTaken = droppingpoint.getTime()-pickuppoint.getTime();
		int endTime = time+timeTaken;
		
		BookTaxi bookedTaxi = new BookTaxi(bookTaxi.getTaxiId(), distancetravelled, charges, pickPoint, dropPoint, endTime,time);

		objBookTaxiRepo.save(bookedTaxi);
		
		User customer = new User(custId, pickPoint, dropPoint, charges, bookedTaxi);
		objUserRepo.save(customer);
		return bookTaxi.getTaxiId();
		
	}


	@Override
	public List<BookTaxi> getTripHistory() {
		List<BookTaxi> tripHistory = (List<BookTaxi>)objBookTaxiRepo.findAll();
		return tripHistory;
	}


	@Override
	public List<User> getUserHistory() {
		List<User> userHistory = (List<User>)objUserRepo.findAll();
		return userHistory;
	}

}
