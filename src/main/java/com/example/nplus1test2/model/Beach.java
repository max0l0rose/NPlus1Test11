package com.example.nplus1test2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "name")
@FieldDefaults(level = AccessLevel.PRIVATE)

@NamedEntityGraph(name = "Beach.lifeguards"
        , attributeNodes = @NamedAttributeNode("lifeguards")
)
public class Beach extends BaseEntity {

    @OneToMany(mappedBy = "beach" // simplifies graph and counts size...
            //, fetch = FetchType.EAGER // Default LAZY !!!
    )
    //@JoinColumn(name="beach_id")
    //@LazyCollection(LazyCollectionOption.EXTRA) // Get Collection Size // HB

    //@Fetch(value = FetchMode.JOIN)        // Right away (@BatchSize)
    //@Fetch(value = FetchMode.SELECT)      // Like JOIN but on request (@BatchSize)        // DEFAULT
    //@Fetch(value = FetchMode.SUBSELECT)   // Thw whole relation (@BatchSize) on request

    @BatchSize(size=3)
    //@Basic(fetch=FetchType.EAGER)
    List<Lifeguard> lifeguards;

    @OneToOne()
    private Boat boat;
}

