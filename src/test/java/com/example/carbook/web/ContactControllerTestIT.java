package com.example.carbook.web;

import com.example.carbook.model.dto.MessageDTO;
import com.example.carbook.service.CarService;
import com.example.carbook.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;


    @Test
    void testContactPage() throws Exception {
        // Perform an HTTP GET request to "/contact"
        ResultActions result = mockMvc.perform(get("/contact"));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "contact"
        result.andExpect(view().name("contact"));

        // Verify that the "messageDTO" attribute is added to the model with the correct data
        result.andExpect(model().attribute("messageDTO", notNullValue()));
        result.andExpect(model().attribute("messageDTO", hasProperty("nameOfUser", nullValue())));
        result.andExpect(model().attribute("messageDTO", hasProperty("emailOfUser", nullValue())));
        result.andExpect(model().attribute("messageDTO", hasProperty("subject", nullValue())));
        result.andExpect(model().attribute("messageDTO", hasProperty("message", nullValue())));
    }
    @Test
    void testAddMessageValid() throws Exception {
        // Given
        MessageDTO validMessage = createValidMessageDTO();

        // When
        ResultActions result = performAddRequest(validMessage);

        // Then
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contact"))
                .andExpect(flash().attribute("successMessage", "Message successfully sent !"));

        verify(messageService, times(1)).save(validMessage);
    }
    @Test
    void testAddMessageInvalid() throws Exception {
        // Given
        MessageDTO invalidMessage = createInvalidMessageDTO();

        // When
        ResultActions result = performAddRequest(invalidMessage);

        // Then
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contact"))
                .andExpect(MockMvcResultMatchers.flash().attribute("messageDTO", invalidMessage));

        verify(messageService, never()).save(any());
    }
    private MessageDTO createInvalidMessageDTO() {
        // Create an invalid message with missing fields
        return new MessageDTO();
    }
    private MessageDTO createValidMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setNameOfUser("John Doe");
        messageDTO.setEmailOfUser("john@example.com");
        messageDTO.setSubject("Test Subject");
        messageDTO.setMessage("Test Message");
        return messageDTO;
    }
    private ResultActions performAddRequest(MessageDTO messageDTO) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contact")
                .flashAttr("messageDTO", messageDTO);

        return mockMvc.perform(requestBuilder);
    }
}
