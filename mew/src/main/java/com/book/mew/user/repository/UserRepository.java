package com.book.mew.user.repository;

import com.book.mew.user.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByRealName(String realName);

    Optional<User> findByPhoneNumber(String phoneNumber);

    // error가 있거나 결과값이 안맞으면 @Query 사용
    Optional<User> findByRealNameAndPhoneNumber(String username, String phoneNumber);

}
