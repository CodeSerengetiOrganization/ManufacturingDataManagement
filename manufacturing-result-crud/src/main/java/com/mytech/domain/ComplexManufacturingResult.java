package com.mytech.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-17
 * @description :
 */
@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper =true)
@MappedSuperclass
@Table(name = "t_manufacturing_complex_result")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ComplexManufacturingResult extends ManufacturingResult{

    @Column(name = "f_result")
    @NotNull(message = "manufacturing result value is null")
    private Double result;

    @Tolerate
    public ComplexManufacturingResult(){}

}//class
