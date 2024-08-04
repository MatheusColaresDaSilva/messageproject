package com.smsender.controller;

import com.smsender.dto.response.ResponseDTO;
import com.smsender.entity.Plan;
import com.smsender.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController extends BaseController{

    private PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Plan>> findById(@PathVariable("id") Long id) {
        Plan response = planService.getPlanById(id);
        return ResponseEntity.ok(new ResponseDTO<>(response));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Plan>>> findAll() {
        List<Plan> response = planService.getAllPlans();
        return ResponseEntity.ok(new ResponseDTO<>(response));
    }

}
