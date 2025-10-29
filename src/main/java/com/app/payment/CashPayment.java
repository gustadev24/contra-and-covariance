package com.app.payment;

/**
 * CashPayment - Implementación para pagos en efectivo
 * 
 * Esta clase maneja pagos realizados en efectivo físico.
 * Extiende directamente de PaymentMethod ya que NO es un pago digital.
 */
public class CashPayment extends PaymentMethod {
    
    private String receivedBy; // Persona que recibió el efectivo
    private String receiptNumber; // Número de recibo
    private String currency; // PEN, USD, etc.
    
    /**
     * Constructor
     */
    public CashPayment(double amount, String payerName, String receivedBy, 
                       String receiptNumber, String currency) {
        super(amount, payerName);
        this.receivedBy = receivedBy;
        this.receiptNumber = receiptNumber;
        this.currency = currency;
    }
    
    @Override
    public String getPaymentType() {
        return "Efectivo";
    }
    
    @Override
    public String getPaymentDetails() {
        return String.format("Efectivo (%s) | Recibido por: %s | Recibo: %s", 
                currency, receivedBy, receiptNumber);
    }
    
    @Override
    public boolean processPayment() {
        System.out.println("\n💵 PROCESANDO PAGO EN EFECTIVO");
        System.out.println("   💰 Monto: " + amount + " " + currency);
        System.out.println("   👤 Recibido por: " + receivedBy);
        System.out.println("   📄 Recibo: " + receiptNumber);
        
        setStatus(PaymentStatus.PROCESSING);
        
        // Simulación de registro
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        setStatus(PaymentStatus.COMPLETED);
        System.out.println("   ✅ Pago en efectivo registrado exitosamente");
        return true;
    }
    
    /**
     * Genera recibo físico (simulado)
     */
    public void printReceipt() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("      RECIBO DE PAGO EN EFECTIVO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Recibo #: " + receiptNumber);
        System.out.println("Pagador: " + payerName);
        System.out.println("Monto: " + amount + " " + currency);
        System.out.println("Recibido por: " + receivedBy);
        System.out.println("Fecha: " + paymentDate);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }
    
    // Getters específicos
    
    public String getReceivedBy() {
        return receivedBy;
    }
    
    public String getReceiptNumber() {
        return receiptNumber;
    }
    
    public String getCurrency() {
        return currency;
    }
}
