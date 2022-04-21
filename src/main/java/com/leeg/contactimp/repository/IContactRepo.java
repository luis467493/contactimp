package com.leeg.contactimp.repository;

import com.leeg.contactimp.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IContactRepo extends JpaRepository<ContactModel, UUID> {

    @Query(value = "select c.* " +
            "from users u, contacts c, user_contacts uc " +
            "where uc.contact = c.id " +
            "and uc.\"user\" = u.id " +
            "and u.username = ?", nativeQuery = true)
    List<ContactModel> findByUser(String username);
}
