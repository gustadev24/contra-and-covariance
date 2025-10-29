package com.app;

import com.app.generics.EventRegistry;
import com.app.generics.GenericsDemo;
import com.app.generics.ParticipantManager;
import com.app.model.Participant;
import com.app.model.Presenter;
import com.app.organizers.CommissionMember;
import com.app.organizers.OrganizerCommission;
import com.app.participants.*;

import java.util.ArrayList;
import java.util.List;

/**
 * App - Sistema de Gestión de Participantes
 * III Seminario Internacional de Ingeniería de Software
 * 
 * Esta aplicación demuestra el uso de COVARIANZA y CONTRAVARIANZA
 * en Java mediante la gestión de diferentes tipos de participantes
 * en un evento académico internacional.
 */
public class App {
    
    public static void main(String[] args) {
        printHeader();
        
        // Ejecutar demos interactivas de covarianza y contravarianza
        System.out.println("\n🎯 PARTE 1: DEMOSTRACIONES INTERACTIVAS\n");
        GenericsDemo.runAllDemos();
        
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("\n🎯 PARTE 2: SISTEMA INTEGRADO DE PARTICIPANTES\n");
        
        // Demostrar el sistema completo
        runIntegratedSystem();
        
        printFooter();
    }
    
    /**
     * Ejecuta el sistema integrado completo con todos los tipos de participantes
     */
    private static void runIntegratedSystem() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│  SISTEMA DE REGISTRO Y GESTIÓN DE PARTICIPANTES           │");
        System.out.println("└────────────────────────────────────────────────────────────┘\n");
        
        // 1. Crear instancias de todos los tipos de participantes
        System.out.println("📝 PASO 1: Creando participantes del seminario...\n");
        
        // Estudiantes
        List<Student> students = createStudents();
        System.out.println("✅ Creados " + students.size() + " estudiantes");
        
        // Docentes
        List<Teacher> teachers = createTeachers();
        System.out.println("✅ Creados " + teachers.size() + " docentes");
        
        // Administrativos
        List<Administrative> administratives = createAdministratives();
        System.out.println("✅ Creados " + administratives.size() + " administrativos");
        
        // Ponentes Nacionales
        List<NationalPresenter> nationalPresenters = createNationalPresenters();
        System.out.println("✅ Creados " + nationalPresenters.size() + " ponentes nacionales");
        
        // Ponentes Internacionales
        List<InternationalPresenter> internationalPresenters = createInternationalPresenters();
        System.out.println("✅ Creados " + internationalPresenters.size() + " ponentes internacionales");
        
        // Invitados
        List<Guest> guests = createGuests();
        System.out.println("✅ Creados " + guests.size() + " invitados");
        
        // Comisión Organizadora
        OrganizerCommission commission = createCommission();
        System.out.println("✅ Creada comisión organizadora con " + commission.getMembers().size() + " miembros");
        
        // 2. Demostrar COVARIANZA con EventRegistry
        System.out.println("\n" + "─".repeat(70));
        System.out.println("📊 PASO 2: COVARIANZA - Registro flexible de participantes\n");
        demonstrateCovariance(students, teachers, administratives, 
                             nationalPresenters, internationalPresenters, guests);
        
        // 3. Demostrar CONTRAVARIANZA con ParticipantManager
        System.out.println("\n" + "─".repeat(70));
        System.out.println("🔄 PASO 3: CONTRAVARIANZA - Gestión flexible de colecciones\n");
        demonstrateContravariance(students, teachers);
        
        // 4. Mostrar estadísticas finales
        System.out.println("\n" + "─".repeat(70));
        System.out.println("📈 PASO 4: Estadísticas del Seminario\n");
        displayStatistics(students, teachers, administratives, 
                         nationalPresenters, internationalPresenters, 
                         guests, commission);
        
