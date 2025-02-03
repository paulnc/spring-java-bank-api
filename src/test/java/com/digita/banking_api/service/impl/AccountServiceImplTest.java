package com.digita.banking_api.service.impl;

import com.digita.banking_api.dto.ScheduleTransferDto;
import com.digita.banking_api.entity.ScheduleTransfer;
import com.digita.banking_api.repository.ScheduleTransferRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    ScheduleTransferRepository scheduleTransferRepository;

    @InjectMocks
    AccountServiceImpl accountService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getScheduleTransferByIdSuccess() {

        //Given


        /*{
            "id": 9,
                "fromAccountId": 1,
                "toAccountId": 2,
                "amount": 95000.0,
                "transferDate": "2025-03-11T12:20:15",
                "transferId": "f7dfb666-d23e-4f4c-add1-af0af170d2c0",
                "timestamp": "2025-02-02T18:23:35"
        }
*/
        LocalDateTime timeStampDate = LocalDateTime.parse("2025-02-02T18:23:35");
        LocalDateTime transferDate = LocalDateTime.parse("2025-03-11T12:20:15");


        ScheduleTransfer s = new ScheduleTransfer();
        s.setId(9L);
        s.setFromAccountId(1L);
        s.setToAccountId(2L);
        s.setAmount(95000);
        s.setTransferId("f7dfb666-d23e-4f4c-add1-af0af170d2c0");
        s.setTimestamp(timeStampDate);


        given(scheduleTransferRepository.findById(9L)).willReturn(Optional.of(s));


        //When
        ScheduleTransferDto returnedScheduleTransferDto = accountService.getScheduleTransferById(9L);

        //Then
        assertThat(returnedScheduleTransferDto.id()).isEqualTo(s.getId());
        assertThat(returnedScheduleTransferDto.fromAccountId()).isEqualTo(s.getFromAccountId());
        assertThat(returnedScheduleTransferDto.toAccountId()).isEqualTo(s.getToAccountId());
        assertThat(returnedScheduleTransferDto.amount()).isEqualTo(s.getAmount());
        assertThat(returnedScheduleTransferDto.transferDate()).isEqualTo(s.getTransferDate());
        assertThat(returnedScheduleTransferDto.transferId()).isEqualTo(s.getTransferId());
        assertThat(returnedScheduleTransferDto.timestamp()).isEqualTo(s.getTimestamp());
        verify(scheduleTransferRepository, times(1)).findById(9L);

    }
}