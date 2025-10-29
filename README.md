# III Seminario Internacional de Ingeniería de Software
## Sistema de Gestión de Participantes

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📋 Descripción

Proyecto académico que demuestra el uso de **Covarianza y Contravarianza** en Java mediante un sistema de gestión de participantes para el III Seminario Internacional de Ingeniería de Software. El proyecto implementa una jerarquía completa de clases que representa diferentes tipos de participantes (estudiantes, docentes, ponentes, administrativos, invitados, etc.) y utiliza genéricos con wildcards (`? extends` y `? super`) para demostrar estos conceptos fundamentales de la programación orientada a objetos.

**🆕 NUEVA FUNCIONALIDAD**: Sistema completo de **métodos de pago** (Yape, Tarjeta, Efectivo) que continúa demostrando covarianza y contravarianza en un contexto práctico diferente.

### 🎯 Objetivos del Proyecto

1. **Demostrar Covarianza**: Uso de `? extends` para flexibilidad en lectura de colecciones
2. **Demostrar Contravarianza**: Uso de `? super` para flexibilidad en escritura de colecciones
3. **Aplicar el Principio PECS**: Producer Extends, Consumer Super
4. **Modelar un sistema real**: Gestión de participantes de un evento académico
5. **🆕 Sistema de Pagos**: Implementar métodos de pago con covarianza y contravarianza

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
│   └── payment/                    # 🆕 NUEVA FUNCIONALIDAD
│       ├── PaymentMethod.java      # Clase abstracta base de pagos
│       ├── DigitalPayment.java     # Clase abstracta para pagos digitales
│       ├── YapePayment.java        # Implementación Yape
│       ├── CardPayment.java        # Implementación Tarjeta
│       ├── CashPayment.java        # Implementación Efectivo
│       ├── PaymentProcessor.java   # COVARIANZA con pagos
│       ├── PaymentRegistry.java    # CONTRAVARIANZA con pagos
│       └── PaymentDemo.java        # Demostraciones de pagos
├── docs/
│   ├── class-diagram.puml          # Diagrama UML del sistema
│   ├── INFORME.md                  # Informe técnico completo
│   ├── PAYMENT_FEATURE.md          # 🆕 Documentación de métodos de pago
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

### 🆕 Jerarquía de Métodos de Pago

```
PaymentMethod (abstract)
├── DigitalPayment (abstract)
│   ├── YapePayment
│   └── CardPayment
└── CashPayment
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

### 🆕 Clases de Pagos (Nueva Funcionalidad)

4. **PaymentProcessor** - Demostración de Covarianza con Pagos (`? extends`)
   - `processAllPayments(List<? extends PaymentMethod>)`: Procesa cualquier tipo de pago
   - `processDigitalPayments(List<? extends DigitalPayment>)`: Procesa pagos digitales
   - `generateReport(List<? extends PaymentMethod>)`: Genera reportes de pagos

5. **PaymentRegistry** - Demostración de Contravarianza con Pagos (`? super`)
   - `registerYapePayment(List<? super YapePayment>, YapePayment)`: Registra pago Yape
   - `registerCardPayment(List<? super CardPayment>, CardPayment)`: Registra pago con tarjeta
   - `registerPayments(List<? super PaymentMethod>, List<PaymentMethod>)`: Registra múltiples pagos
   - `transferPayments(List<? super PaymentMethod>, List<? extends PaymentMethod>)`: Transfiere pagos

6. **PaymentDemo** - Demostraciones Completas de Pagos
   - Demo 1: Covarianza - Procesamiento flexible de pagos
   - Demo 2: Contravarianza - Registro flexible de pagos
   - Demo 3: Combinación de ambos conceptos
   - Demo 4: Principio PECS aplicado a pagos

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

## � Salida de la Aplicación

La aplicación ejecuta tres partes principales:

### Parte 1: Demostraciones Interactivas de Genéricos
- **Demo 1**: Covarianza - Lectura flexible de diferentes tipos
- **Demo 2**: Contravarianza - Escritura flexible en diferentes colecciones
- **Demo 3**: Combinación de covarianza y contravarianza
- **Demo 4**: Explicación del principio PECS

### Parte 2: Sistema Integrado de Participantes
1. Creación de participantes (21 en total)
2. Demostración de covarianza con registro masivo
3. Demostración de contravarianza con gestión de colecciones
4. Estadísticas del seminario
5. Sesión de presentaciones
6. **🆕 Procesamiento de pagos de participantes**

### 🆕 Parte 3: Sistema de Pagos
1. **Demo 1**: Covarianza - Procesamiento flexible de pagos (Yape, Tarjeta, Efectivo)
2. **Demo 2**: Contravarianza - Registro flexible en diferentes colecciones
3. **Demo 3**: Combinación de lectura y escritura flexible
4. **Demo 4**: Principio PECS aplicado a pagos

## 📚 Documentación Adicional

- **[INFORME.md](docs/INFORME.md)**: Documentación técnica detallada con explicación de todos los conceptos
- **🆕 [PAYMENT_FEATURE.md](docs/PAYMENT_FEATURE.md)**: Documentación completa del sistema de métodos de pago
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

### 🆕 Ejemplo 3: Covarianza con Pagos - Procesar diferentes tipos de pago

```java
PaymentProcessor processor = new PaymentProcessor();

List<YapePayment> yapePayments = Arrays.asList(/* ... */);
List<CardPayment> cardPayments = Arrays.asList(/* ... */);
List<CashPayment> cashPayments = Arrays.asList(/* ... */);

// ✅ Todas estas llamadas son válidas gracias a covarianza
processor.processAllPayments(yapePayments);    // List<YapePayment>
processor.processAllPayments(cardPayments);    // List<CardPayment>
processor.processAllPayments(cashPayments);    // List<CashPayment>
```

### 🆕 Ejemplo 4: Contravarianza con Pagos - Registrar en diferentes colecciones

```java
PaymentRegistry registry = new PaymentRegistry();
YapePayment yape = new YapePayment(50.0, "Ana García", "987654321", "YAPE-001");

List<YapePayment> yapeList = new ArrayList<>();
List<DigitalPayment> digitalList = new ArrayList<>();
List<PaymentMethod> paymentList = new ArrayList<>();
List<Object> objectList = new ArrayList<>();

// ✅ Todas estas llamadas son válidas gracias a contravarianza
registry.registerYapePayment(yapeList, yape);      // List<YapePayment>
registry.registerYapePayment(digitalList, yape);   // List<DigitalPayment>
registry.registerYapePayment(paymentList, yape);   // List<PaymentMethod>
registry.registerYapePayment(objectList, yape);    // List<Object>
```

### 🆕 Ejemplo 5: Agregar pagos a participantes

```java
// Crear participante
Student student = new Student("S001", "Ana García", "ana@mail.com", 
                              "Universidad Nacional", "Ing. Software");

// Crear y agregar pagos
YapePayment yape = new YapePayment(50.0, "Ana García", "987654321", "YAPE-001");
student.addPayment(yape);

// Verificar estado
System.out.println(student.getPaymentInfo());
System.out.println("Ha pagado completamente: " + student.hasFullyPaid());
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
- **🆕 Extensión**: Sistema de Métodos de Pago (PaymentMethod, YapePayment, CardPayment, CashPayment, PaymentProcessor, PaymentRegistry, PaymentDemo, Documentación de pagos)

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
