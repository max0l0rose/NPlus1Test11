package com.example.nplus1test2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "name")
public class Lifeguard extends BaseEntity {

    @ManyToOne
    //@JoinColumn
    private Beach beach;

    @ManyToOne(
            //fetch = FetchType.LAZY // Default EAGER!
    )
    //@JoinColumn
    private Lifeguard boss;

    private Integer speed;

    //@OneToOne(fetch = FetchType.LAZY) //default EAGER;
    @ManyToOne(fetch = FetchType.LAZY //fetch() default EAGER;
    )
    @BatchSize(size = 4)
    //@LazyToOne(LazyToOneOption.NO_PROXY)
    private Boat boat;
}
