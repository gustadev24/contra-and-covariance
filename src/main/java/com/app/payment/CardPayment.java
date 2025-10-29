package com.app.payment;

/**
 * CardPayment - Implementación para pagos con tarjeta de crédito/débito
 * 
 * Esta clase maneja pagos realizados con tarjetas VISA, MasterCard, etc.
 * Extiende DigitalPayment ya que es un pago electrónico.
 */
public class CardPayment extends DigitalPayment {
    
    private String cardType; // VISA, MasterCard, American Express, etc.
    private String lastFourDigits;
    private String cardholderName;
    
    /**
     * Constructor
     */
    public CardPayment(double amount, String payerName, String cardType, 
                       String lastFourDigits, String cardholderName) {
        super(amount, payerName, cardType + "-" + lastFourDigits);
        this.cardType = cardType;
        this.lastFourDigits = lastFourDigits;
        this.cardholderName = cardholderName;
    }
    
    @Override
    public String getPaymentType() {
        return "Tarjeta";
    }
    
    @Override
    public String getPaymentDetails() {
        return String.format("Tarjeta %s | Terminación: ****%s | Titular: %s", 
                cardType, lastFourDigits, cardholderName);
    }
    
    @Override
    public boolean processPayment() {
        System.out.println("\n💳 PROCESANDO PAGO CON TARJETA");
        System.out.println("   🏦 Tipo: " + cardType);
        System.out.println("   🔢 Terminación: ****" + lastFourDigits);
        System.out.println("   👤 Titular: " + cardholderName);
        return super.processPayment();
    }
    
    /**
     * Método específico para validar CVV (simulado)
     */
    public boolean validateCVV(String cvv) {
        // Simulación de validación
        return cvv != null && cvv.length() >= 3;
    }
    
    // Getters específicos
    
    public String getCardType() {
        return cardType;
    }
    
    public String getLastFourDigits() {
        return lastFourDigits;
    }
    
    public String getCardholderName() {
        return cardholderName;
    }
}
