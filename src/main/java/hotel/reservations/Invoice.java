/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.reservations;

import hotel.model.PaymentMethod;
import hotel.interfaces.Payable;

public class Invoice implements Payable {
    private int invoiceId;
    private Reservation reservation;
    private double amount;
    private PaymentMethod paymentMethod;
    private boolean paid;


   public Invoice(int invoiceId, Reservation reservation, double amount, PaymentMethod paymentMethod) {

    if (reservation == null || paymentMethod == null) {
        throw new InvalidDataException("Reservation or payment method is invalid");
    }

    if (amount <= 0) {
        throw new INVALIDPAYMENTEXCEPTION("Amount must be greater than zero");
    }

    this.invoiceId = invoiceId;
    this.reservation = reservation;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.paid = false;
}

    public void processPayment() {
        if (!paid) {
            paid = true;
            System.out.println("Invoice " + invoiceId + " paid using " + paymentMethod);
        }
        else {
            System.out.println("Invoice " + invoiceId + " is already paid.");
        }
    }

    // Getters
    public int getInvoiceId() {
        return invoiceId;
    }
 public int getReservationId() {
    return reservation.getReservationId();
}
    
    public double getAmount() {
        return amount;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public boolean isPaid() {
        return paid;
    }

public void setAmount(double amount) {
    if (amount <= 0) {
        throw new INVALIDPAYMENTEXCEPTION("Amount must be greater than zero");
    }
    this.amount = amount;
}
 public void setPaymentMethod(PaymentMethod paymentMethod) {
    if (paymentMethod == null) {
        throw new InvalidDataException("Payment method cannot be null");
    }
    this.paymentMethod = paymentMethod;
}
    public void resetPayment() {
    paid = false;
}
    
    public String getPaymentStatus() {
    if (paid) {
        return "PAID";
    } else {
        return "UNPAID";
    }
}
    
}

