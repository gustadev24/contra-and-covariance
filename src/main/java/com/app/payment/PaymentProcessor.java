package com.app.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PaymentProcessor - Demostración de COVARIANZA con Pagos
 * 
 * Esta clase demuestra COVARIANZA (? extends) permitiendo LEER/PROCESAR
 * diferentes tipos de pagos de manera flexible.
 * 
 * COVARIANZA = "Producer Extends" = La colección PRODUCE datos que leemos
 */
public class PaymentProcessor {
    
    private int totalProcessed;
    private double totalAmount;
    
    public PaymentProcessor() {
        this.totalProcessed = 0;
        this.totalAmount = 0.0;
    }
    
    /**
     * COVARIANZA - Ejemplo 1: Procesar cualquier tipo de pago
     * 
     * '? extends PaymentMethod' acepta:
     * - List<YapePayment>
     * - List<CardPayment>
     * - List<CashPayment>
     * - List<DigitalPayment>
     * - List<PaymentMethod>
     * 
     * Podemos LEER pero NO ESCRIBIR en la lista
     */
    public void processAllPayments(List<? extends PaymentMethod> payments) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  COVARIANZA - Procesando Pagos (? extends PaymentMethod)  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔹 Tipo de parámetro: List<? extends PaymentMethod>");
        System.out.println("🔹 Total de pagos a procesar: " + payments.size());
        
        for (PaymentMethod payment : payments) {
            // ✅ LECTURA permitida: sabemos que es un PaymentMethod
            payment.processPayment();
            totalProcessed++;
            totalAmount += payment.getAmount();
        }
        
        // ❌ ESCRITURA NO permitida:
        // payments.add(new YapePayment(...)); // Error de compilación
        // payments.add(new CardPayment(...)); // Error de compilación
        
        System.out.println("\n✅ Procesamiento completado:");
        System.out.println("   • Pagos procesados en este lote: " + payments.size());
        System.out.println("   • Total acumulado: " + totalProcessed + " pagos");
        System.out.println("   • Monto total: S/ " + String.format("%.2f", totalAmount));
    }
    
    /**
     * COVARIANZA - Ejemplo 2: Procesar solo pagos digitales
     * 
     * '? extends DigitalPayment' acepta:
     * - List<YapePayment>
     * - List<CardPayment>
     * - List<DigitalPayment>
     * 
     * Más específico que el método anterior
     */
    public void processDigitalPayments(List<? extends DigitalPayment> digitalPayments) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  COVARIANZA - Procesando Pagos Digitales (? extends)      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔹 Solo acepta pagos digitales (Yape, Tarjeta)");
        System.out.println("🔹 Total: " + digitalPayments.size());
        
        int verifiedCount = 0;
        for (DigitalPayment payment : digitalPayments) {
            // ✅ Podemos acceder a métodos específicos de DigitalPayment
            if (payment.isVerified()) {
                verifiedCount++;
            }
            System.out.println("   • " + payment.getPaymentType() + ": S/ " + payment.getAmount());
        }
        
        System.out.println("\n✅ Pagos verificados: " + verifiedCount + "/" + digitalPayments.size());
    }
    
    /**
     * COVARIANZA - Ejemplo 3: Generar reporte
     * 
     * Podemos generar reportes de cualquier tipo de colección de pagos
     */
    public void generateReport(List<? extends PaymentMethod> payments) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              REPORTE DE PAGOS (Covarianza)                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Map<String, Integer> countByType = new HashMap<>();
        Map<String, Double> amountByType = new HashMap<>();
        
        // ✅ LECTURA: Procesamos todos los pagos
        for (PaymentMethod payment : payments) {
            String type = payment.getPaymentType();
            countByType.put(type, countByType.getOrDefault(type, 0) + 1);
            amountByType.put(type, amountByType.getOrDefault(type, 0.0) + payment.getAmount());
        }
        
        System.out.println("📊 Resumen por Método de Pago:");
        for (String type : countByType.keySet()) {
            System.out.printf("   • %s: %d pagos - S/ %.2f%n", 
                    type, countByType.get(type), amountByType.get(type));
        }
        
        double total = amountByType.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("\n💰 Total General: S/ %.2f%n", total);
    }
    
    /**
     * COVARIANZA - Ejemplo 4: Comparar colecciones de pagos
     */
    public void comparePaymentLists(List<? extends PaymentMethod> list1, 
                                   List<? extends PaymentMethod> list2) {
        System.out.println("\n🔹 COVARIANZA - Comparando dos listas de pagos:");
        
        double total1 = list1.stream().mapToDouble(PaymentMethod::getAmount).sum();
        double total2 = list2.stream().mapToDouble(PaymentMethod::getAmount).sum();
        
        System.out.printf("   • Lista 1: %d pagos - S/ %.2f%n", list1.size(), total1);
        System.out.printf("   • Lista 2: %d pagos - S/ %.2f%n", list2.size(), total2);
        System.out.printf("   • Diferencia: S/ %.2f%n", Math.abs(total1 - total2));
    }
    
    // Getters
    
    public int getTotalProcessed() {
        return totalProcessed;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
}
