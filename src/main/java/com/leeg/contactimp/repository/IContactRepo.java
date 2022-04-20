package com.leeg.contactimp.repository;

import com.leeg.contactimp.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IContactRepo extends JpaRepository<ContactModel, UUID> {
}
