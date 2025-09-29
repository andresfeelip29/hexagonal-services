package com.co.technicaltest.domain.model;

import com.co.technicaltest.infrastructure.shared.enums.BankAccountType;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

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
}
