package com.deepika.testProjects;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface BookTaxiRepo extends CrudRepository<BookTaxi, Integer>  {

	ArrayList<BookTaxi> findByDropPoint(String pickPoint);
	

}
