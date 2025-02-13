/*
 * Copyright (c) 2025. Paul Nwabudike
 * Since: February 2025
 * Author: Paul Nwabudike
 * Name: ScheduleTansferMapper
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at   http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.digita.banking_api.mapper;

import com.digita.banking_api.dto.ScheduleTransferDto;
import com.digita.banking_api.entity.ScheduleTransfer;

public class ScheduleTansferMapper {

    public static ScheduleTransferDto mapToScheduleTransferDto(ScheduleTransfer scheduleTransfer) {
        return new ScheduleTransferDto(
                scheduleTransfer.getId(),
                scheduleTransfer.getFromAccountId(),
                scheduleTransfer.getToAccountId(),
                scheduleTransfer.getAmount(),
                scheduleTransfer.getTransferDate(),
                scheduleTransfer.getTransferId(),
                scheduleTransfer.getTimestamp()
        );
    }

    public static ScheduleTransfer mapToScheduleTransfer(ScheduleTransferDto scheduleTransferDto) {
        return new ScheduleTransfer(
                scheduleTransferDto.getId(),
                scheduleTransferDto.getFromAccountId(),
                scheduleTransferDto.getToAccountId(),
                scheduleTransferDto.getAmount(),
                scheduleTransferDto.getTransferDate(),
                scheduleTransferDto.getTransferId(),
                scheduleTransferDto.getTimestamp()
        );
    }


}
