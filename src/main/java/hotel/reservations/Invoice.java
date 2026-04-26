package hotel.reservations;

import hotel.model.PaymentMethod;
import hotel.interfaces.Payable;
import hotel.exceptions.InvalidDataException;
import hotel.exceptions.INVALIDPAYMENTEXCEPTION;
import java.time.LocalDate;

public class Invoice implements Payable {
    private int invoiceId;
    private Reservation reservation;
    private double amount;
    private PaymentMethod paymentMethod;
    private boolean paid;
    private LocalDate paymentDate; 

    public Invoice(int invoiceId, Reservation reservation, double amount, PaymentMethod paymentMethod) {
        if (reservation == null)
            throw new InvalidDataException("Reservation cannot be null.");
        if (paymentMethod == null)
            throw new InvalidDataException("Payment method cannot be null.");
        if (amount <= 0)
            throw new INVALIDPAYMENTEXCEPTION("Amount must be greater than zero.");

        this.invoiceId = invoiceId;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paid = false;
        this.paymentDate = null;
    }

 
    public void processPayment() {
        if (!paid) {
            paid = true;
            paymentDate = LocalDate.now();
            System.out.println("Invoice #" + invoiceId + " paid $" + amount +
                               " using " + paymentMethod + " on " + paymentDate);
        } else {
            System.out.println("Invoice #" + invoiceId + " is already paid.");
        }
    }


    public int getInvoiceId() {
        return invoiceId;
    }

    public int getReservationId() {
        return reservation.getReservationId();
    }

    public Reservation getReservation() {
        return reservation;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paid ? "PAID" : "UNPAID";
    }

    public void setAmount(double amount) {
        if (amount <= 0)
            throw new INVALIDPAYMENTEXCEPTION("Amount must be greater than zero.");
        this.amount = amount;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null)
            throw new InvalidDataException("Payment method cannot be null.");
        this.paymentMethod = paymentMethod;
    }

    public void resetPayment() {
        paid = false;
        paymentDate = null;
    }


    public String toString() {
        return "Invoice #" + invoiceId +
               " | Amount: $" + amount +
               " | Method: " + paymentMethod +
               " | Status: " + getPaymentStatus() +
               (paymentDate != null ? " | Paid on: " + paymentDate : "");
    }
}
