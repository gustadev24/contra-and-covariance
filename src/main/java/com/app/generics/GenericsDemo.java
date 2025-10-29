package com.app.generics;

import com.app.model.Participant;
import com.app.model.Presenter;
import com.app.participants.*;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericsDemo - Demostración práctica de Covarianza y Contravarianza
 * 
 * Esta clase contiene ejemplos ejecutables que muestran cómo y cuándo usar
 * los wildcards '? extends' (covarianza) y '? super' (contravarianza)
 * en el contexto del III Seminario Internacional de Ingeniería de Software.
 */
public class GenericsDemo {
    
    /**
     * Ejecuta todas las demostraciones de covarianza y contravarianza
     */
    public static void runAllDemos() {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║   DEMOSTRACIÓN: COVARIANZA Y CONTRAVARIANZA EN JAVA          ║");
        System.out.println("║   III Seminario Internacional de Ingeniería de Software      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        demoCovariance();
        System.out.println("\n" + "=".repeat(70) + "\n");
        demoContravariance();
        System.out.println("\n" + "=".repeat(70) + "\n");
        demoCombined();
        System.out.println("\n" + "=".repeat(70) + "\n");
        demoPECS();
    }
    
    /**
     * DEMO 1: COVARIANZA (? extends)
     * 
     * Escenario: Queremos procesar diferentes tipos de participantes
     * sin preocuparnos por el tipo exacto de la lista.
     */
    private static void demoCovariance() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMO 1: COVARIANZA (? extends) - LECTURA FLEXIBLE         │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        
        // Crear diferentes listas de participantes específicos
        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Ana García", "ana@uni.edu", "Universidad Nacional", "Ing. Software"));
        students.add(new Student("S002", "Carlos López", "carlos@uni.edu", "Universidad Central", "Ing. Sistemas"));
        
        List<NationalPresenter> nationalPresenters = new ArrayList<>();
        nationalPresenters.add(new NationalPresenter("P001", "Dr. María Torres", "maria@instituto.edu", 
            "Arquitecturas de Microservicios", "Instituto Tecnológico"));
        
        List<InternationalPresenter> internationalPresenters = new ArrayList<>();
        internationalPresenters.add(new InternationalPresenter("P002", "Prof. John Smith", "john@mit.edu",
            "AI in Software Engineering", "USA", "MIT"));
        
        // COVARIANZA: EventRegistry puede recibir cualquier List<? extends Participant>
        EventRegistry registry = new EventRegistry();
        
        System.out.println("\n🔹 Registrando estudiantes (List<Student>):");
        registry.registerAll(students); // ✅ List<Student> es válido
        
        System.out.println("\n🔹 Registrando ponentes nacionales (List<NationalPresenter>):");
        registry.registerAll(nationalPresenters); // ✅ List<NationalPresenter> es válido
        
        System.out.println("\n🔹 Registrando ponentes internacionales (List<InternationalPresenter>):");
        registry.registerAll(internationalPresenters); // ✅ List<InternationalPresenter> es válido
        
        // COVARIANZA: Podemos leer de listas de tipos específicos
        List<Presenter> allPresenters = new ArrayList<>();
        allPresenters.addAll(nationalPresenters);
        allPresenters.addAll(internationalPresenters);
        
        System.out.println("\n🔹 Procesando ponentes con covarianza:");
        registry.printPresenters(allPresenters); // ✅ List<Presenter> es válido
        registry.printPresenters(nationalPresenters); // ✅ List<NationalPresenter> también es válido
        
        // COVARIANZA: Comparar listas de diferentes tipos
        registry.compareLists(students, nationalPresenters);
        
        System.out.println("\n✅ CONCLUSIÓN COVARIANZA:");
        System.out.println("   - Podemos LEER de listas de tipos más específicos");
        System.out.println("   - '? extends Participant' acepta List<Student>, List<Teacher>, etc.");
        System.out.println("   - Útil para métodos que solo CONSUMEN/LEEN datos");
    }
    
