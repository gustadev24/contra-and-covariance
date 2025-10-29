package com.app.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * PaymentMethod - Clase abstracta base para todos los métodos de pago
 * 
 * Esta clase representa la raíz de la jerarquía de pagos y define
 * el contrato que deben cumplir todos los métodos de pago específicos.
 */
public abstract class PaymentMethod {
    
    protected double amount;
    protected String payerName;
    protected LocalDateTime paymentDate;
    protected String transactionId;
    protected PaymentStatus status;
    
    /**
     * Estado del pago
     */
    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REFUNDED
    }
    
    /**
     * Constructor
     */
    public PaymentMethod(double amount, String payerName) {
        this.amount = amount;
        this.payerName = payerName;
        this.paymentDate = LocalDateTime.now();
        this.transactionId = generateTransactionId();
        this.status = PaymentStatus.PENDING;
    }
    
    /**
     * Procesa el pago (método abstracto - cada tipo lo implementa)
     */
    public abstract boolean processPayment();
    
    /**
     * Obtiene detalles específicos del pago (método abstracto)
     */
    public abstract String getPaymentDetails();
    
    /**
     * Obtiene el tipo de método de pago
     */
    public abstract String getPaymentType();
    
    /**
     * Genera un ID único para la transacción
     */
    private String generateTransactionId() {
        long timestamp = System.currentTimeMillis();
        return "TXN-" + timestamp + "-" + (int)(Math.random() * 1000);
    }
    
    /**
     * Obtiene información general del pago
     */
    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Pago: S/ %.2f | Pagador: %s | Fecha: %s | ID: %s | Estado: %s",
                amount, payerName, paymentDate.format(formatter), transactionId, status);
    }
    
    // Getters y Setters
    
    public double getAmount() {
        return amount;
    }
    
    public String getPayerName() {
        return payerName;
    }
    
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public PaymentStatus getStatus() {
        return status;
    }
    
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return getInfo() + " | " + getPaymentDetails();
    }
}
