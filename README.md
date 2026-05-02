# MediTrack

Small educational clinic management console app demonstrating Java OOP, design patterns and modern Java features.  
This repository implements an in-memory clinic system with Patients, Doctors, Appointments and Billing designed to map directly to the assignment rubric.

---

## Quick plan / checklist
- [ ] Confirm Java 17+ is installed
- [ ] Install Maven or run from IDE if you prefer
- [ ] Build with `mvn clean compile`
- [ ] Run the interactive console `Main` or the manual `TestRunner`
- [ ] (Optional) Move docs from `src/main/java/.../docs/` to a root-level `docs/` folder for submission

---

## Features implemented
- Package layout under `com.airtribe.meditrack` with subpackages: `entity`, `service`, `util`, `abstracted`, `exception`, `test`, `constants`.
- Entities: `Person`, `Doctor`, `Patient`, `Appointment`, `Bill`, `BillSummary` (immutable).
- Abstract base `MedicalEntity` and `abstracted` interfaces `Searchable` and `Payable`.
- Enums: `Specialization`, `AppointmentStatus`.
- Generic `DataStore<T>` — in-memory store supporting CRUD and predicate searches.
- Services: `DoctorService`, `PatientService`, `AppointmentService` (CRUD, search, generate/cancel appointments).
- Billing: `Appointment.generateBill()` uses a tax constant from `Constants`.
- Validation: centralized `Validator`.
- Design patterns:
  - Singleton (eager and lazy) in `IdGenerator`
  - Observer-like listeners in `AppointmentService` for notifications
  - Streams + lambdas for analytics and filtering
- Utilities: `DateUtil`, `CSVUtil`, `AIHelper` (placeholder).
- Manual test runner: `com.airtribe.meditrack.test.TestRunner`
- Menu-driven CLI: `com.airtribe.meditrack.Main`

---

## Project structure (major files)
