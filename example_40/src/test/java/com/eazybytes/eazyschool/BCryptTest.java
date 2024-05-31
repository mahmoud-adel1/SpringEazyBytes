package com.eazybytes.eazyschool;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.function.Function;

public class BCryptTest {
    @Test
    public void test() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String hashedPassword = encoder.encode("12345");
//        System.out.println(hashedPassword);
        Function<String,String> encode = (input) -> new BCryptPasswordEncoder().encode(input);

        UserDetails user = User.builder()
                .username("admin")
                .password("admin")
                .passwordEncoder(encode)
                .roles("ADMIN").build();

        System.out.println(user.getPassword());
    }
}
