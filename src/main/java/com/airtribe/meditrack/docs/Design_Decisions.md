# Design Decisions — MediTrack

This document explains the main design choices made to satisfy the assignment rubric: package layout, OOP features, immutability, patterns, data storage, and other trade-offs.

## 1. Package layout
Base package: `com.airtribe.meditrack`

Subpackages:
- `entity` — domain model classes and enums (Person, Doctor, Patient, Appointment, Bill, BillSummary, Specialization, AppointmentStatus).
- `service` — business logic and orchestration (DoctorService, PatientService, AppointmentService).
- `util` — utilities (Validator, DateUtil, CSVUtil, IdGenerator, DataStore, AIHelper).
- `abstracted` — interfaces (`Searchable`, `Payable`). `abstracted` used instead of `interface` because `interface` is a reserved keyword.
- `exception` — custom exceptions (InvalidDataException, AppointmentNotFoundException).
- `test` — manual test harness (TestRunner).
- `docs` — project documentation (JVM report, setup, design decisions).

Rationale: This structure satisfies separation of concerns and maps directly to the rubric.

## 2. Encapsulation and validation
- All entity fields are private; setters/getters provide controlled access.
- `Validator` centralizes validation (names, age, phone, fee).
- Access modifiers: package-private for helper classes where appropriate; public for service APIs.

## 3. Inheritance and constructor chaining
- `Person` is the base class for `Doctor` and `Patient` (demonstrates inheritance).
- `MedicalEntity` is an abstract base that provides `id` and `createdAt` and forces a `deepCopy()` method for Cloneable-like behavior.
- Constructors use `super(...)` to perform proper initialization and constructor chaining.

## 4. Polymorphism
- Overloaded methods in services: `searchPatient(String)` and `searchPatient(int)` demonstrate compile-time polymorphism (method overloading).
- `Appointment` implements `Payable` and overrides `generateBill()` to demonstrate runtime polymorphism/dynamic dispatch.
- Interfaces with default methods support partial implementations.

## 5. Abstraction
- Interfaces in `abstracted` define behavior (`Searchable`, `Payable`). Default methods provide common utilities (e.g., `containsIgnoreCase`).
- `MedicalEntity` abstracts common properties and requires subclasses to implement deep copy semantics.

## 6. Advanced OOP
- `Patient` and `Appointment` provide deep-copy constructors; `deepCopy()` is used to prevent shared mutable state.
- `BillSummary` is immutable: all fields are `final` and no setters are exposed.
- Enums: `Specialization` and `AppointmentStatus` capture fixed domain values.
- Static initialization blocks are used for constants (e.g., `Constants` and `IdGenerator`).

## 7. Data storage and generics
- `DataStore<T>` is a generic, in-memory store that provides:
    - list storage for ordered retrieval
    - map-backed keyed lookup for fast access by id
    - simple CRUD operations and predicate search
- This fits the rubric's requirement for a generic `DataStore<T>` and demonstrates generics usage.

## 8. Error handling
- Custom exceptions (`InvalidDataException`, `AppointmentNotFoundException`) make error conditions explicit and descriptive.
- `Validator` throws `InvalidDataException` on invalid input to keep validation consistent.

## 9. Design patterns
- Singleton: `IdGenerator` exposes both eager (`eagerInstance()`) and lazy (`lazyInstance()` via holder class) singleton accessors.
- Observer: `AppointmentService` includes a listener interface and notifies listeners when appointments are created or cancelled.
- Factory (suggested/extendable): billing creation logic in `Appointment.generateBill()` acts as a simple factory for `Bill`. The pattern can be extended into a dedicated factory class for multiple bill types.
- These patterns are intentionally lightweight to demonstrate understanding without introducing unnecessary complexity.

## 10. Streams and lambdas
- Services use streams for analytics:
    - Filtering doctors by `Specialization`
    - Calculating average consultation fee
    - Grouping appointments per doctor via `Collectors.groupingBy`
- This demonstrates modern Java idioms and functional-style processing.

## 11. Why BillSummary is immutable
- Immutable `BillSummary` ensures that once a bill is produced it cannot be mutated accidentally. This simplifies reasoning about billing and improves thread-safety.

## 12. Notes on CLI design (Main.java)
- `Main` is a simple menu-driven console UI demonstrating CRUD flows, appointment creation/cancellation, billing, and analytics.
- Input parsing uses basic validation and will throw descriptive exceptions for invalid inputs; these are intended for demonstration rather than production-grade input resilience.

## 13. Trade-offs and future improvements
- Persistence: The project uses in-memory storage (`DataStore`); a next step would be adding a simple file-based CSV persistence (using `CSVUtil`) or integrating a database.
- Concurrency: DataStore is not thread-safe by default; for concurrent access, wrap collections or use concurrent collections (e.g., `ConcurrentHashMap`, `CopyOnWriteArrayList`).
- Tests: The repo includes a manual `TestRunner` for simplicity; adding JUnit tests would improve maintainability.

## 14. Summary
The project emphasizes clean OOP design, generics, interfaces, design patterns, streams, and an immutable model for derived data — all mapped directly to the rubric's grading criteria.
