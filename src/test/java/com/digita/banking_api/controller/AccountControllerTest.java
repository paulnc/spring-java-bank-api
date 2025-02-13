package com.digita.banking_api.controller;

import com.digita.banking_api.dto.ScheduleTransferDto;
import com.digita.banking_api.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateScheduleTransfer_Success() {
        LocalDateTime timeStampDate = LocalDateTime.parse("2025-02-02T18:23:35");
        LocalDateTime transferDate = LocalDateTime.parse("2025-03-11T12:20:15");


        ScheduleTransferDto dto  = new ScheduleTransferDto();
        dto.setFromAccountId(1L);
        dto.setToAccountId(2L);
        dto.setAmount(95000);
        dto.setTransferDate(transferDate);


        ScheduleTransferDto savedDto  = new ScheduleTransferDto();
        savedDto.setId(9L);
        savedDto.setFromAccountId(dto.getFromAccountId());
        savedDto.setToAccountId(dto.getToAccountId());
        savedDto.setAmount(dto.getAmount());
        savedDto.setTransferDate(dto.getTransferDate());
        savedDto.setTransferId("f7dfb666-d23e-4f4c-add1-af0af170d2c0");
        savedDto.setTimestamp(timeStampDate);



        Mockito.when(accountService.createScheduleTransfer(dto)).thenReturn(savedDto);
        ResponseEntity<ScheduleTransferDto> responseEntity = accountController.createScheduleTransfer(dto);
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }
}