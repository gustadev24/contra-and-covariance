# Organización del Proyecto: III Seminario Internacional de Ingeniería de Software
# ⚡ VERSIÓN RÁPIDA - MENOS DE 24 HORAS

## 📋 Descripción del Proyecto
Desarrollar un **MVP (Mínimo Producto Viable)** que modele participantes del III Seminario Internacional de Ingeniería de Software, aplicando conceptos de **Covarianza y Contravarianza** en Java 21.

### Participantes a modelar:
- Comisión Organizadora
- Estudiantes
- Docentes
- Administrativos
- Invitados
- Ponentes Nacionales
- Ponentes Internacionales
- Público General (Nacional e Internacional)

### ⚠️ IMPORTANTE:
- **Diagrama UML**: Se generará DESPUÉS del código (reverse engineering)
- **Informe**: Mínimo necesario con screenshots
- **Foco**: Código funcionando + ejemplos de covarianza/contravarianza

---

## 👥 División de Tareas por Persona - MODO RÁPIDO

### 🔵 **Persona 1: Estructura Base + Participantes Básicos (2-3 horas)**
**Responsabilidad:** Jerarquía de clases fundamental

#### Tareas:
1. **Crear clase base `Participant.java`** (abstracta)
   - Atributos: id, name, email, type
   - Métodos: register(), getInfo()

2. **Implementar 3 clases simples:**
   - `Student.java` - Estudiantes
   - `Teacher.java` - Docentes
   - `Administrative.java` - Administrativos

**Entregables:**
- `model/Participant.java`
- `participants/Student.java`
- `participants/Teacher.java`
- `participants/Administrative.java`

---

### 🟢 **Persona 2: Ponentes + Público (2-3 horas)**
**Responsabilidad:** Implementar tipos de ponentes y audiencia

#### Tareas:
1. **Crear interface `Presenter`:**
   - Método: `present(String topic)`

2. **Implementar ponentes:**
   - `NationalPresenter.java` - Extiende Participant, implementa Presenter
   - `InternationalPresenter.java` - Extiende Participant, implementa Presenter
   - Atributos extra: country (internacional), topic

3. **Implementar audiencia:**
   - `Audience.java` - Clase simple que extiende Participant
   - Atributo: isInternational (boolean)

**Entregables:**
- `model/Presenter.java` (interface)
- `participants/NationalPresenter.java`
- `participants/InternationalPresenter.java`
- `participants/Audience.java`

---

### 🟡 **Persona 3: Comisión Organizadora + Invitados (2-3 horas)**
**Responsabilidad:** Comisión e invitados

#### Tareas:
1. **Implementar Comisión:**
   - `CommissionMember.java` - Extiende Participant
   - `OrganizerCommission.java` - Gestiona lista de miembros
   - Métodos: addMember(), listMembers()

2. **Implementar invitados:**
   - `Guest.java` - Extiende Participant
   - Atributos: organization, country

**Entregables:**
- `organizers/CommissionMember.java`
- `organizers/OrganizerCommission.java`
- `participants/Guest.java`

---

### 🟣 **Persona 4: Covarianza y Contravarianza - CRÍTICO (3-4 horas)**
**Responsabilidad:** Implementar y demostrar genéricos

#### Tareas:
1. **Crear `EventRegistry.java`:**
   ```java
   public class EventRegistry {
       // COVARIANZA - Leer de colecciones específicas
       public void printPresenters(List<? extends Presenter> presenters) { }
       public List<? extends Participant> getParticipantsByType(String type) { }
   }
   ```

2. **Crear `ParticipantManager.java`:**
   ```java
   public class ParticipantManager {
       // CONTRAVARIANZA - Escribir a colecciones generales
       public void addToGeneral(List<? super Student> list, Student student) { }
       public void transferParticipants(List<? super Participant> destination) { }
   }
   ```

3. **Crear `GenericsDemo.java`:**
   - Demostrar covarianza: lectura de diferentes tipos
   - Demostrar contravarianza: escritura a colecciones generales
   - Comentarios explicativos en el código

4. **Documentar:**
   - `docs/COVARIANZA_CONTRAVARIANZA.md` - Explicación breve con ejemplos del código

**Entregables:**
- `generics/EventRegistry.java`
- `generics/ParticipantManager.java`
- `generics/GenericsDemo.java`
- `docs/COVARIANZA_CONTRAVARIANZA.md`

---

### 🔴 **Persona 5: Integración + App Principal (2-3 horas)**
**Responsabilidad:** App principal y coordinación de ejecución

