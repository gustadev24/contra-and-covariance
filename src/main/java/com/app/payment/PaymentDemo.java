package com.app.payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PaymentDemo - Demostraciones completas de Covarianza y Contravarianza con Pagos
 * 
 * Esta clase contiene ejemplos prácticos que demuestran el uso de:
 * - COVARIANZA (? extends) para lectura flexible
 * - CONTRAVARIANZA (? super) para escritura flexible
 * - Principio PECS aplicado a sistemas de pago
 */
public class PaymentDemo {
    
    /**
     * Ejecuta todas las demostraciones de pago
     */
    public static void runAllPaymentDemos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║         PARTE 3: SISTEMA DE PAGOS CON GENÉRICOS               ║");
        System.out.println("║                                                                ║");
        System.out.println("║     Covarianza y Contravarianza aplicados a Métodos de Pago   ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        demo1_CovarianzaConPagos();
        demo2_ContravarianzaConPagos();
        demo3_CombinandoAmbosConceptos();
        demo4_PrincipioPECSConPagos();
    }
    
    /**
     * DEMO 1: COVARIANZA - Procesamiento flexible de pagos
     */
    private static void demo1_CovarianzaConPagos() {
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("DEMO 1: COVARIANZA (? extends) - Procesamiento Flexible de Pagos");
        System.out.println("═".repeat(70));
        
        System.out.println("\n📚 CONCEPTO:");
        System.out.println("Covarianza permite LEER de colecciones de tipos más específicos.");
        System.out.println("Usamos '? extends PaymentMethod' para procesar CUALQUIER tipo de pago.\n");
        
        // Crear diferentes tipos de pagos
        List<YapePayment> yapePayments = Arrays.asList(
            new YapePayment(50.0, "Ana García", "987654321", "YAPE-001"),
            new YapePayment(80.0, "Carlos Mendoza", "976543210", "YAPE-002")
        );
        
        List<CardPayment> cardPayments = Arrays.asList(
            new CardPayment(100.0, "Laura Martínez", "VISA", "4242", "Laura M."),
            new CardPayment(80.0, "Diego Ramírez", "MasterCard", "5555", "Diego R.")
        );
        
        List<CashPayment> cashPayments = Arrays.asList(
            new CashPayment(50.0, "Sofía Chen", "Carmen Ortiz", "REC-001", "PEN"),
            new CashPayment(40.0, "Miguel Rojas", "Luis Herrera", "REC-002", "PEN")
        );
        
        PaymentProcessor processor = new PaymentProcessor();
        
        System.out.println("🔹 EJEMPLO: Procesando diferentes tipos de listas\n");
        
        // ✅ Covarianza permite pasar List<YapePayment>
        System.out.println("1️⃣ Procesando List<YapePayment>:");
        processor.processAllPayments(yapePayments);
        
        // ✅ Covarianza permite pasar List<CardPayment>
        System.out.println("\n2️⃣ Procesando List<CardPayment>:");
        processor.processAllPayments(cardPayments);
        
        // ✅ Covarianza permite pasar List<CashPayment>
        System.out.println("\n3️⃣ Procesando List<CashPayment>:");
        processor.processAllPayments(cashPayments);
        
        // Generar reporte
        List<PaymentMethod> allPayments = new ArrayList<>();
        allPayments.addAll(yapePayments);
        allPayments.addAll(cardPayments);
        allPayments.addAll(cashPayments);
        
        processor.generateReport(allPayments);
        
        // Procesar solo pagos digitales
        List<DigitalPayment> digitalPayments = new ArrayList<>();
        digitalPayments.addAll(yapePayments);
        digitalPayments.addAll(cardPayments);
        
        processor.processDigitalPayments(digitalPayments);
    }
    
    /**
     * DEMO 2: CONTRAVARIANZA - Registro flexible de pagos
     */
    private static void demo2_ContravarianzaConPagos() {
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("DEMO 2: CONTRAVARIANZA (? super) - Registro Flexible de Pagos");
        System.out.println("═".repeat(70));
        
        System.out.println("\n📚 CONCEPTO:");
        System.out.println("Contravarianza permite ESCRIBIR en colecciones de tipos más generales.");
        System.out.println("Usamos '? super YapePayment' para escribir en List<YapePayment>,");
        System.out.println("List<DigitalPayment>, List<PaymentMethod> o List<Object>.\n");
        
        PaymentRegistry registry = new PaymentRegistry();
        
        // Crear diferentes destinos
        List<YapePayment> yapeList = new ArrayList<>();
        List<DigitalPayment> digitalList = new ArrayList<>();
        List<PaymentMethod> paymentList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        
        // Crear pagos
        YapePayment yape1 = new YapePayment(50.0, "Ana García", "987654321", "YAPE-003");
        YapePayment yape2 = new YapePayment(80.0, "Carlos Mendoza", "976543210", "YAPE-004");
        CardPayment card1 = new CardPayment(100.0, "Laura Martínez", "VISA", "4242", "Laura M.");
        CashPayment cash1 = new CashPayment(40.0, "Diego Ramírez", "Carmen Ortiz", "REC-003", "PEN");
        
        System.out.println("🔸 EJEMPLO 1: Registrando Yape en diferentes tipos de listas\n");
        
        // ✅ Todas estas escrituras son válidas por contravarianza
        registry.registerYapePayment(yapeList, yape1);
        registry.registerYapePayment(digitalList, yape2);
        
        System.out.println("\n🔸 EJEMPLO 2: Registrando diferentes pagos\n");
        
        registry.registerCardPayment(digitalList, card1);
        registry.registerCashPayment(paymentList, cash1);
        
        System.out.println("\n🔸 EJEMPLO 3: Registro múltiple\n");
        
        List<PaymentMethod> paymentsToAdd = Arrays.asList(
            new YapePayment(50.0, "Sofía Chen", "965432109", "YAPE-005"),
            new CardPayment(80.0, "Miguel Rojas", "VISA", "1234", "Miguel R."),
            new CashPayment(60.0, "Patricia Vargas", "Luis Herrera", "REC-004", "PEN")
        );
        
        registry.registerPayments(paymentList, paymentsToAdd);
        
        System.out.println("\n📊 Resumen de destinos:");
        System.out.println("   • List<YapePayment>: " + yapeList.size() + " elementos");
        System.out.println("   • List<DigitalPayment>: " + digitalList.size() + " elementos");
        System.out.println("   • List<PaymentMethod>: " + paymentList.size() + " elementos");
        System.out.println("   • List<Object>: " + objectList.size() + " elementos");
    }
    
