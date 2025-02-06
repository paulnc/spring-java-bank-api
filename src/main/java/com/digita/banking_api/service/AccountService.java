package com.digita.banking_api.service;

import com.digita.banking_api.dto.*;
import com.digita.banking_api.entity.ScheduleTransfer;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    void transferFunds(TransferFundDto transferFundDto);

    List<TransactionDto> getAccountTransactions(Long accountId);

    ScheduleTransferDto createScheduleTransfer(ScheduleTransferDto scheduleTransferDto);

    ScheduleTransferDto getScheduleTransferById(Long id);

     void deleteScheduleTransferById(Long id);

    List<ScheduleTransferDto> getScheduleTransfers(Long fromAccountId);


}
