package com.leeg.contactimp.repository;

import com.leeg.contactimp.model.UserContactModel;
import com.leeg.contactimp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserContactRepo extends JpaRepository<UserContactModel, UUID> {
}
