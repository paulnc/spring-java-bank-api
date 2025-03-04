package com.digita.banking_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public record ScheduleTransferFundDto(Long fromAccountId,
                                      Long toAccountId,
                                      double amount,
                                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                      LocalDateTime transferDate
                                      ){


        }
