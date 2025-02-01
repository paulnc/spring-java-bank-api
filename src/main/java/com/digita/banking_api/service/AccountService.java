package com.digita.banking_api.service;

import com.digita.banking_api.dto.*;

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

    void scheduleTransfer(ScheduleTransferFundDto scheduleTransferFundDto);

    List<ScheduleTransferDto> getScheduleTransfers(Long fromAccountId);


}
