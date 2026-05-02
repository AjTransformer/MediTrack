2) JVM_Report.md
   Path: `src/main/java/com/airtribe/meditrack/docs/JVM_Report.md` (recommended: move to `docs/JVM_Report.md`)

Contents:
```markdown
# JVM Report — MediTrack

This report covers JVM internals relevant for the MediTrack assignment: ClassLoader, memory areas (Heap/Stack/Method Area/PC Register), the Execution Engine, JIT vs Interpreter, and WORA.

## 1. ClassLoader subsystem
Java's ClassLoader subsystem is responsible for loading class bytecode into the JVM so it can be executed.

- Bootstrap ClassLoader: Loads core JDK classes (rt.jar / platform classes).
- Platform (or Extension) ClassLoader: Loads platform libraries.
- Application (System) ClassLoader: Loads classes from the application classpath, e.g., `com.airtribe.meditrack.*`.

At runtime, when the JVM needs `com.airtribe.meditrack.Main`, the Application ClassLoader locates the compiled class file and loads it into the Method Area.

## 2. JVM memory areas

- Heap
  - Stores all objects and arrays.
  - Shared among all threads.
  - Entities such as `Doctor`, `Patient`, `Appointment`, and `Bill` live here.
  - Garbage-collected when no live references remain.

- Stack (per-thread)
  - Each thread has its own JVM stack.
  - Contains method frames, local primitives and references, and operand stacks.
  - Short-lived local variables (e.g., loop counters) live here.

- Method Area (also referred to as "PermGen" in older JVMs or part of Metaspace)
  - Stores class metadata, static variables, method bytecode and runtime constant pool.
  - Holds class definitions for `Doctor`, `Patient`, `Appointment`, `DataStore` etc.

- Program Counter (PC) Register
  - Holds the address of the current JVM instruction for each thread.
  - Helps execution resume after interrupts and context switches.

## 3. Execution Engine
The Execution Engine executes bytecode loaded into the JVM. Key components:

- Interpreter
  - Reads and executes bytecode instructions one by one.
  - Low startup overhead but lower throughput for hot code.

- JIT (Just-In-Time) Compiler
  - Identifies hot (frequently executed) methods and compiles them into native code to improve performance.
  - Over time, long-running applications benefit from JIT-compiled code.

- Garbage Collector
  - Manages reclaiming memory for unreachable objects on the heap.
  - Different GC algorithms (G1, Parallel, CMS, Shenandoah, ZGC) offer trade-offs.

In MediTrack, repeated operations (searching, billing calculations, appointment processing) may be JIT-compiled by the JVM, improving throughput.

## 4. JIT vs Interpreter
- Interpreter: Good for quick startup; executes bytecode directly.
- JIT: Improves performance by compiling hot code paths to native instructions; introduces compilation overhead but speeds up repeated execution.

MediTrack (a short CLI app) will see most benefit on repeated operations (e.g., bulk analytics) when run for longer periods.

## 5. WORA (Write Once Run Anywhere)
- Java bytecode is platform independent; the same .class files run on any OS with a compatible JVM.
- MediTrack compiled on Windows can run unchanged on Linux/macOS JVMs.
- Platform-specific behavior is limited to OS APIs (file paths, newline differences, native libs). Use Java standard APIs to maximize portability.

## 6. Practical pointers for MediTrack
- Keep heavy short-lived objects to a minimum; reuse DataStore and collections to avoid pressure on the GC.
- Use immutable value objects for thread-safety (e.g., `BillSummary`).
- Use streams and lambdas for concise analytics; they are JIT-friendly when executed repeatedly.

## 7. Summary
Understanding how the JVM loads classes, manages memory, and optimizes code (JIT) helps design performant, maintainable Java applications. MediTrack's design focuses on clear separation of responsibilities (entities, services, utilities), safe immutability for derived data (bill summaries), and light-weight in-memory storage — all choices that map well to JVM behavior.
