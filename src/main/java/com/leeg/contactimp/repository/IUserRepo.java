package com.leeg.contactimp.repository;

import com.leeg.contactimp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepo extends JpaRepository<UserModel, UUID> {
    UserModel findByUsernameAndAndPassword(String username, String password);
}
