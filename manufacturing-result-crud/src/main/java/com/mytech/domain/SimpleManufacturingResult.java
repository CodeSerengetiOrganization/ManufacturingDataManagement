package com.mytech.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-07
 * @description :
 */
@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper =true)
@MappedSuperclass
@Table(name = "t_manufacturing_simple_result")
public class SimpleManufacturingResult extends Result{
    @Column(name = "f_result")
    @NotNull(message = "manufacturing result value is null")
    private Boolean result;
}
