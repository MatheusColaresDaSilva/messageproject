package com.smsender.repository;

import com.smsender.entity.Plan;
import com.smsender.enums.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByType(PlanType type);
}