        // 5. Demostrar presentaciones
        System.out.println("\n" + "─".repeat(70));
        System.out.println("🎤 PASO 5: Sesión de Presentaciones\n");
        demonstratePresentations(nationalPresenters, internationalPresenters);
    }
    
    /**
     * Crea lista de estudiantes
     */
    private static List<Student> createStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Ana García Ruiz", "ana.garcia@unacional.edu.co",
            "Universidad Nacional de Colombia", "Ingeniería de Software"));
        students.add(new Student("S002", "Carlos Mendoza López", "carlos.mendoza@unal.edu.co",
            "Universidad de los Andes", "Ingeniería de Sistemas"));
        students.add(new Student("S003", "Laura Martínez", "laura.martinez@javeriana.edu.co",
            "Pontificia Universidad Javeriana", "Ingeniería de Software"));
        students.add(new Student("S004", "Diego Ramírez", "diego.ramirez@uis.edu.co",
            "Universidad Industrial de Santander", "Ingeniería de Sistemas"));
        students.add(new Student("S005", "Sofía Chen Wang", "sofia.chen@eafit.edu.co",
            "Universidad EAFIT", "Ingeniería de Software"));
        return students;
    }
    
    /**
     * Crea lista de docentes
     */
    private static List<Teacher> createTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("T001", "Prof. Juan Pérez Torres", "juan.perez@unacional.edu.co",
            "Universidad Nacional", "Arquitectura de Software"));
        teachers.add(new Teacher("T002", "Dra. María González", "maria.gonzalez@uis.edu.co",
            "Universidad Industrial de Santander", "Computación en la Nube"));
        teachers.add(new Teacher("T003", "Ing. Roberto Silva", "roberto.silva@unal.edu.co",
            "Universidad de los Andes", "Ingeniería de Datos"));
        return teachers;
    }
    
    /**
     * Crea lista de administrativos
     */
    private static List<Administrative> createAdministratives() {
        List<Administrative> admins = new ArrayList<>();
        admins.add(new Administrative("A001", "Carmen Ortiz", "carmen.ortiz@unacional.edu.co",
            "Eventos Académicos", "Coordinadora"));
        admins.add(new Administrative("A002", "Luis Herrera", "luis.herrera@unacional.edu.co",
            "Logística", "Asistente"));
        return admins;
    }
    
    /**
     * Crea lista de ponentes nacionales
     */
    private static List<NationalPresenter> createNationalPresenters() {
        List<NationalPresenter> presenters = new ArrayList<>();
        presenters.add(new NationalPresenter("PN001", "Dr. Andrés Solano", 
            "andres.solano@itc.edu.co",
            "Microservicios y Arquitecturas Cloud-Native", 
            "Instituto Tecnológico de Colombia"));
        presenters.add(new NationalPresenter("PN002", "Dra. Patricia Vargas",
            "patricia.vargas@univalle.edu.co",
            "DevOps y CI/CD en Proyectos Enterprise",
            "Universidad del Valle"));
        presenters.add(new NationalPresenter("PN003", "Dr. Fernando Castro",
            "fernando.castro@uniandes.edu.co",
            "Machine Learning en Ingeniería de Software",
            "Universidad de los Andes"));
        return presenters;
    }
    
    /**
     * Crea lista de ponentes internacionales
     */
    private static List<InternationalPresenter> createInternationalPresenters() {
        List<InternationalPresenter> presenters = new ArrayList<>();
        presenters.add(new InternationalPresenter("PI001", "Prof. John Smith",
            "john.smith@mit.edu",
            "AI-Driven Software Development",
            "Estados Unidos", "MIT"));
        presenters.add(new InternationalPresenter("PI002", "Dr. María Fernández",
            "maria.fernandez@upm.es",
            "Blockchain en Sistemas Distribuidos",
            "España", "Universidad Politécnica de Madrid"));
        presenters.add(new InternationalPresenter("PI003", "Prof. Wei Chen",
            "wei.chen@tsinghua.edu.cn",
            "Quantum Computing Applications",
            "China", "Tsinghua University"));
        return presenters;
    }
    
    /**
     * Crea lista de invitados
     */
    private static List<Guest> createGuests() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("G001", "Camila Restrepo", 
            "camila.restrepo@techcorp.com", "TechCorp Colombia"));
        guests.add(new Guest("G002", "Miguel Ángel Rojas",
            "miguel.rojas@innovasoft.co", "InnovaSoft"));
        return guests;
    }
    
    /**
     * Crea la comisión organizadora
     */
    private static OrganizerCommission createCommission() {
        OrganizerCommission commission = new OrganizerCommission("Comisión Organizadora - III Seminario");
        
        commission.addMember(new CommissionMember("CM001", "Dr. Ricardo Moreno",
            "ricardo.moreno@unacional.edu.co",
            "Universidad Nacional", "Ingeniería de Software",
            "Presidente"));
        
        commission.addMember(new CommissionMember("CM002", "Dra. Elena Vargas",
            "elena.vargas@unacional.edu.co",
            "Universidad Nacional", "Computación Distribuida",
            "Vicepresidente"));
        
        commission.addMember(new CommissionMember("CM003", "Dr. Jorge López",
            "jorge.lopez@unacional.edu.co",
            "Universidad Nacional", "Arquitectura de Software",
            "Secretario"));
        
        return commission;
    }
    
    /**
     * Demuestra COVARIANZA usando EventRegistry
     */
    private static void demonstrateCovariance(List<Student> students,
                                             List<Teacher> teachers,
                                             List<Administrative> admins,
                                             List<NationalPresenter> nationalPresenters,
                                             List<InternationalPresenter> intlPresenters,
                                             List<Guest> guests) {
        EventRegistry registry = new EventRegistry();
        
        System.out.println("🔹 COVARIANZA permite registrar DIFERENTES tipos de listas:");
        System.out.println("   Usando List<? extends Participant> podemos aceptar:");
        System.out.println("   • List<Student>");
        System.out.println("   • List<Teacher>");
        System.out.println("   • List<NationalPresenter>");
        System.out.println("   • etc...\n");
        
        // COVARIANZA: Podemos pasar listas de tipos específicos
        registry.registerAll(students);
        registry.registerAll(teachers);
        registry.registerAll(admins);
        registry.registerAll(nationalPresenters);
        registry.registerAll(intlPresenters);
        registry.registerAll(guests);
        
        // COVARIANZA: Procesar ponentes
        List<Presenter> allPresenters = new ArrayList<>();
        allPresenters.addAll(nationalPresenters);
        allPresenters.addAll(intlPresenters);
        
        System.out.println("\n🔹 Procesando ponentes con List<? extends Presenter>:");
        registry.printPresenters(allPresenters);
    }
    
    /**
     * Demuestra CONTRAVARIANZA usando ParticipantManager
     */
    private static void demonstrateContravariance(List<Student> students, List<Teacher> teachers) {
        ParticipantManager manager = new ParticipantManager();
        
        System.out.println("🔸 CONTRAVARIANZA permite ESCRIBIR en DIFERENTES tipos de listas:");
        System.out.println("   Usando List<? super Student> podemos escribir en:");
        System.out.println("   • List<Student>");
        System.out.println("   • List<Participant>");
        System.out.println("   • List<Object>\n");
        
        // Diferentes destinos para demostrar contravarianza
        List<Student> studentList = new ArrayList<>();
        List<Participant> participantList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        
        // CONTRAVARIANZA: Podemos escribir Students en todas estas listas
        System.out.println("🔸 Agregando estudiantes a diferentes tipos de colecciones:");
        manager.addStudents(studentList, students.subList(0, 2));
        manager.addStudents(participantList, students.subList(2, 4));
        manager.addStudents(objectList, students.subList(4, 5));
        
        System.out.println("\n🔸 Resultados:");
        System.out.println("   • List<Student> tiene: " + studentList.size() + " elementos");
        System.out.println("   • List<Participant> tiene: " + participantList.size() + " elementos");
        System.out.println("   • List<Object> tiene: " + objectList.size() + " elementos");
        
        // CONTRAVARIANZA: Transferir participantes a colección general
        List<Participant> allTeachers = new ArrayList<>(teachers);
        manager.transferParticipants(participantList, allTeachers);
        
        System.out.println("\n🔸 Después de transferir docentes:");
        System.out.println("   • List<Participant> ahora tiene: " + participantList.size() + " elementos");
    }
    
    /**
     * Muestra estadísticas del seminario
     */
    private static void displayStatistics(List<Student> students,
                                         List<Teacher> teachers,
                                         List<Administrative> admins,
                                         List<NationalPresenter> nationalPresenters,
                                         List<InternationalPresenter> intlPresenters,
                                         List<Guest> guests,
                                         OrganizerCommission commission) {
        int totalParticipants = students.size() + teachers.size() + admins.size() +
                               nationalPresenters.size() + intlPresenters.size() +
                               guests.size() + commission.getMembers().size();
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           ESTADÍSTICAS DEL SEMINARIO                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ Total de Participantes: " + String.format("%-32s", totalParticipants) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ • Estudiantes:                  " + String.format("%-25s", students.size()) + "║");
        System.out.println("║ • Docentes:                     " + String.format("%-25s", teachers.size()) + "║");
        System.out.println("║ • Administrativos:              " + String.format("%-25s", admins.size()) + "║");
        System.out.println("║ • Ponentes Nacionales:          " + String.format("%-25s", nationalPresenters.size()) + "║");
        System.out.println("║ • Ponentes Internacionales:     " + String.format("%-25s", intlPresenters.size()) + "║");
        System.out.println("║ • Invitados Especiales:         " + String.format("%-25s", guests.size()) + "║");
        System.out.println("║ • Comisión Organizadora:        " + String.format("%-25s", commission.getMembers().size()) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Demuestra las presentaciones de los ponentes
     */
    private static void demonstratePresentations(List<NationalPresenter> nationalPresenters,
                                                List<InternationalPresenter> intlPresenters) {
        System.out.println("🎤 Presentaciones del Seminario:\n");
        
        System.out.println("📍 SESIÓN NACIONAL:");
        for (NationalPresenter presenter : nationalPresenters) {
            presenter.present(presenter.getTopic());
        }
        
        System.out.println("\n🌍 SESIÓN INTERNACIONAL:");
        for (InternationalPresenter presenter : intlPresenters) {
            presenter.present(presenter.getTopic());
        }
    }
    
    /**
     * Imprime el encabezado de la aplicación
     */
    private static void printHeader() {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║     III SEMINARIO INTERNACIONAL DE INGENIERÍA DE SOFTWARE     ║");
        System.out.println("║                                                                ║");
        System.out.println("║            Sistema de Gestión de Participantes                ║");
        System.out.println("║                                                                ║");
        System.out.println("║     Demostración de COVARIANZA y CONTRAVARIANZA en Java       ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Imprime el pie de página de la aplicación
     */
    private static void printFooter() {
        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    FIN DE LA DEMOSTRACIÓN                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  ✅ Covarianza (? extends): Flexibilidad en LECTURA           ║");
        System.out.println("║  ✅ Contravarianza (? super): Flexibilidad en ESCRITURA       ║");
        System.out.println("║                                                                ║");
        System.out.println("║  📚 Principio PECS: Producer Extends, Consumer Super          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}
