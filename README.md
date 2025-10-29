# III Seminario Internacional de Ingeniería de Software
## Sistema de Gestión de Participantes

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📋 Descripción

Proyecto académico que demuestra el uso de **Covarianza y Contravarianza** en Java mediante un sistema de gestión de participantes para el III Seminario Internacional de Ingeniería de Software. El proyecto implementa una jerarquía completa de clases que representa diferentes tipos de participantes (estudiantes, docentes, ponentes, administrativos, invitados, etc.) y utiliza genéricos con wildcards (`? extends` y `? super`) para demostrar estos conceptos fundamentales de la programación orientada a objetos.

### 🎯 Objetivos del Proyecto

1. **Demostrar Covarianza**: Uso de `? extends` para flexibilidad en lectura de colecciones
2. **Demostrar Contravarianza**: Uso de `? super` para flexibilidad en escritura de colecciones
3. **Aplicar el Principio PECS**: Producer Extends, Consumer Super
4. **Modelar un sistema real**: Gestión de participantes de un evento académico

## 🏗️ Arquitectura del Proyecto

```
contra-and-covariance/
├── src/main/java/com/app/
│   ├── App.java                    # Clase principal con integración completa
│   ├── model/
│   │   ├── Participant.java        # Clase abstracta base
│   │   └── Presenter.java          # Interface para ponentes
│   ├── participants/
│   │   ├── Student.java            # Estudiantes
│   │   ├── Teacher.java            # Docentes
│   │   ├── Administrative.java     # Personal administrativo
│   │   ├── NationalPresenter.java  # Ponentes nacionales
│   │   ├── InternationalPresenter.java # Ponentes internacionales
│   │   ├── Audience.java           # Público general
│   │   └── Guest.java              # Invitados especiales
│   ├── organizers/
│   │   ├── CommissionMember.java   # Miembros de la comisión
│   │   └── OrganizerCommission.java # Comisión organizadora
│   └── generics/
│       ├── EventRegistry.java      # Demostración de COVARIANZA
│       ├── ParticipantManager.java # Demostración de CONTRAVARIANZA
│       └── GenericsDemo.java       # Ejemplos prácticos integrados
├── docs/
│   ├── class-diagram.puml          # Diagrama UML del sistema
│   ├── INFORME.md                  # Informe técnico completo
│   └── screenshots/
│       └── ejecucion_completa.txt  # Salida completa de la ejecución
├── pom.xml                         # Configuración de Maven
└── README.md                       # Este archivo
```

## 🚀 Instalación y Ejecución

### Prerrequisitos

- **Java 21** o superior
- **Maven 3.8** o superior
- **Git** (para clonar el repositorio)

### Verificar versiones instaladas

```bash
java -version
mvn -version
```

### Clonar el repositorio

```bash
git clone https://github.com/gustadev24/contra-and-covariance.git
cd contra-and-covariance
```

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass="com.app.App"
```

### Ejecutar con salida limpia (sin logs de Maven)

```bash
mvn exec:java -Dexec.mainClass="com.app.App" -q
```

## 📊 Estructura de Clases

### Jerarquía de Participantes

```
Participant (abstract)
├── Student
├── Teacher
│   └── CommissionMember
├── Administrative
├── Guest
├── Audience
├── NationalPresenter (implements Presenter)
└── InternationalPresenter (implements Presenter)
```

### Clases de Genéricos

1. **EventRegistry** - Demostración de Covarianza (`? extends`)
   - `printPresenters(List<? extends Presenter>)`
   - `registerAll(List<? extends Participant>)`
   - `compareLists(List<? extends Participant>, List<? extends Participant>)`

2. **ParticipantManager** - Demostración de Contravarianza (`? super`)
   - `addStudent(List<? super Student>, Student)`
   - `addStudents(List<? super Student>, List<Student>)`
   - `transferParticipants(List<? super Participant>, List<Participant>)`

3. **GenericsDemo** - Ejemplos Prácticos
   - Demo 1: Covarianza - Lectura flexible
   - Demo 2: Contravarianza - Escritura flexible
   - Demo 3: Combinación de ambos conceptos
   - Demo 4: Principio PECS

## 🔍 Conceptos Clave

### Covarianza (`? extends T`)

**Cuándo usar**: Cuando necesitas **LEER/CONSUMIR** datos de una colección.

```java
// Podemos pasar List<Student>, List<Teacher>, List<NationalPresenter>, etc.
public void registerAll(List<? extends Participant> participants) {
    for (Participant p : participants) {
        // ✅ LECTURA permitida
        p.register();
    }
    // ❌ ESCRITURA NO permitida (excepto null)
    // participants.add(new Student(...)); // Error de compilación
}
```

**Ventajas**:
- Flexibilidad para aceptar listas de subtipos
- Type-safe al leer elementos
- Útil para métodos que solo procesan/leen datos

### Contravarianza (`? super T`)

**Cuándo usar**: Cuando necesitas **ESCRIBIR/PRODUCIR** datos en una colección.

```java
// Podemos pasar List<Student>, List<Participant>, List<Object>
public void addStudent(List<? super Student> list, Student student) {
    // ✅ ESCRITURA permitida
    list.add(student);
    
    // ⚠️ LECTURA limitada (solo como Object)
    Object obj = list.get(0);
}
```

**Ventajas**:
- Flexibilidad para escribir en listas de supertipos
- Type-safe al agregar elementos
- Útil para métodos que producen/escriben datos

### Principio PECS (Producer Extends, Consumer Super)

| Escenario | Wildcard | Ejemplo |
|-----------|----------|---------|
| **La colección PRODUCE datos** (lees de ella) | `? extends T` | `List<? extends Participant>` |
| **La colección CONSUME datos** (escribes en ella) | `? super T` | `List<? super Student>` |
| **Necesitas LEER Y ESCRIBIR** | Sin wildcard | `List<Student>` |

## 📈 Salida de la Aplicación

La aplicación ejecuta dos partes principales:

### Parte 1: Demostraciones Interactivas
- **Demo 1**: Covarianza - Lectura flexible de diferentes tipos
- **Demo 2**: Contravarianza - Escritura flexible en diferentes colecciones
- **Demo 3**: Combinación de covarianza y contravarianza
- **Demo 4**: Explicación del principio PECS

### Parte 2: Sistema Integrado
1. Creación de participantes (21 en total)
2. Demostración de covarianza con registro masivo
3. Demostración de contravarianza con gestión de colecciones
4. Estadísticas del seminario
5. Sesión de presentaciones

## 📚 Documentación Adicional

- **[INFORME.md](docs/INFORME.md)**: Documentación técnica detallada con explicación de todos los conceptos
- **[class-diagram.puml](docs/class-diagram.puml)**: Diagrama UML en formato PlantUML
- **[Salida de Ejecución](docs/screenshots/ejecucion_completa.txt)**: Captura completa de la ejecución del programa

## 🧪 Ejemplos de Uso

### Ejemplo 1: Covarianza - Procesar diferentes tipos de participantes

```java
EventRegistry registry = new EventRegistry();

