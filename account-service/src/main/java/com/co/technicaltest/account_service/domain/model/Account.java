package com.co.technicaltest.account_service.domain.model;

import com.co.technicaltest.account_service.domain.enums.BankAccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Object domain for account.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
@Builder
public class Account {
    private Long accountId;
    private String accountNumber;
    private BankAccountType accountType;
    private BigDecimal balance;
    private Boolean status;

    public String generateBankAccountNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return String.valueOf(random
                .nextLong(10_000_000_000L, 100_000_000_000L));
    }


}
