/*
 * Copyright (c) 2025. Paul Nwabudike
 * Since: January 2025
 * Author: Paul Nwabudike
 * Name: TransactionRepository
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at   http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.digita.banking_api.repository;

import com.digita.banking_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



    public interface TransactionRepository extends JpaRepository<Transaction, Long> {
        List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
    }

