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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@Proxy(lazy=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "\"user_contacts\"")
public class UserContactModel {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @JoinColumn(name = "\"user\"")
    @OneToOne()
    private UserModel userModel;

    @JoinColumn(name = "contact")
    @OneToOne()
    private ContactModel contactModel;

}
