package com.Overonix.Test.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "e_role")
@Data
public class ERole extends BaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;
}