    /**
     * DEMO 2: CONTRAVARIANZA (? super)
     * 
     * Escenario: Queremos agregar estudiantes a diferentes tipos de listas
     * (List<Student>, List<Participant>, List<Object>)
     */
    private static void demoContravariance() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMO 2: CONTRAVARIANZA (? super) - ESCRITURA FLEXIBLE     │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        
        // Crear estudiantes
        Student student1 = new Student("S003", "Laura Martínez", "laura@uni.edu", 
            "Universidad del Valle", "Ing. Software");
        Student student2 = new Student("S004", "Pedro Ramírez", "pedro@uni.edu",
            "Universidad de los Andes", "Ing. Informática");
        Student student3 = new Student("S005", "Sofia Chen", "sofia@uni.edu",
            "Universidad Javeriana", "Ing. Sistemas");
        
        // Diferentes tipos de listas que pueden contener Students
        List<Student> studentList = new ArrayList<>();
        List<Participant> participantList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        
        ParticipantManager manager = new ParticipantManager();
        
        // CONTRAVARIANZA: Podemos agregar Students a todas estas listas
        System.out.println("\n🔸 Agregando a List<Student>:");
        manager.addStudent(studentList, student1); // ✅ List<Student> es válido
        
        System.out.println("\n🔸 Agregando a List<Participant>:");
        manager.addStudent(participantList, student2); // ✅ List<Participant> es válido
        
        System.out.println("\n🔸 Agregando a List<Object>:");
        manager.addStudent(objectList, student3); // ✅ List<Object> es válido
        
        // CONTRAVARIANZA: Transferir múltiples estudiantes
        List<Student> newStudents = new ArrayList<>();
        newStudents.add(new Student("S006", "Miguel Ángel", "miguel@uni.edu", "UIS", "Software"));
        newStudents.add(new Student("S007", "Diana Torres", "diana@uni.edu", "UdeA", "Sistemas"));
        
        System.out.println("\n🔸 Transferencia masiva a List<Participant>:");
        manager.addStudents(participantList, newStudents);
        
        System.out.println("\n🔸 Transferencia masiva a List<Object>:");
        manager.addStudents(objectList, newStudents);
        
