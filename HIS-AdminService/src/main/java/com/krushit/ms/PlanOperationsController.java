package com.krushit.ms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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

import com.krushit.model.PlanData;
import com.krushit.entity.PlanEntity;
import com.krushit.model.CategoryData;
import com.krushit.service.IPlanMgmtService;
import com.krushit.service.PlanMgmtServiceImpl;

@RestController
@RequestMapping("/plan-api")
public class PlanOperationsController {

    @Autowired
    private IPlanMgmtService service;

    @GetMapping("/categories")
    public ResponseEntity<?> showPlanCategories() {
        try {
            Map<Integer, String> categories = service.getPlanCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPlan(@RequestBody PlanData plan) {
        try {
            String msg = service.registerPlan(plan);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/registerCategory")
    public ResponseEntity<String> registerPlanCategory(@RequestBody CategoryData category) {
        try {
            String msg = service.registerPlanCategory(category);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plans")
    public ResponseEntity<?> showAllPlans() {
        try {
            List<PlanData> plans = service.showAllPlans();
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/planByID/{id}")
    public ResponseEntity<?> showPlanByID(@PathVariable Integer id) {
        try {
        	PlanEntity entity = service.showTravelPlanByID(id); 
            PlanData data = new PlanData();
            BeanUtils.copyProperties(entity, data);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePlan")
    public ResponseEntity<String> updatePlan(@RequestBody PlanData plan) {
        try {
            String msg = service.updatePlan(plan);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePlan/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer id) {
        try {
            String msg = service.deletePlan(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/changeStatus/{id}/{status}")
    public ResponseEntity<String> changePlanStatus(@PathVariable Integer id, @PathVariable String status) {
        try {
            String msg = service.changePlanStatus(id, status);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
