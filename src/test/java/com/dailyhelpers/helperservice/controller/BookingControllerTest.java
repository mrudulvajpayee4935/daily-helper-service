package com.dailyhelpers.helperservice.controller;

import com.dailyhelpers.helperservice.model.Booking;
import com.dailyhelpers.helperservice.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testCreateBooking_Success() throws Exception {
        Booking booking = new Booking();
        booking.setUserId(1L);
        booking.setHelperId(2L);
        booking.setBookingTime(LocalDateTime.now());

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.helperId").value(2L));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetBookingsByUser_Success() throws Exception {
        Long userId = 1L;
        Booking booking1 = new Booking();
        booking1.setId(1L);
        booking1.setUserId(userId);
        booking1.setHelperId(2L);
        booking1.setBookingTime(LocalDateTime.now());

        Booking booking2 = new Booking();
        booking2.setId(2L);
        booking2.setUserId(userId);
        booking2.setHelperId(3L);
        booking2.setBookingTime(LocalDateTime.now().plusHours(1));

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingRepository.findByUserId(userId)).thenReturn(bookings);

        mockMvc.perform(get("/bookings/user/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[1].userId").value(userId))
                .andExpect(jsonPath("$.length()").value(2));
    }
}
