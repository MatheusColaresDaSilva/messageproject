package com.smsender.repository;

import com.smsender.entity.ClientPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientPlanRepository extends JpaRepository<ClientPlan, Long> {

    @Query("SELECT cp FROM ClientPlan cp WHERE cp.id.client.id = :clientId AND cp.id.plan.id = :planId")
    Optional<ClientPlan> findClientPlanByClientIdAndPlanId(@Param("clientId") Long clientId, @Param("planId") Long planId);
}
