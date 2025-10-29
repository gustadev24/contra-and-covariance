package com.app.model;

import com.app.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    protected String id;
    protected String name;
    protected String email;
    protected String type;
    protected double registrationFee; // Tarifa de inscripción
    protected List<PaymentMethod> paymentMethods; // Métodos de pago usados
    protected boolean hasPaid; // Indicador de pago

    public Participant(String id, String name, String email, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.paymentMethods = new ArrayList<>();
        this.hasPaid = false;
        this.registrationFee = calculateRegistrationFee(type);
    }
    
    /**
     * Calcula la tarifa de inscripción según el tipo de participante
     */
    private double calculateRegistrationFee(String participantType) {
        switch (participantType.toLowerCase()) {
            case "estudiante":
                return 50.0;
            case "docente":
                return 80.0;
            case "administrativo":
                return 60.0;
            case "ponente nacional":
            case "ponente internacional":
            case "invitado":
            case "miembro comisión":
                return 0.0; // Gratis
            case "público":
                return 40.0;
            default:
                return 50.0;
        }
    }
    
    /**
     * Añade un método de pago
     */
    public void addPayment(PaymentMethod payment) {
        paymentMethods.add(payment);
        checkPaymentStatus();
    }
    
    /**
     * Obtiene el total pagado
     */
    public double getTotalPaid() {
        return paymentMethods.stream()
                .mapToDouble(PaymentMethod::getAmount)
                .sum();
    }
    
    /**
     * Verifica si el participante ha pagado completamente
     */
    private void checkPaymentStatus() {
        this.hasPaid = getTotalPaid() >= registrationFee;
    }
    
    /**
     * Verifica si ha pagado completamente
     */
    public boolean hasFullyPaid() {
        return hasPaid || registrationFee == 0.0;
    }
    
    /**
     * Obtiene los métodos de pago usados
     */
    public List<PaymentMethod> getPaymentMethods() {
        return new ArrayList<>(paymentMethods);
    }

    public void register() {
        System.out.println("[REGISTRO] " + type + " registrado: " + name);
    }

    public String getInfo() {
        return String.format("%s: %s (ID: %s, Email: %s)", type, name, id, email);
    }
    
    /**
     * Obtiene información de pago
     */
    public String getPaymentInfo() {
        return String.format("Tarifa: S/ %.2f | Pagado: S/ %.2f | Estado: %s",
                registrationFee, getTotalPaid(), 
                hasFullyPaid() ? "✅ COMPLETO" : "⚠️ PENDIENTE");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }
    
    public double getRegistrationFee() {
        return registrationFee;
    }
    
    public boolean hasPaid() {
        return hasPaid;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
