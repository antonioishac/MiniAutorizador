package br.com.miniautorizador.api.controller;

import br.com.miniautorizador.Application;
import br.com.miniautorizador.api.mock.CardEntityMock;
import br.com.miniautorizador.api.mock.SaveCardMock;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.repositories.entities.CardEntity;
import br.com.miniautorizador.domain.services.dtos.CardRespDTO;
import br.com.miniautorizador.domain.services.exceptions.CreateCardException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotNull;

@ActiveProfiles(profiles = {"test"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class SaveCardControllerTest {

    private final static String URL = "/cartoes";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardRepository repository;

    @Test
    public void test_create_card_with_success() throws Exception {
        BDDMockito.when(repository.save(ArgumentMatchers.any(CardEntity.class))).thenReturn(CardEntityMock.cardEntity());

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(SaveCardMock.createCardRequest())))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        CardRespDTO response = objectMapper.readValue(contentAsString, new TypeReference<CardRespDTO>() {
        });

        assertNotNull(response.getCardNumber());
    }

    @Test
    public void test_create_card_exists_card_exception() throws Exception {
        BDDMockito.when(repository.save(ArgumentMatchers.any(CardEntity.class))).thenThrow(CreateCardException.class);

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(SaveCardMock.createCardRequest())))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isUnprocessableEntity())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        CardRespDTO response = objectMapper.readValue(contentAsString, new TypeReference<CardRespDTO>() {
        });

        assertNotNull(response.getCardNumber());
    }
}
