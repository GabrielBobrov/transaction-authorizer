package com.gabriel.transaction.authorizer.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationModel;
import com.gabriel.transaction.authorizer.core.model.CreateTransactionAuthorizationResultModel;
import com.gabriel.transaction.authorizer.core.ports.in.service.transaction.ITransactionServicePort;
import com.gabriel.transaction.authorizer.dummies.TransactionDummy;
import com.gabriel.transaction.authorizer.entrypoint.UrlConstant;
import com.gabriel.transaction.authorizer.entrypoint.dto.request.CreateTransactionAuthorizationRequestDTO;
import com.gabriel.transaction.authorizer.entrypoint.mapper.ITransactionAuthorizationEntrypointMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionAuthorizationControllerTest {

    @MockBean
    private ITransactionServicePort transferServicePort;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createTransactionAuthorization_returnsResponseDTO_whenValidRequest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        CreateTransactionAuthorizationRequestDTO requestDTO = TransactionDummy.createTransactionAuthorizationRequestDTOBuilder(accountId, transactionId).build();
        CreateTransactionAuthorizationModel model = TransactionDummy.createTransactionAuthorizationModelBuilder(accountId, transactionId).build();
        CreateTransactionAuthorizationResultModel resultModel = TransactionDummy.createTransactionAuthorizationResultModelBuilder().build();

        when(transferServicePort.authorizeTransaction(model)).thenReturn(resultModel);

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(post(UrlConstant.TRANSACTION_AUTHORIZATION_URI, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}