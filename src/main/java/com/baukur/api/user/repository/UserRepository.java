package com.baukur.api.user.repository;

import com.baukur.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    void deleteById(Long id);
    void editUsernameById(Long id, String email);
    void editUserById(Long id, User user);
}