List<Student> students = Arrays.asList(/* ... */);
List<Teacher> teachers = Arrays.asList(/* ... */);

// ✅ Ambas llamadas son válidas gracias a covarianza
registry.registerAll(students);    // List<Student>
registry.registerAll(teachers);    // List<Teacher>
```

### Ejemplo 2: Contravarianza - Agregar a diferentes colecciones

```java
ParticipantManager manager = new ParticipantManager();
Student student = new Student(/* ... */);

List<Student> studentList = new ArrayList<>();
List<Participant> participantList = new ArrayList<>();
List<Object> objectList = new ArrayList<>();

// ✅ Todas estas llamadas son válidas gracias a contravarianza
manager.addStudent(studentList, student);      // List<Student>
manager.addStudent(participantList, student);  // List<Participant>
manager.addStudent(objectList, student);       // List<Object>
```

## 🛠️ Comandos Útiles

### Compilar sin ejecutar
```bash
mvn compile
```

### Limpiar archivos compilados
```bash
mvn clean
```

### Generar JavaDoc
```bash
mvn javadoc:javadoc
```

### Ver estructura del proyecto
```bash
find src -name "*.java" -type f
```

### Contar líneas de código
```bash
find src -name "*.java" | xargs wc -l
```

## 👥 Contribuciones

Este es un proyecto académico desarrollado por el equipo del III Seminario Internacional de Ingeniería de Software.

### Equipo de Desarrollo

- **Persona 1**: Estructura base y participantes básicos (Participant, Student, Teacher, Administrative)
- **Persona 2**: Ponentes y público (Presenter, NationalPresenter, InternationalPresenter, Audience)
- **Persona 3**: Comisión organizadora e invitados (CommissionMember, OrganizerCommission, Guest)
- **Persona 4**: Covarianza y Contravarianza (EventRegistry, ParticipantManager, GenericsDemo, Documentación)
- **Persona 5**: Integración y coordinación (App.java, README, Diagrama UML, Documentación de integración)

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 🔗 Enlaces Útiles

- [Documentación oficial de Java Generics](https://docs.oracle.com/javase/tutorial/java/generics/)
- [Effective Java - Item 31: Use bounded wildcards to increase API flexibility](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Understanding Covariance and Contravariance](https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science))

## ❓ Preguntas Frecuentes

### ¿Cuándo debo usar `? extends` vs `? super`?

Usa la regla **PECS**:
- **Producer Extends**: Si la colección **produce/proporciona** elementos que vas a leer → `? extends`
- **Consumer Super**: Si la colección **consume/recibe** elementos que vas a escribir → `? super`

### ¿Por qué no puedo escribir en `List<? extends T>`?

Porque el compilador no puede garantizar el tipo exacto de la lista. Podría ser `List<Student>` o `List<Teacher>`, y agregar un `Student` a una lista que es realmente `List<Teacher>` rompería la seguridad de tipos.

### ¿Por qué al leer de `List<? super T>` solo obtengo `Object`?

Porque el compilador solo puede garantizar que la lista puede contener `T` o cualquier supertipo. Podría ser `List<Participant>` o `List<Object>`, por lo que el único tipo común garantizado es `Object`.

## 📞 Contacto

Para preguntas o sugerencias sobre este proyecto, por favor contacta al equipo de desarrollo a través del repositorio de GitHub.

---

**Desarrollado con ❤️ para el III Seminario Internacional de Ingeniería de Software**
