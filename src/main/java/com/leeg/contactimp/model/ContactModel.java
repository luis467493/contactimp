package com.leeg.contactimp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@Proxy(lazy=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "\"contacts\"")
public class ContactModel {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "credit_card")
    private String credit_card;

    @Column(name = "franchise")
    private String franchise;

    @Column(name = "email")
    private String email;
}
