package com.training.salon.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @OneToMany( mappedBy = "category",  cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Master> masters;

    @OneToMany( mappedBy = "category",  cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Procedure> procedures;

    @Transient
    private String base64;

    @Transient
    public String getBase64() {
        return this.base64 = new String(Base64.getEncoder().encode(this.image));
    }


}
