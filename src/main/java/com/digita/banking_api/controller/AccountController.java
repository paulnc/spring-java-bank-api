/*
 * Copyright (c) 2025. Paul Nwabudike
 * Since: January 2025
 * Author: Paul Nwabudike
 * Name: AccountController
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at   http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.digita.banking_api.controller;

import com.digita.banking_api.dto.*;
import com.digita.banking_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    // Create New Account API
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw REST API6
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }

    // Build transfer REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto){
        accountService.transferFunds(transferFundDto);
        return ResponseEntity.ok("Transfer Successful");
    }

    // Build transactions REST API
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable("id") Long accountId){

        List<TransactionDto> transactions = accountService.getAccountTransactions(accountId);

        return ResponseEntity.ok(transactions);
    }



    // Build sheduleTransferfund REST API
    @PostMapping("/sheduleTransferfund")
    public ResponseEntity<String> createScheduleTransfer(@RequestBody ScheduleTransferDto scheduleTransferDto){
        accountService.createScheduleTransfer(scheduleTransferDto);
        return ResponseEntity.ok("Schedule Funds Transfer Successful");

    }


    // Get Schedule Transfer fund by Schedule Transfer ID REST API
    @GetMapping("/sheduleTransferfund/{id}")
    public ResponseEntity<ScheduleTransferDto> getScheduleTransferById(@PathVariable Long id){
        ScheduleTransferDto scheduleTransferDto = accountService.getScheduleTransferById(id);
        return ResponseEntity.ok(scheduleTransferDto);
    }

    // Delete Account REST API
    @DeleteMapping("/sheduleTransferfund/{id}")
    public ResponseEntity<String> deleteScheduleTransferById(@PathVariable Long id){
        accountService.deleteScheduleTransferById(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }


    //  Get All scheduleTransfers REST API by Account ID
    @GetMapping("/{id}/scheduleTransfer")
    public ResponseEntity<List<ScheduleTransferDto>> getScheduleTransfers(@PathVariable("id") Long fromAccountId){

        List<ScheduleTransferDto> scheduleTransfers = accountService.getScheduleTransfers(fromAccountId);

        return ResponseEntity.ok(scheduleTransfers);
    }


}

