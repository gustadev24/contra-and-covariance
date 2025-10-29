package com.app.payment;

/**
 * YapePayment - Implementación para pagos mediante Yape
 * 
 * Yape es una aplicación de pagos móviles popular en Perú.
 * Esta clase extiende DigitalPayment ya que es un pago electrónico.
 */
public class YapePayment extends DigitalPayment {
    
    private String phoneNumber;
    private String yapeCode;
    
    /**
     * Constructor
     */
    public YapePayment(double amount, String payerName, String phoneNumber, String yapeCode) {
        super(amount, payerName, phoneNumber);
        this.phoneNumber = phoneNumber;
        this.yapeCode = yapeCode;
    }
    
    @Override
    public String getPaymentType() {
        return "Yape";
    }
    
    @Override
    public String getPaymentDetails() {
        return String.format("Yape | Teléfono: %s | Código: %s", 
                phoneNumber, yapeCode);
    }
    
    @Override
    public boolean processPayment() {
        System.out.println("\n💰 PROCESANDO PAGO VÍA YAPE");
        System.out.println("   📱 Teléfono: " + phoneNumber);
        System.out.println("   🔢 Código Yape: " + yapeCode);
        return super.processPayment();
    }
    
    // Getters específicos
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getYapeCode() {
        return yapeCode;
    }
}
