package com.app.generics;

import com.app.model.Participant;
import com.app.model.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * EventRegistry - Demostración de COVARIANZA en Java
 * 
 * COVARIANZA permite LEER de colecciones de tipos más específicos
 * usando el wildcard '? extends Type'.
 * 
 * Regla: "Producer extends" - Cuando producimos/leemos datos
 */
public class EventRegistry {
    
    private List<Participant> allParticipants;
    
    public EventRegistry() {
        this.allParticipants = new ArrayList<>();
    }
    
    /**
     * COVARIANZA - Ejemplo 1: Lectura de ponentes
     * 
     * '? extends Presenter' significa: "Cualquier tipo que SEA un Presenter o herede de él"
     * Podemos LEER de la lista, pero NO podemos ESCRIBIR en ella (excepto null)
     * 
     * ¿Por qué? Porque no sabemos el tipo exacto:
     * - Podría ser List<NationalPresenter>
     * - Podría ser List<InternationalPresenter>
     * - Podría ser List<Presenter>
     * 
     * Java garantiza que todo lo que leamos SERÁ un Presenter, pero no puede
     * garantizar que lo que escribamos sea del tipo correcto de la lista.
     */
    public void printPresenters(List<? extends Presenter> presenters) {
        System.out.println("\n=== COVARIANZA - Imprimiendo Ponentes ===");
        System.out.println("Tipo de parámetro: List<? extends Presenter>");
        System.out.println("Podemos LEER pero NO ESCRIBIR");
        System.out.println("Total de ponentes: " + presenters.size());
        
        for (Presenter presenter : presenters) {
            // LECTURA permitida: sabemos que es un Presenter
            System.out.println("  - " + presenter.getClass().getSimpleName());
            
            // Podemos llamar métodos de Presenter
            presenter.present("Tema del seminario");
        }
        
        // ESCRITURA NO permitida (descomentar para ver error):
        // presenters.add(new NationalPresenter(...)); // ❌ Error de compilación
        // presenters.add(new InternationalPresenter(...)); // ❌ Error de compilación
        
        // Solo null es permitido porque null es compatible con cualquier tipo
        // presenters.add(null); // ✅ Compilaría (pero no es útil)
    }
    
    /**
     * COVARIANZA - Ejemplo 2: Obtener participantes filtrados
     * 
     * Retornamos List<? extends Participant> para indicar que retornamos
     * una lista de algún tipo específico de Participant.
     * 
     * El que llama puede LEER de esta lista sabiendo que obtendrá Participants,
     * pero no puede ESCRIBIR en ella sin conocer el tipo exacto.
     */
    public List<? extends Participant> getParticipantsByType(String type) {
        System.out.println("\n=== COVARIANZA - Filtrando por tipo: " + type + " ===");
        
        List<Participant> filtered = new ArrayList<>();
        for (Participant p : allParticipants) {
            if (p.getType().equalsIgnoreCase(type)) {
                filtered.add(p);
            }
        }
        
        System.out.println("Encontrados: " + filtered.size() + " participantes de tipo " + type);
        return filtered; // Retornamos List<Participant> que es subtipo de List<? extends Participant>
    }
    
    /**
     * COVARIANZA - Ejemplo 3: Registro masivo
     * 
     * Acepta cualquier lista de tipos que extiendan Participant.
     * Podemos leer cada elemento y procesarlo como Participant.
     */
    public void registerAll(List<? extends Participant> participants) {
        System.out.println("\n=== COVARIANZA - Registro masivo ===");
        System.out.println("Registrando " + participants.size() + " participantes...");
        
        for (Participant p : participants) {
            // LECTURA: Podemos leer y tratar cada elemento como Participant
            p.register();
            allParticipants.add(p); // Agregamos a nuestra lista interna
        }
        
        System.out.println("Total de participantes registrados: " + allParticipants.size());
    }
    
    /**
     * COVARIANZA - Ejemplo 4: Comparar listas
     * 
     * Podemos comparar listas de diferentes tipos específicos de Participant
     * porque ambas son '? extends Participant'.
     */
    public void compareLists(List<? extends Participant> list1, List<? extends Participant> list2) {
        System.out.println("\n=== COVARIANZA - Comparando listas ===");
        System.out.println("Lista 1 tiene " + list1.size() + " elementos");
        System.out.println("Lista 2 tiene " + list2.size() + " elementos");
        
        // Podemos LEER de ambas listas
        if (!list1.isEmpty() && !list2.isEmpty()) {
            Participant first1 = list1.get(0);
            Participant first2 = list2.get(0);
            System.out.println("Primer elemento Lista 1: " + first1.getName());
            System.out.println("Primer elemento Lista 2: " + first2.getName());
        }
    }
    
    /**
     * Método auxiliar para agregar participantes a la lista interna
     */
    public void addParticipant(Participant participant) {
        allParticipants.add(participant);
    }
    
    /**
     * Obtener todos los participantes registrados
     */
    public List<Participant> getAllParticipants() {
        return new ArrayList<>(allParticipants);
    }
    
    /**
     * RESUMEN COVARIANZA:
     * 
     * ✅ Usar '? extends T' cuando:
     *    - Necesitamos LEER/CONSUMIR elementos de una colección
     *    - Queremos aceptar colecciones de tipos más específicos
     *    - Queremos flexibilidad en entrada (Producer)
     * 
     * ❌ NO podemos:
     *    - ESCRIBIR elementos en la colección (excepto null)
     *    - Agregar elementos específicos
     * 
     * 📝 Regla mnemotécnica: "Producer Extends"
     *    - La colección PRODUCE/da elementos que podemos consumir
     */
}
