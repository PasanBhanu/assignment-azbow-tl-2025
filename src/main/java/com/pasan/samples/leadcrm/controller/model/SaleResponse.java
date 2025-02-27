package com.pasan.samples.leadcrm.controller.model;

import com.pasan.samples.leadcrm.repository.model.Sale;

import java.math.BigDecimal;
import java.util.Date;

public record SaleResponse(
        Date saleDate,
        BigDecimal finalSalePrice,
        String commissionDetails
) {
    public static SaleResponse of(Sale sale) {
        return new SaleResponse(
                sale.getSaleDate(),
                sale.getFinalSalePrice(),
                sale.getCommissionDetails()
        );
    }
}
