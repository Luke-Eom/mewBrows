package com.book.mew.user.repository;

import com.book.mew.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByPhoneNumber(Integer phoneNumber);

    // error가 있거나 결과값이 안맞으면 @Query 사용
    Optional<User> findByUserNameAndPhoneNumber(String username, Integer phoneNumber);

}