#### Tareas:
1. **Actualizar `App.java`:**
   - Crear instancias de todos los tipos de participantes
   - Demostrar covarianza con EventRegistry
   - Demostrar contravarianza con ParticipantManager
   - Imprimir resultados claros en consola

2. **Capturar ejecución:**
   - Ejecutar el programa
   - Tomar screenshots (mínimo 3)
   - Guardar en `docs/screenshots/`

3. **Crear README.md:**
   - Cómo compilar: `mvn clean compile`
   - Cómo ejecutar: `mvn exec:java -Dexec.mainClass="com.app.App"`
   - Estructura del proyecto

4. **Generar diagrama UML (reverse engineering):**
   - Usar IntelliJ IDEA (Diagrams > Show Diagram)
   - O usar: `mvn javadoc:javadoc` + herramienta online
   - O PlantUML con plugin

5. **Documentar su parte del informe:**
   - Sección de integración y ejecución
   - Explicación de la App principal
   - Screenshots con descripciones

**Entregables:**
- `App.java` (actualizado)
- `docs/screenshots/` (mínimo 3 imágenes)
- `README.md`
- `docs/class-diagram.png`
- Su sección del `docs/INFORME.md`

---

## 📝 INFORME COLABORATIVO

### Cada persona documenta su parte:

**📌 Persona 1:** Escribe sobre la jerarquía base
- Diseño de la clase abstracta Participant
- Explicación de Student, Teacher, Administrative
- Justificación de decisiones de diseño

**📌 Persona 2:** Escribe sobre ponentes y público
- Interface Presenter y su propósito
- Diferencias entre ponentes nacionales e internacionales
- Explicación de la clase Audience

**📌 Persona 3:** Escribe sobre comisión e invitados
- Estructura de la comisión organizadora
- Gestión de miembros
- Clase Guest y sus características

**📌 Persona 4:** Escribe sobre genéricos (⚠️ SECCIÓN CRÍTICA)
- Explicación detallada de COVARIANZA con ejemplos del código
- Explicación detallada de CONTRAVARIANZA con ejemplos del código
- Cuándo usar `? extends` vs `? super`
- Screenshots de ejecución de GenericsDemo.java

**📌 Persona 5:** Escribe sobre integración
- Explicación de App.java
- Flujo de ejecución del programa
- Screenshots generales
- Diagrama UML con explicación

### 📄 Estructura del INFORME.md:

```markdown
# Informe: Sistema de Participantes - III Seminario Internacional

## 1. Introducción (Todos - 1 párrafo conjunto)

## 2. Jerarquía de Clases Base (Persona 1)
- Clase Participant
- Clases básicas de participantes

## 3. Ponentes y Público (Persona 2)
- Interface Presenter
- Implementaciones

## 4. Comisión Organizadora e Invitados (Persona 3)
- OrganizerCommission
- CommissionMember y Guest

## 5. Covarianza y Contravarianza (Persona 4) ⚠️ CRÍTICO
- Explicación teórica
- Ejemplos con código
- Screenshots de ejecución

## 6. Integración y Ejecución (Persona 5)
- App.java
- Diagrama UML
- Screenshots generales

## 7. Conclusiones (Todos - cada uno su párrafo)
```

---

## ⏱️ Cronograma Express - Menos de 24 horas

### ✅ Checkpoint 1: Primeras 4 horas
- **Persona 1**: Clases base listas
- **Persona 2**: Ponentes listos
- **Persona 3**: Comisión lista
- **Persona 4**: Estructura de genéricos creada

### ✅ Checkpoint 2: Horas 5-8
- **Persona 4**: Ejemplos de covarianza/contravarianza funcionando
- **Persona 5**: App.java integrando todo
- **Todos**: Code review rápido

### ✅ Checkpoint 3: Horas 9-12
- **Persona 5**: Screenshots + crea estructura del informe + README
- **Persona 4**: Documentación de genéricos
- **TODOS**: Escriben su sección del informe
- **Persona 5**: Diagrama UML generado

### 🎯 Final: Hora 12-24
- **Todos**: Revisión del informe completo
- **Persona 5**: Integra todas las secciones del informe
- **Todos**: Ajustes finales
- **Preparación para entrega**

---

## 📂 Estructura Mínima del Proyecto

