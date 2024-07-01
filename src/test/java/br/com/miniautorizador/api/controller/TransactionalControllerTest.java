package br.com.miniautorizador.api.controller;

import br.com.miniautorizador.Application;
import br.com.miniautorizador.api.mock.CardEntityMock;
import br.com.miniautorizador.api.mock.SaveCardMock;
import br.com.miniautorizador.api.mock.TransactionCardMock;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.repositories.entities.CardEntity;
import br.com.miniautorizador.domain.services.dtos.CardRespDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles(profiles = {"test"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class TransactionalControllerTest {

    private final static String URL = "/transacoes";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardRepository repository;

    @Test
    public void test_transactional_card_with_success() throws Exception {
        BDDMockito.when(repository.findByCardNumber(ArgumentMatchers.anyString())).thenReturn(Optional.of(CardEntityMock.cardEntity()));
        BDDMockito.when(repository.findByCardNumberAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(CardEntityMock.cardEntity()));
        BDDMockito.doNothing().when(repository).updateCardBalance(ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.anyString());

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(TransactionCardMock.transactionRequestMock())))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("OK", contentAsString);
    }

    @Test
    public void test_transactional_card_with_insufficient_balance_exception() throws Exception {
        BDDMockito.when(repository.findByCardNumber(ArgumentMatchers.anyString())).thenReturn(Optional.of(CardEntityMock.cardEntity()));
        BDDMockito.when(repository.findByCardNumberAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(CardEntityMock.cardEntity()));

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(TransactionCardMock.transactionInsufficientBalanceRequestMock())))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isUnprocessableEntity())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("SALDO_INSUFICIENTE", contentAsString);
    }

    @Test
    public void test_transactional_card_with_error_password_exception() throws Exception {
        BDDMockito.when(repository.findByCardNumber(ArgumentMatchers.anyString())).thenReturn(Optional.of(CardEntityMock.cardEntity()));
        BDDMockito.when(repository.findByCardNumberAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(TransactionCardMock.transactionPasswordErrorRequestMock())))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isUnprocessableEntity())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("SENHA_INVALIDA", contentAsString);
    }

    @Test
    public void test_transactional_card_not_exists_exception() throws Exception {
        BDDMockito.when(repository.findByCardNumber(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(TransactionCardMock.transactionRequestMock())))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isUnprocessableEntity())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("CARTAO_INEXISTENTE", contentAsString);
    }
}
