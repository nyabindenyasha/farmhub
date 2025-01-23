package com.xplug.tech.personelinfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class BankingDetailResponse {
    private Long id;
    private String name;
    private String accountNumber;
    private String accountName;
    private String branch;
    private String currency;

    public static BankingDetailResponse of(BankingDetails bankingDetails) {
        if (Objects.isNull(bankingDetails)) {
            return null;
        }
        return BankingDetailResponse.builder()
                .id(bankingDetails.getId())
                .name(bankingDetails.getName())
                .accountName(bankingDetails.getAccountName())
                .accountNumber(bankingDetails.getAccountNumber())
                .branch(bankingDetails.getBranch())
                .currency(bankingDetails.getCurrency().getCode())
                .build();
    }

    public static List<BankingDetailResponse> of(Collection<BankingDetails> collection) {
        if (Objects.isNull(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(BankingDetailResponse::of).toList();
    }
}
