package com.comment.Repository;

import com.comment.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUsername(String username);
}