    /**
     * DEMO 3: Combinando Covarianza y Contravarianza
     */
    private static void demo3_CombinandoAmbosConceptos() {
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("DEMO 3: Combinando Covarianza y Contravarianza");
        System.out.println("═".repeat(70));
        
        System.out.println("\n📚 CONCEPTO:");
        System.out.println("Podemos combinar LECTURA (covarianza) y ESCRITURA (contravarianza)");
        System.out.println("en operaciones complejas como transferencias de datos.\n");
        
        PaymentRegistry registry = new PaymentRegistry();
        PaymentProcessor processor = new PaymentProcessor();
        
        // Fuente: Lista específica de pagos Yape
        List<YapePayment> yapeSource = Arrays.asList(
            new YapePayment(50.0, "Ana García", "987654321", "YAPE-006"),
            new YapePayment(80.0, "Carlos Mendoza", "976543210", "YAPE-007"),
            new YapePayment(50.0, "Laura Martínez", "965432109", "YAPE-008")
        );
        
        // Destino: Lista general de pagos
        List<PaymentMethod> paymentDestination = new ArrayList<>();
        
        System.out.println("🔄 EJEMPLO: Transferencia con lectura y escritura flexible\n");
        
        System.out.println("📥 Fuente: List<YapePayment> con " + yapeSource.size() + " pagos");
        System.out.println("📤 Destino: List<PaymentMethod> (vacía inicialmente)");
        
        // ✅ LECTURA con covarianza (? extends)
        // ✅ ESCRITURA con contravarianza (? super)
        registry.transferPayments(paymentDestination, yapeSource);
        
        System.out.println("\n✅ Después de la transferencia:");
        System.out.println("   • Destino ahora tiene: " + paymentDestination.size() + " pagos");
        
        // Procesar los pagos transferidos
        processor.generateReport(paymentDestination);
    }
    
    /**
     * DEMO 4: Principio PECS (Producer Extends, Consumer Super)
     */
    private static void demo4_PrincipioPECSConPagos() {
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("DEMO 4: Principio PECS con Pagos");
        System.out.println("═".repeat(70));
        
        System.out.println("\n📚 PRINCIPIO PECS:");
        System.out.println("┌───────────────────────────────────────────────────────────┐");
        System.out.println("│  PRODUCER EXTENDS (? extends T)                           │");
        System.out.println("│  • Usa cuando la colección PRODUCE/da datos (lectura)    │");
        System.out.println("│  • Ejemplo: PaymentProcessor lee de listas               │");
        System.out.println("│                                                           │");
        System.out.println("│  CONSUMER SUPER (? super T)                               │");
        System.out.println("│  • Usa cuando la colección CONSUME/recibe datos          │");
        System.out.println("│  • Ejemplo: PaymentRegistry escribe en listas            │");
        System.out.println("└───────────────────────────────────────────────────────────┘\n");
        
        // PRODUCER EXTENDS - La lista produce/da pagos
        List<CardPayment> cardProducer = Arrays.asList(
            new CardPayment(100.0, "Ana García", "VISA", "4242", "Ana G."),
            new CardPayment(80.0, "Carlos Mendoza", "MasterCard", "5555", "Carlos M.")
        );
        
        // CONSUMER SUPER - La lista consume/recibe pagos
        List<PaymentMethod> paymentConsumer = new ArrayList<>();
        
        System.out.println("🔹 PRODUCER (? extends CardPayment):");
        System.out.println("   • PaymentProcessor PRODUCE/lee de cardProducer");
        System.out.println("   • Puede leer pero NO escribir");
        
        PaymentProcessor processor = new PaymentProcessor();
        processor.processAllPayments(cardProducer); // PRODUCER EXTENDS
        
        System.out.println("\n🔸 CONSUMER (? super PaymentMethod):");
        System.out.println("   • PaymentRegistry CONSUME/escribe en paymentConsumer");
        System.out.println("   • Puede escribir pero solo leer como Object");
        
        PaymentRegistry registry = new PaymentRegistry();
        registry.registerPayments(paymentConsumer, new ArrayList<>(cardProducer)); // CONSUMER SUPER
        
        System.out.println("\n✅ RESUMEN:");
        System.out.println("   • Producer procesó: " + processor.getTotalProcessed() + " pagos");
        System.out.println("   • Consumer registró: " + registry.getTotalRegistered() + " pagos");
        System.out.println("   • Total en consumer: " + paymentConsumer.size() + " pagos");
        
        System.out.println("\n" + "═".repeat(70));
        System.out.println("                   FIN DE DEMOS DE PAGOS");
        System.out.println("═".repeat(70));
    }
}
