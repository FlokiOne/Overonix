package com.Overonix.Test.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "e_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EAddress extends BaseEntity implements Serializable {
    @NotBlank
    @Size(max = 120)
    @Column
    private String country;

    @NotBlank
    @Size(max = 120)
    @Column
    private Double lat;

    @NotBlank
    @Size(max = 120)
    @Column
    private Double lon;
}
