package com.sparta.newsfeedapp.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sparta.newsfeedapp.entity.UserStatusEnum.UNCHECKED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {

    @Test
    @DisplayName("User Entity 검사")
    public void userEntityTest() {
        // given
        String userId = "woojin";
        String password = "1234";
        String name = "우진";
        String email = "rladnwlsok@naver.com";
        String bio = "짱짱맨";
        UserStatusEnum userStatus = UNCHECKED;

        // when
        User user = new User(userId, password, email, name, bio, userStatus);

        // then
        assertEquals("woojin", user.getUserId());
        assertEquals("1234", user.getPassword());
        assertEquals("우진", user.getName());
        assertEquals("rladnwlsok@naver.com", user.getEmail());
        assertEquals("짱짱맨", user.getBio());
        assertEquals(UNCHECKED, user.getUserStatus());
    }

}
