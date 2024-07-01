package br.com.miniautorizador.api.controller;

import br.com.miniautorizador.Application;
import br.com.miniautorizador.api.mock.CardEntityMock;
import br.com.miniautorizador.domain.repositories.CardRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles(profiles = {"test"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class GetBalanceCarControllerTest {

    private final static String URL = "/cartoes";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardRepository repository;

    @Test
    public void test_get_balance_card_with_success() throws Exception {
        BDDMockito.when(repository.findCardByBalance(ArgumentMatchers.anyString())).thenReturn(Optional.of(CardEntityMock.cardEntity()));

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(URL.concat("/2122232425262728"))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        assertNotNull(contentAsString);
    }

    @Test
    public void test_get_balance_card_not_found_exception() throws Exception {
        BDDMockito.when(repository.findCardByBalance(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(URL.concat("/2122232425262999"))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound())
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}
