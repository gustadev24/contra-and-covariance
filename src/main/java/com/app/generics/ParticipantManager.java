package com.app.generics;

import com.app.model.Participant;
import com.app.participants.Student;

import java.util.List;

/**
 * ParticipantManager - Demostración de CONTRAVARIANZA en Java
 * 
 * CONTRAVARIANZA permite ESCRIBIR en colecciones de tipos más generales
 * usando el wildcard '? super Type'.
 * 
 * Regla: "Consumer super" - Cuando consumimos/escribimos datos
 */
public class ParticipantManager {
    
    /**
     * CONTRAVARIANZA - Ejemplo 1: Agregar estudiantes
     * 
     * '? super Student' significa: "Cualquier tipo que SEA Student o un supertipo de Student"
     * Podemos ESCRIBIR Students en la lista, pero al LEER solo obtenemos Object
     * 
     * ¿Por qué? Porque la lista podría ser:
     * - List<Student> - puede contener Students
     * - List<Participant> - puede contener Students (porque Student extends Participant)
     * - List<Object> - puede contener Students (porque todo es Object)
     * 
     * Java garantiza que podemos agregar Students de forma segura, pero al leer
     * solo puede garantizar que es un Object (el supertipo común).
     */
    public void addStudent(List<? super Student> list, Student student) {
        System.out.println("\n=== CONTRAVARIANZA - Agregando estudiante ===");
        System.out.println("Tipo de parámetro: List<? super Student>");
        System.out.println("Podemos ESCRIBIR pero solo LEER como Object");
        
        // ESCRITURA permitida: podemos agregar Students
        list.add(student); // ✅ Permitido
        System.out.println("✅ Estudiante agregado: " + student.getName());
        
        // También podemos agregar subtipos de Student si existieran
        // list.add(new GraduateStudent(...)); // ✅ Si GraduateStudent extends Student
        
        // LECTURA limitada: solo podemos leer como Object
        if (!list.isEmpty()) {
            Object obj = list.get(0); // Solo podemos asegurar que es Object
            System.out.println("Al leer obtenemos Object: " + obj.getClass().getSimpleName());
            
            // NO podemos asumir que es Student sin cast:
            // Student s = list.get(0); // ❌ Error de compilación
            
            // Necesitamos cast explícito (no seguro en tiempo de compilación):
            // Student s = (Student) list.get(0); // ⚠️ Compilaría pero podría fallar en runtime
        }
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 2: Agregar múltiples estudiantes
     * 
     * Útil para transferir estudiantes a colecciones más generales.
     */
    public void addStudents(List<? super Student> destination, List<Student> students) {
        System.out.println("\n=== CONTRAVARIANZA - Agregando múltiples estudiantes ===");
        System.out.println("Transfiriendo " + students.size() + " estudiantes a la lista destino");
        
        for (Student student : students) {
            // ESCRITURA: Podemos escribir todos los Students
            destination.add(student);
            System.out.println("  ✅ Transferido: " + student.getName());
        }
        
        System.out.println("Total de elementos en destino: " + destination.size());
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 3: Transferir participantes
     * 
     * Podemos transferir participantes de cualquier tipo a una lista que
     * acepte Participant o sus supertipos (como Object).
     */
    public void transferParticipants(List<? super Participant> destination, List<Participant> source) {
        System.out.println("\n=== CONTRAVARIANZA - Transferencia de participantes ===");
        System.out.println("Tipo de destino: List<? super Participant>");
        System.out.println("Elementos a transferir: " + source.size());
        
        for (Participant p : source) {
            // ESCRITURA: Podemos escribir cualquier Participant
            destination.add(p); // ✅ Siempre seguro
            System.out.println("  ✅ Transferido: " + p.getType() + " - " + p.getName());
        }
        
        System.out.println("Transferencia completada. Total en destino: " + destination.size());
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 4: Operación condicional
     * 
     * Agregar participantes a una lista general solo si cumplen una condición.
     */
    public void addIfRegistered(List<? super Participant> list, Participant participant) {
        System.out.println("\n=== CONTRAVARIANZA - Agregar condicional ===");
        
        // Simulamos que verificamos si está registrado
        boolean isRegistered = participant.getId() != null && !participant.getId().isEmpty();
        
        if (isRegistered) {
            // ESCRITURA: Agregamos el participante
            list.add(participant);
            System.out.println("✅ Participante agregado: " + participant.getName());
        } else {
            System.out.println("❌ Participante no registrado, no se agregó: " + participant.getName());
        }
    }
    
    /**
     * CONTRAVARIANZA - Ejemplo 5: Combinar listas
     * 
     * Acepta dos listas de Students y las combina en una lista más general.
     * Demuestra cómo '? super Student' permite flexibilidad en la escritura.
     */
    public void mergeLists(List<? super Student> destination, 
                          List<Student> list1, 
                          List<Student> list2) {
        System.out.println("\n=== CONTRAVARIANZA - Combinar listas ===");
        System.out.println("Combinando dos listas de estudiantes en una lista general");
        
        int initialSize = destination.size();
        
        // ESCRITURA: Agregamos todos de la primera lista
        for (Student s : list1) {
            destination.add(s);
        }
        
        // ESCRITURA: Agregamos todos de la segunda lista
        for (Student s : list2) {
            destination.add(s);
        }
        
        int added = destination.size() - initialSize;
        System.out.println("✅ Se agregaron " + added + " estudiantes a la lista destino");
        System.out.println("Tamaño final de la lista destino: " + destination.size());
    }
    
    /**
     * COMPARACIÓN: Método SIN wildcard para contraste
     * 
     * Este método solo acepta exactamente List<Student>, no List<Participant> ni List<Object>
     */
    public void addStudentStrict(List<Student> list, Student student) {
        System.out.println("\n=== SIN WILDCARD - Método estricto ===");
        System.out.println("Tipo de parámetro: List<Student> (exacto, sin wildcard)");
        
        list.add(student);
        
        // Ventaja: Podemos leer como Student directamente
        if (!list.isEmpty()) {
            Student s = list.get(0); // ✅ Sabemos que es Student
            System.out.println("Podemos leer como Student: " + s.getName());
        }
        
        // Desventaja: NO podemos pasar List<Participant> o List<Object>
        // List<Participant> participants = new ArrayList<>();
        // addStudentStrict(participants, student); // ❌ Error de compilación
    }
    
    /**
     * RESUMEN CONTRAVARIANZA:
     * 
     * ✅ Usar '? super T' cuando:
     *    - Necesitamos ESCRIBIR/AGREGAR elementos a una colección
     *    - Queremos aceptar colecciones de tipos más generales
     *    - Queremos flexibilidad en salida (Consumer)
     * 
     * ❌ Al leer:
     *    - Solo podemos garantizar Object
     *    - Necesitamos cast para tipos específicos (no type-safe)
     * 
     * 📝 Regla mnemotécnica: "Consumer Super"
     *    - La colección CONSUME/recibe elementos que le damos
     * 
     * 💡 Ejemplo práctico:
     *    - List<? super Student> puede ser List<Student>, List<Participant>, o List<Object>
     *    - Todas pueden contener Students de forma segura
     *    - Por eso podemos ESCRIBIR Students
     *    - Pero al leer, solo sabemos que es Object (el denominador común)
     */
}
