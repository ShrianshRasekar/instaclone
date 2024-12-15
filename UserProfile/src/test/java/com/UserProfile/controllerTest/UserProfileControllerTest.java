package com.UserProfile.controllerTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.UserProfile.controller.ProfileController;
import com.UserProfile.entity.UserProfile;
import com.UserProfile.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController userProfileController;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeClass
    public static void init() {
        System.out.println("Before test");
        System.out.println("Started test: " + new Date());
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
    }

    @Test
    public void testGetUserProfiles() throws Exception {
        // Arrange
        List<UserProfile> mockProfiles = Arrays.asList(
                new UserProfile(1, "John Doe", "Johnathan Doe", "Software Developer", 50, 100, 200, 123),
                new UserProfile(2, "Jane Doe", "Jane Doe", "Product Manager", 30, 150, 250, 124)
        );

        when(profileService.getUserProfiles()).thenReturn(mockProfiles);

        // Act & Assert
        mockMvc.perform(get("/userprofile/profiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{\"pid\":1,\"uname\":\"John Doe\",\"fullName\":\"Johnathan Doe\",\"bio\":\"Software Developer\",\"posts\":50,\"followers\":100,\"following\":200,\"uid\":123}," +
                        "{\"pid\":2,\"uname\":\"Jane Doe\",\"fullName\":\"Jane Doe\",\"bio\":\"Product Manager\",\"posts\":30,\"followers\":150,\"following\":250,\"uid\":124}" +
                        "]"));
    }

    @Test
    public void addTest() {
        System.out.println("Add test");
        long result = ProfileController.add(4, 6);
        long expected = 10;
        Assert.assertEquals(expected, result);
    }

    @AfterClass
    public static void clean() {
        System.out.println("After test");
        System.out.println("Ended test: " + new Date());
    }
}
