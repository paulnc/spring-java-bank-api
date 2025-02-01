/*
 * Copyright (c) 2025. Paul Nwabudike
 * Since: January 2025
 * Author: Paul Nwabudike
 * Name: AccountServiceImpl
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at   http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.digita.banking_api.service.impl;

import com.digita.banking_api.dto.*;
import com.digita.banking_api.entity.Account;
import com.digita.banking_api.entity.ScheduleTransfer;
import com.digita.banking_api.entity.Transaction;
import com.digita.banking_api.exception.AccountException;
import com.digita.banking_api.mapper.AccountMapper;
import com.digita.banking_api.mapper.ScheduleTansferMapper;
import com.digita.banking_api.mapper.TransactionMapper;
import com.digita.banking_api.repository.AccountRepository;
import com.digita.banking_api.repository.ScheduleTransferRepository;
import com.digita.banking_api.repository.TransactionRepository;
import com.digita.banking_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/*
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
*/


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;
    private ScheduleTransferRepository scheduleTransferRepository;


    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER";

    public AccountServiceImpl(AccountRepository accountRepository,
                              TransactionRepository transactionRepository,
                              ScheduleTransferRepository scheduleTransferRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.scheduleTransferRepository = scheduleTransferRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));

        accountRepository.deleteById(id);
    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {

        // Retrieve the account from which we send the amount
        Account fromAccount = accountRepository
                .findById(transferFundDto.fromAccountId())
                .orElseThrow(() -> new AccountException("Account does not exists"));

        // Retrieve the account to which we send the amount
        Account toAccount = accountRepository.findById(transferFundDto.toAccountId())
                .orElseThrow(() -> new AccountException("Account does not exists"));

        if (fromAccount.getBalance() < transferFundDto.amount()) {
            throw new RuntimeException("Insufficient Amount");
        }
        // Debit the amount from fromAccount object
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());

        // Credit the amount to toAccount object
        toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());

        accountRepository.save(fromAccount);

        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDto> getAccountTransactions(Long accountId) {

        List<Transaction> transactions = transactionRepository
                .findByAccountIdOrderByTimestampDesc(accountId);

        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }


    @Override
    public void scheduleTransfer(ScheduleTransferFundDto scheduleTransferFundDto) {


        // Retrieve the account from which we send the amount
        //      Account fromAccount = accountRepository
        //            .findById(scheduleTransferFundDto.fromAccountId())
        //          .orElseThrow(() -> new AccountException("Account does not exists"));

        // Retrieve the account to which we send the amount
        //Account toAccount = accountRepository.findById(scheduleTransferFundDto.toAccountId())
        //      .orElseThrow(() -> new AccountException("Account does not exists"));


        String transferId = UUID.randomUUID().toString();

        ScheduleTransfer scheduleTransfer = new ScheduleTransfer();
        scheduleTransfer.setFromAccountId(scheduleTransferFundDto.fromAccountId());
        scheduleTransfer.setToAccountId(scheduleTransferFundDto.toAccountId());
        scheduleTransfer.setAmount(scheduleTransferFundDto.amount());
        scheduleTransfer.setTransferDate(scheduleTransferFundDto.transferDate());
        scheduleTransfer.setTransferId(transferId);


        scheduleTransfer.setTimestamp(LocalDateTime.now());

        scheduleTransferRepository.save(scheduleTransfer);
    }

    @Override
    public List<ScheduleTransferDto> getScheduleTransfers(Long fromAccountId) {

        List<ScheduleTransfer> scheduleTransfers = scheduleTransferRepository
                .findByFromAccountIdOrderByTimestampDesc(fromAccountId);

        return scheduleTransfers.stream()
                .map(ScheduleTansferMapper::mapToScheduleTransferDto)
                .collect(Collectors.toList());

    }
}