        System.out.println("\n✅ CONCLUSIÓN CONTRAVARIANZA:");
        System.out.println("   - Podemos ESCRIBIR en listas de tipos más generales");
        System.out.println("   - '? super Student' acepta List<Student>, List<Participant>, List<Object>");
        System.out.println("   - Útil para métodos que PRODUCEN/ESCRIBEN datos");
    }
    
    /**
     * DEMO 3: COMBINACIÓN DE COVARIANZA Y CONTRAVARIANZA
     * 
     * Escenario real: Filtrar participantes y agregarlos a diferentes colecciones
     */
    private static void demoCombined() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMO 3: COMBINACIÓN - COVARIANZA + CONTRAVARIANZA         │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        
        EventRegistry registry = new EventRegistry();
        ParticipantManager manager = new ParticipantManager();
        
        // Crear diferentes tipos de participantes
        List<Student> students = List.of(
            new Student("S008", "Andrea Ruiz", "andrea@uni.edu", "UN", "Software"),
            new Student("S009", "Roberto Gómez", "roberto@uni.edu", "UdeA", "Sistemas")
        );
        
        List<Teacher> teachers = List.of(
            new Teacher("T001", "Prof. Juan Pérez", "juan@uni.edu", "Universidad Nacional", "Arquitectura"),
            new Teacher("T002", "Ing. María González", "maria@uni.edu", "UIS", "Cloud Computing")
        );
        
        // COVARIANZA: Registrar desde listas específicas (LECTURA)
        System.out.println("\n🔄 Paso 1 - COVARIANZA: Leer y registrar estudiantes");
        registry.registerAll(students); // Leemos de List<Student>
        
        System.out.println("\n🔄 Paso 2 - COVARIANZA: Leer y registrar docentes");
        registry.registerAll(teachers); // Leemos de List<Teacher>
        
        // CONTRAVARIANZA: Transferir a colección general (ESCRITURA)
        System.out.println("\n🔄 Paso 3 - CONTRAVARIANZA: Transferir a List<Object>");
        List<Object> generalCollection = new ArrayList<>();
        List<Participant> allParticipants = registry.getAllParticipants();
        
        manager.transferParticipants(generalCollection, allParticipants);
        
        System.out.println("\n✅ CONCLUSIÓN COMBINADA:");
        System.out.println("   - COVARIANZA para LEER de colecciones específicas");
        System.out.println("   - CONTRAVARIANZA para ESCRIBIR en colecciones generales");
        System.out.println("   - Juntas permiten pipelines de transformación flexible");
    }
    
    /**
     * DEMO 4: Principio PECS (Producer Extends, Consumer Super)
     * 
     * Muestra el principio fundamental para decidir qué wildcard usar
     */
    private static void demoPECS() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMO 4: PRINCIPIO PECS (Producer Extends, Consumer Super) │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n📚 PRINCIPIO PECS - Guía de decisión:\n");
        
        System.out.println("1️⃣  PRODUCER EXTENDS (? extends T)");
        System.out.println("   ┌─────────────────────────────────────────┐");
        System.out.println("   │ Usa cuando: La colección PRODUCE datos │");
        System.out.println("   │            Solo necesitas LEER          │");
        System.out.println("   └─────────────────────────────────────────┘");
        System.out.println("   Ejemplo:");
        
        List<NationalPresenter> nationalPresenters = List.of(
            new NationalPresenter("P003", "Dr. Laura Sánchez", "laura@inst.edu",
                "DevOps Moderno", "Instituto Tech")
        );
        
        // La lista PRODUCE ponentes que leemos
        processPresentersFromList(nationalPresenters);
        
        System.out.println("\n2️⃣  CONSUMER SUPER (? super T)");
        System.out.println("   ┌─────────────────────────────────────────┐");
        System.out.println("   │ Usa cuando: La colección CONSUME datos │");
        System.out.println("   │            Necesitas ESCRIBIR           │");
        System.out.println("   └─────────────────────────────────────────┘");
        System.out.println("   Ejemplo:");
        
        List<Participant> destination = new ArrayList<>();
        Student student = new Student("S010", "Carmen Ortiz", "carmen@uni.edu", "EAFIT", "Software");
        
        // La lista CONSUME estudiantes que escribimos
        addStudentToCollection(destination, student);
        
        System.out.println("\n3️⃣  SIN WILDCARD (T)");
        System.out.println("   ┌─────────────────────────────────────────┐");
        System.out.println("   │ Usa cuando: Necesitas LEER Y ESCRIBIR  │");
        System.out.println("   │            Tipo exacto requerido        │");
        System.out.println("   └─────────────────────────────────────────┘");
        System.out.println("   Ejemplo:");
        
        List<Student> students = new ArrayList<>();
        students.add(new Student("S011", "Luis Herrera", "luis@uni.edu", "UniNorte", "Software"));
        Student first = students.get(0); // Lectura type-safe
        System.out.println("   Leído y escrito en List<Student> exacta: " + first.getName());
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  REGLA DE ORO:                                                 ║");
        System.out.println("║  • Si la colección PRODUCE → usa ? extends (covarianza)       ║");
        System.out.println("║  • Si la colección CONSUME → usa ? super (contravarianza)     ║");
        System.out.println("║  • Si hace AMBAS → NO uses wildcard                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Método auxiliar que PRODUCE/LEE ponentes (usa ? extends)
     */
    private static void processPresentersFromList(List<? extends Presenter> presenters) {
        System.out.println("   📖 Procesando " + presenters.size() + " ponente(s) de la lista (LECTURA)");
        for (Presenter p : presenters) {
            System.out.println("      - " + p.getClass().getSimpleName());
        }
    }
    
    /**
     * Método auxiliar que CONSUME/ESCRIBE estudiantes (usa ? super)
     */
    private static void addStudentToCollection(List<? super Student> collection, Student student) {
        collection.add(student);
        System.out.println("   ✍️  Estudiante agregado a colección (ESCRITURA): " + student.getName());
        System.out.println("      Tamaño de colección: " + collection.size());
    }
}
