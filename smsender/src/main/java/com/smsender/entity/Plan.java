package com.smsender.entity;

import com.smsender.enums.PlanType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PLAN")
public class Plan extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private PlanType type;
}
