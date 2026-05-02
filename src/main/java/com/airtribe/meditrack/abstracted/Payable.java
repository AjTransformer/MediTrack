package com.airtribe.meditrack.abstracted;

import com.airtribe.meditrack.entity.Bill;

public interface Payable {

    Bill generateBill();

    default double calculateTax(double amount, double taxRate) {
        return amount * taxRate;
    }

    default double calculateTotal(double amount, double taxRate) {
        return amount + calculateTax(amount, taxRate);
    }
}
