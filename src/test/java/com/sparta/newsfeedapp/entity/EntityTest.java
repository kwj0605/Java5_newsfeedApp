package com.sparta.newsfeedapp.entity;

import com.sparta.newsfeedapp.NewsfeedAppApplication;
import com.sparta.newsfeedapp.dto.comment.CommentCreateRequestDto;
import com.sparta.newsfeedapp.dto.post.PostRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sparta.newsfeedapp.entity.UserStatusEnum.UNCHECKED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {

    User user = new User();
    Post post = new Post();

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
        this.user = user;

        // then
        assertEquals("woojin", user.getUserId());
        assertEquals("1234", user.getPassword());
        assertEquals("우진", user.getName());
        assertEquals("rladnwlsok@naver.com", user.getEmail());
        assertEquals("짱짱맨", user.getBio());
        assertEquals(UNCHECKED, user.getUserStatus());
    }

    @Test
    @DisplayName("Post Entity 검사")
    public void postEntityTest() {
        // given
        String content = "테스트내용";
        User user = this.user;

        // when
        PostRequestDto requestDto = new PostRequestDto(content);
        Post post = new Post(requestDto, user);
        this.post = post;

        // then
        assertEquals("테스트내용", post.getContent());
    }

    @Test
    @DisplayName("Comment Entity 검사")
    public void commentEntityTest() {
        // given
        String content = "테스트";
        User user = this.user;
        Post post = this.post;

        // when
        CommentCreateRequestDto requestDto = new CommentCreateRequestDto(content);
        Comment comment = new Comment(requestDto, user, post);

        // then
        assertEquals("테스트", comment.getContent());
    }



}
