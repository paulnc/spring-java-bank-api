package com.digita.banking_api.dto;

import java.time.LocalDateTime;

public record ScheduleTransferDto(
        Long id,
        Long fromAccountId,
        Long toAccountId,
        double amount,
        LocalDateTime transferDate,
        String transferId,
        LocalDateTime timestamp) {
}

