package com.smsender.utils;

import com.smsender.entity.*;
import com.smsender.enums.PlanType;
import com.smsender.enums.StatusType;
import com.smsender.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Initialzer implements ApplicationRunner {

    private PlanRepository planRepository;

    public Initialzer(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Plan plan1 = new Plan();
        plan1.setType(PlanType.POS);
        planRepository.save(plan1);

        Plan plan2 = new Plan();
        plan2.setType(PlanType.PRE);
        planRepository.save(plan2);

    }

}