```
contra-and-covariance/
├── pom.xml ✅
├── README.md (Persona 5)
├── PROJECT_ORGANIZATION.md ✅
├── docs/
│   ├── INFORME.md (Persona 5)
│   ├── COVARIANZA_CONTRAVARIANZA.md (Persona 4)
│   ├── class-diagram.png (Persona 5)
│   └── screenshots/
│       ├── ejecucion1.png
│       ├── ejecucion2.png
│       └── ejecucion3.png
├── src/main/java/com/app/
│   ├── App.java (Persona 5)
│   ├── model/
│   │   ├── Participant.java (Persona 1)
│   │   └── Presenter.java (Persona 2 - interface)
│   ├── participants/
│   │   ├── Student.java (Persona 1)
│   │   ├── Teacher.java (Persona 1)
│   │   ├── Administrative.java (Persona 1)
│   │   ├── NationalPresenter.java (Persona 2)
│   │   ├── InternationalPresenter.java (Persona 2)
│   │   ├── Audience.java (Persona 2)
│   │   └── Guest.java (Persona 3)
│   ├── organizers/
│   │   ├── CommissionMember.java (Persona 3)
│   │   └── OrganizerCommission.java (Persona 3)
│   └── generics/
│       ├── EventRegistry.java (Persona 4)
│       ├── ParticipantManager.java (Persona 4)
│       └── GenericsDemo.java (Persona 4)
└── target/
```

---

## 🎯 Criterios de Éxito Mínimos

✅ **CRÍTICO (Obligatorio):**
- [ ] Código compila sin errores
- [ ] Jerarquía de clases implementada (mínimo 8 clases)
- [ ] Ejemplo de COVARIANZA funcionando
- [ ] Ejemplo de CONTRAVARIANZA funcionando
- [ ] App.java ejecuta y muestra resultados
- [ ] CADA PERSONA documenta SU SECCIÓN del informe
- [ ] Screenshots de ejecución

✅ **IMPORTANTE (Deseable):**
- [ ] Diagrama UML generado
- [ ] Documentación de genéricos completa
- [ ] README completo
- [ ] Código con comentarios básicos
- [ ] Informe con formato profesional

⚠️ **OPCIONAL (Si sobra tiempo):**
- [ ] JavaDoc completo
- [ ] Más de 3 screenshots
- [ ] Ejemplos adicionales

---

## 💡 Tips para Velocidad

### Para todos:
1. **NO over-engineer**: Código simple y funcional
2. **Copy-paste inteligente**: Usa clases similares como template
3. **Commits frecuentes**: Push cada hora
4. **Comunicación rápida**: WhatsApp/Discord para coordinación

### Para Persona 1-3:
- Clases simples: 3-4 atributos máximo
- 1-2 métodos por clase (register + getInfo)
- Constructores automáticos (IDE)
- toString() automático

### Para Persona 4:
- Foco en ejemplos CLAROS y COMENTADOS
- Mejor 2 ejemplos perfectos que 5 mediocres
- Usa System.out.println() generoso para demostrar

### Para Persona 5:
- App.java con salida VISUAL y CLARA
- Screenshots con consola completa visible
- README directo al grano
- Diagrama con herramienta automática
- Escribir SU SECCIÓN del informe (integración)

---

## 📝 IMPORTANTE: Informe Colaborativo

**⚠️ CADA PERSONA ESCRIBE SU PROPIA SECCIÓN DEL INFORME**

### Responsabilidades por persona:

**Persona 1:** Sección "Jerarquía de Clases Base"
- Explicar Participant, Student, Teacher, Administrative

**Persona 2:** Sección "Ponentes y Público"  
- Explicar Presenter, NationalPresenter, InternationalPresenter, Audience

**Persona 3:** Sección "Comisión Organizadora e Invitados"
- Explicar OrganizerCommission, CommissionMember, Guest

**Persona 4:** Sección "Covarianza y Contravarianza" ⚠️ CRÍTICO
- Teoría + ejemplos del código + screenshots

**Persona 5:** Sección "Integración y Ejecución"
- App.java + diagrama UML + screenshots generales

### Estructura del INFORME.md:
```
1. Introducción (1 párrafo conjunto - Persona 5 coordina)
2. Jerarquía Base (Persona 1)
3. Ponentes y Público (Persona 2)
4. Comisión e Invitados (Persona 3)
5. Covarianza/Contravarianza (Persona 4)
6. Integración y Ejecución (Persona 5)
7. Conclusiones (cada uno 1 párrafo)
```

**📝 Persona 5 crea el archivo base y estructura, todos completan su sección**

---

## 🚨 Comandos de Emergencia

```bash
# Compilación rápida
mvn clean compile -q

# Ejecución directa
mvn exec:java -Dexec.mainClass="com.app.App" -q

# Si algo falla, limpiar todo
mvn clean
rm -rf target/

# Generar JavaDoc rápido (para diagrama)
mvn javadoc:javadoc -q

# Ver estructura de clases
find src -name "*.java" -type f

# Contar líneas de código
find src -name "*.java" | xargs wc -l
```

---

