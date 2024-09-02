package com.krushit.ms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit.entity.TravelPlan;
import com.krushit.service.ITravelPlanMgmtService;

@RestController
@RequestMapping("/travelplan-api")
public class TravelPlanOperationsController {
	@Autowired
	private ITravelPlanMgmtService service;

	@GetMapping("/categories")
	public ResponseEntity<?> showTravelPlanCategories() {
		try {
			Map<Integer, String> mapCategories = service.getTrvelPlanCategories();
			return new ResponseEntity<Map<Integer, String>>(mapCategories, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveTourPlan(@RequestBody TravelPlan plan) {
		try {
			String msg = service.registerTravelPlan(plan);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

	}
	
	@GetMapping("/travelplans")
	public ResponseEntity<?> showAllTravelPlans() {
		try {
			List<TravelPlan> plans = service.showAllTravelPlan();
			return new ResponseEntity<List<TravelPlan>>(plans, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/planByID/{id}")
	public ResponseEntity<?> showTravelPlanByID(@PathVariable Integer id) {
		try {
			TravelPlan plan = service.showTravelPlanByID(id);
			return new ResponseEntity<TravelPlan>(plan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatePlan")
	public ResponseEntity<String> updateTravelPlan(@RequestBody TravelPlan plan) {
		try {
			String msg =service.updateTravelPlan(plan);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deletePlan/{id}")
	public ResponseEntity<String> deleteTravelPlan(@PathVariable Integer id) {
		try {
			String msg =service.deleteTravelPlan(id);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/changeStatus/{id}/{status}")
	public ResponseEntity<String> deleteTravelPlan(@PathVariable Integer id, @PathVariable String status) {
		try {
			String msg =service.changeTravelPlanStatus(id, status);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
