package com.sparta.newsfeedapp.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.newsfeedapp.NewsfeedAppApplication;
import com.sparta.newsfeedapp.config.WebSecurityConfig;
import com.sparta.newsfeedapp.controller.CommentController;
import com.sparta.newsfeedapp.dto.post.PostRequestDto;
import com.sparta.newsfeedapp.dto.post.PostResponseDto;
import com.sparta.newsfeedapp.entity.Post;
import com.sparta.newsfeedapp.entity.User;
import com.sparta.newsfeedapp.entity.UserStatusEnum;
import com.sparta.newsfeedapp.security.UserDetailsImpl;
import com.sparta.newsfeedapp.service.CommentService;
import com.sparta.newsfeedapp.service.PostService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static com.sparta.newsfeedapp.entity.UserStatusEnum.ACTIVE;
import static com.sparta.newsfeedapp.entity.UserStatusEnum.UNCHECKED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {PostCommentMvcTest.class, CommentController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
public class PostCommentMvcTest {
    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @MockBean
    CommentService commentService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유저 생성
        String userId = "test1234567";
        String password = "test1234567!";
        String name = "김우진";
        String email = "rladnwlsok@naver.com";
        String bio = "testbio";
        UserStatusEnum userStatus = ACTIVE;

        User testuser = new User(userId, password, email, name, bio, userStatus);

        UserDetailsImpl testUserDetails = new UserDetailsImpl(testuser);

        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("Post 생성")
    void test1() throws Exception {
        // given
        this.mockUserSetup();
        String content = "게시글등록 테스트";
        PostRequestDto requestDto = new PostRequestDto(
                content
        );

        String postInfo = objectMapper.writeValueAsString(requestDto);

        // when - then
        mvc.perform(post("/api/posts")
                        .content(postInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}
