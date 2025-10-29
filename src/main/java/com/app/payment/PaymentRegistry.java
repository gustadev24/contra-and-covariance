package com.app.payment;

import java.util.List;

/**
 * PaymentRegistry - Demostración de CONTRAVARIANZA con Pagos
 * 
 * Esta clase demuestra CONTRAVARIANZA (? super) permitiendo ESCRIBIR
 * pagos específicos en colecciones más generales.
 * 
 * CONTRAVARIANZA = "Consumer Super" = La colección CONSUME datos que escribimos
 */
public class PaymentRegistry {
    
    private int totalRegistered;
    
    public PaymentRegistry() {
        this.totalRegistered = 0;
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 1: Registrar pago Yape
     * 
     * '? super YapePayment' acepta:
     * - List<YapePayment>
     * - List<DigitalPayment>
     * - List<PaymentMethod>
     * - List<Object>
     * 
     * Podemos ESCRIBIR YapePayment pero solo LEER como Object
     */
    public void registerYapePayment(List<? super YapePayment> list, YapePayment payment) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  CONTRAVARIANZA - Registro de Yape (? super YapePayment)  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔸 Tipo de parámetro: List<? super YapePayment>");
        System.out.println("🔸 Puede escribir en List<YapePayment>, List<DigitalPayment>,");
        System.out.println("   List<PaymentMethod> o List<Object>");
        
        // ✅ ESCRITURA permitida: podemos agregar YapePayment
        list.add(payment);
        totalRegistered++;
        
        System.out.println("\n✅ Pago Yape registrado:");
        System.out.println("   • Monto: S/ " + payment.getAmount());
        System.out.println("   • Teléfono: " + payment.getPhoneNumber());
        System.out.println("   • Código: " + payment.getYapeCode());
        
        // ⚠️ LECTURA limitada: solo podemos leer como Object
        Object obj = list.get(list.size() - 1);
        System.out.println("   • Tipo al leer: " + obj.getClass().getSimpleName());
        
        // ❌ NO podemos leer como YapePayment directamente:
        // YapePayment retrieved = list.get(0); // Error de compilación
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 2: Registrar pago con Tarjeta
     * 
     * Similar al anterior pero para CardPayment
     */
    public void registerCardPayment(List<? super CardPayment> list, CardPayment payment) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  CONTRAVARIANZA - Registro de Tarjeta (? super Card)      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔸 Registrando pago con tarjeta...");
        
        // ✅ ESCRITURA permitida
        list.add(payment);
        totalRegistered++;
        
        System.out.println("✅ Pago con tarjeta registrado:");
        System.out.println("   • Monto: S/ " + payment.getAmount());
        System.out.println("   • Tipo: " + payment.getCardType());
        System.out.println("   • Terminación: ****" + payment.getLastFourDigits());
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 3: Registrar efectivo
     */
    public void registerCashPayment(List<? super CashPayment> list, CashPayment payment) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  CONTRAVARIANZA - Registro de Efectivo (? super Cash)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔸 Registrando pago en efectivo...");
        
        // ✅ ESCRITURA permitida
        list.add(payment);
        totalRegistered++;
        
        System.out.println("✅ Pago en efectivo registrado:");
        System.out.println("   • Monto: S/ " + payment.getAmount());
        System.out.println("   • Recibo: " + payment.getReceiptNumber());
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 4: Registrar múltiples pagos
     * 
     * Permite agregar una lista de pagos a una colección más general
     */
    public void registerPayments(List<? super PaymentMethod> destination, 
                                List<PaymentMethod> paymentsToAdd) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  CONTRAVARIANZA - Registro Múltiple (? super Payment)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔸 Registrando " + paymentsToAdd.size() + " pagos...");
        
        int count = 0;
        for (PaymentMethod payment : paymentsToAdd) {
            // ✅ ESCRITURA permitida
            destination.add(payment);
            count++;
            totalRegistered++;
        }
        
        System.out.println("✅ " + count + " pagos agregados a la colección destino");
        System.out.println("   • Total registrado en sesión: " + totalRegistered);
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 5: Transferir pagos entre colecciones
     * 
     * Combina covarianza (lectura) y contravarianza (escritura)
     */
    public void transferPayments(List<? super PaymentMethod> destination,
                                List<? extends PaymentMethod> source) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  COVARIANZA + CONTRAVARIANZA - Transferencia              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔹 COVARIANZA: Leyendo de origen (? extends)");
        System.out.println("🔸 CONTRAVARIANZA: Escribiendo a destino (? super)");
        System.out.println("\n🔄 Transfiriendo " + source.size() + " pagos...");
        
        int transferred = 0;
        double totalAmount = 0.0;
        
        for (PaymentMethod payment : source) {
            // ✅ LECTURA de source (covarianza)
            // ✅ ESCRITURA a destination (contravarianza)
            destination.add(payment);
            transferred++;
            totalAmount += payment.getAmount();
        }
        
        System.out.println("\n✅ Transferencia completada:");
        System.out.println("   • Pagos transferidos: " + transferred);
        System.out.println("   • Monto total: S/ " + String.format("%.2f", totalAmount));
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 6: Agregar pago a múltiples destinos
     * 
     * Demuestra que un pago puede agregarse a diferentes tipos de colecciones
     */
    public void registerToMultipleDestinations(YapePayment payment,
                                              List<? super YapePayment> dest1,
                                              List<? super DigitalPayment> dest2,
                                              List<? super PaymentMethod> dest3) {
        System.out.println("\n🔸 CONTRAVARIANZA - Registrando en múltiples destinos:");
        
        // ✅ Todas estas escrituras son válidas
        dest1.add(payment); // List<? super YapePayment>
        dest2.add(payment); // List<? super DigitalPayment>
        dest3.add(payment); // List<? super PaymentMethod>
        
        totalRegistered += 3;
        
        System.out.println("   ✅ Pago agregado a 3 colecciones diferentes");
        System.out.println("   • List<? super YapePayment>");
        System.out.println("   • List<? super DigitalPayment>");
        System.out.println("   • List<? super PaymentMethod>");
    }
    
    // Getter
    
    public int getTotalRegistered() {
        return totalRegistered;
    }
}
