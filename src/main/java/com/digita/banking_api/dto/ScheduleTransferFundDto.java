package com.digita.banking_api.dto;

import java.time.LocalDateTime;

public record ScheduleTransferFundDto(Long fromAccountId,
                                      Long toAccountId,
                                      double amount,
                                      LocalDateTime transferDate){


        }
