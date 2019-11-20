package com.training.salon.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "proced")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "proced_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
