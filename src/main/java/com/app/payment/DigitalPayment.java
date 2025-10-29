package com.app.payment;

/**
 * DigitalPayment - Clase abstracta para pagos digitales
 * 
 * Representa pagos realizados de forma electrónica/digital
 * (Yape, Tarjeta de crédito/débito, transferencias, etc.)
 */
public abstract class DigitalPayment extends PaymentMethod {
    
    protected String accountReference;
    protected boolean requiresVerification;
    protected boolean isVerified;
    
    /**
     * Constructor
     */
    public DigitalPayment(double amount, String payerName, String accountReference) {
        super(amount, payerName);
        this.accountReference = accountReference;
        this.requiresVerification = true;
        this.isVerified = false;
    }
    
    /**
     * Verifica el pago digital (simulación)
     */
    public boolean verifyPayment() {
        // Simulación de verificación
        System.out.println("   🔍 Verificando pago digital...");
        try {
            Thread.sleep(500); // Simula delay de verificación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        this.isVerified = true;
        System.out.println("   ✅ Pago verificado correctamente");
        return true;
    }
    
    /**
     * Procesa el pago digital
     */
    @Override
    public boolean processPayment() {
        System.out.println("\n🔄 Procesando pago digital...");
        System.out.println("   Monto: S/ " + amount);
        System.out.println("   Pagador: " + payerName);
        System.out.println("   Referencia: " + accountReference);
        
        setStatus(PaymentStatus.PROCESSING);
        
        if (requiresVerification && !isVerified) {
            verifyPayment();
        }
        
        // Simulación de procesamiento
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        setStatus(PaymentStatus.COMPLETED);
        System.out.println("   ✅ Pago completado exitosamente");
        return true;
    }
    
    // Getters
    
    public String getAccountReference() {
        return accountReference;
    }
    
    public boolean isVerified() {
        return isVerified;
    }
}
