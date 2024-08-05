package com.smsender.utils;

import com.smsender.entity.*;
import com.smsender.enums.PlanType;
import com.smsender.enums.StatusType;
import com.smsender.exception.PlanNotFoundException;
import com.smsender.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component()
@Profile("!test")
public class Initialzer implements ApplicationRunner {

    private PlanRepository planRepository;

    public Initialzer(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Optional<Plan> pos = planRepository.findByType(PlanType.POS);
        if(!pos.isPresent()) {
            Plan plan1 = new Plan();
            plan1.setType(PlanType.POS);
            planRepository.save(plan1);
        }
        Optional<Plan> pre = planRepository.findByType(PlanType.PRE);
        if(!pre.isPresent()) {
            Plan plan2 = new Plan();
            plan2.setType(PlanType.PRE);
            planRepository.save(plan2);
        }

    }

}
