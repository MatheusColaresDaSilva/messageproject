package com.smsender.entity;

import com.smsender.enums.PlanType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PLAN")
public class Plan extends BaseEntity {

    private PlanType type;
}
