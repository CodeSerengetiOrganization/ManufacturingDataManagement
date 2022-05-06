package com.mytech.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-17
 * @description :
 */
@Data
@SuperBuilder(toBuilder = true)
@Entity
@EqualsAndHashCode(callSuper =true)
@ToString(callSuper = true)
@Table(name = "t_manufacturing_complex_result")
public class ComplexManufacturingResult extends ManufacturingResult{

    @Column(name = "f_result")
    @NotNull(message = "manufacturing result value is null")
    private Double result;

    @Tolerate
    public ComplexManufacturingResult(){}

}//class
