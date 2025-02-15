package com.digita.banking_api.service.impl;

import com.digita.banking_api.dto.ScheduleTransferDto;
import com.digita.banking_api.entity.Account;
import com.digita.banking_api.entity.ScheduleTransfer;
import com.digita.banking_api.mapper.ScheduleTransferMapper;
import com.digita.banking_api.repository.AccountRepository;
import com.digita.banking_api.repository.ScheduleTransferRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {


    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    ScheduleTransferRepository scheduleTransferRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ScheduleTransferMapper scheduleTransferMapper;


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

        // ScheduleTransferDto
        ScheduleTransferDto dto = new ScheduleTransferDto();
        dto.setFromAccountId(1L);
        dto.setToAccountId(2L);
        dto.setAmount(95000);
        dto.setTransferDate(transferDate);

        //Sender Account
        Account account1 = new Account();
        account1.setId(1L);
        account1.setAccountHolderName("Sender");
        account1.setBalance(150000L);

        //Receiver Account

        Account account2 = new Account();
        account2.setId(2L);
        account2.setAccountHolderName("Receiver");
        account2.setBalance(10000L);

        // Saved Entity
        ScheduleTransfer savedEntity = new ScheduleTransfer();
        savedEntity.setId(9L);
        savedEntity.setFromAccountId(dto.getFromAccountId());
        savedEntity.setToAccountId(dto.getToAccountId());
        savedEntity.setAmount(dto.getAmount());
        savedEntity.setTransferDate(dto.getTransferDate());
        savedEntity.setTransferId("f7dfb666-d23e-4f4c-add1-af0af170d2c0");
        savedEntity.setTimestamp(timeStampDate);

        // Returned Dto
        ScheduleTransferDto returnedDto = new ScheduleTransferDto();
        returnedDto.setId(savedEntity.getId());
        returnedDto.setFromAccountId(savedEntity.getFromAccountId());
        returnedDto.setToAccountId(savedEntity.getToAccountId());
        returnedDto.setAmount(savedEntity.getAmount());
        returnedDto.setTransferDate(savedEntity.getTransferDate());
        returnedDto.setTransferId(savedEntity.getTransferId());
        returnedDto.setTimestamp(savedEntity.getTimestamp());


        Mockito.when(accountRepository.findById(dto.getFromAccountId())).thenReturn(Optional.of(account1));
        Mockito.when(accountRepository.findById(dto.getToAccountId())).thenReturn(Optional.of(account2));
        Mockito.when(scheduleTransferRepository.save(Mockito.any())).thenReturn(savedEntity);
        Mockito.when(scheduleTransferMapper.mapToScheduleTransferDto(Mockito.any())).thenReturn(returnedDto);

        ScheduleTransferDto result;
        result = accountService.createScheduleTransfer(dto);

        Assertions.assertEquals(savedEntity.getId(), result.getId());
        Assertions.assertEquals(returnedDto.getId(), result.getId());
        verify(scheduleTransferRepository, times(1)).save(Mockito.any());


    }


    @Test
    void testGetScheduleTransferById_Success() {

        //Given

        /*{
            "id": 9,
                "fromAccountId": 1,
                "toAccountId": 2,
                "amount": 95000.0,
                "transferDate": "2025-03-11T12:20:15",
                "transferId": "f7dfb666-d23e-4f4c-add1-af0af170d2c0",
                "timestamp": "2025-02-02T18:23:35"
        }*/
        LocalDateTime timeStampDate = LocalDateTime.parse("2025-02-02T18:23:35");
        LocalDateTime transferDate = LocalDateTime.parse("2025-03-11T12:20:15");


        ScheduleTransfer s = new ScheduleTransfer();
        s.setId(9L);
        s.setFromAccountId(1L);
        s.setToAccountId(2L);
        s.setAmount(95000);
        s.setTransferId("f7dfb666-d23e-4f4c-add1-af0af170d2c0");
        s.setTimestamp(timeStampDate);


        // Returned Dto
        ScheduleTransferDto returnedDto = new ScheduleTransferDto();
        returnedDto.setId(s.getId());
        returnedDto.setFromAccountId(s.getFromAccountId());
        returnedDto.setToAccountId(s.getToAccountId());
        returnedDto.setAmount(s.getAmount());
        returnedDto.setTransferDate(s.getTransferDate());
        returnedDto.setTransferId(s.getTransferId());
        returnedDto.setTimestamp(s.getTimestamp());


        given(scheduleTransferRepository.findById(9L)).willReturn(Optional.of(s));
        Mockito.when(scheduleTransferMapper.mapToScheduleTransferDto(Mockito.any())).thenReturn(returnedDto);


        //When
        ScheduleTransferDto returnedScheduleTransferDto = accountService.getScheduleTransferById(9L);

        //Then
        assertThat(returnedScheduleTransferDto.getId()).isEqualTo(s.getId());
        assertThat(returnedScheduleTransferDto.getFromAccountId()).isEqualTo(s.getFromAccountId());
        assertThat(returnedScheduleTransferDto.getToAccountId()).isEqualTo(s.getToAccountId());
        assertThat(returnedScheduleTransferDto.getAmount()).isEqualTo(s.getAmount());
        assertThat(returnedScheduleTransferDto.getTransferDate()).isEqualTo(s.getTransferDate());
        assertThat(returnedScheduleTransferDto.getTransferId()).isEqualTo(s.getTransferId());
        assertThat(returnedScheduleTransferDto.getTimestamp()).isEqualTo(s.getTimestamp());
        verify(scheduleTransferRepository, times(1)).findById(9L);

    }


}