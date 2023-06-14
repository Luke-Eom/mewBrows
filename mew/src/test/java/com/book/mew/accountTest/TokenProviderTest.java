package com.book.mew.accountTest;

import com.book.mew.user.security.testingFiles.TokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Value("${security.jwt.token.secret-key")
    private String secretKey;

    private final TokenProvider invalidSecretKey = new TokenProvider("유효하지 않은 시크릿 키입니다", 123456789L);

    @Test
    @DisplayName("")
    void createToken() {
        final String payload = String.valueOf(1L);

//        final String token = tokenProvider.createToken(payload);
//
//        assertThat(token).isNotNull();

    }


}
