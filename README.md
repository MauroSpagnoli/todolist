# TodoList

Aplicación de ejemplo ToDo construida con arquitectura limpia (Clean Architecture), Room, Hilt y Jetpack Compose.

Este README resume la estructura del proyecto, cómo compilarlo y ejecutar, decisiones arquitectónicas y pasos útiles para contribuir.

---

## Resumen

- Nombre: TodoList
- Lenguaje: Kotlin
- Plataforma: Android (Compose)
- Arquitectura: Clean Architecture (capas: domain, data, ui)
- Dependencias destacadas: Room, Hilt, Kotlin Coroutines, Jetpack Compose

---

## Estructura principal del proyecto

- `app/src/main/java/msh/todolist/domain`  
  Contiene la capa de dominio: modelos (`domain.model`), contratos (interfaces de repositorio en `domain.repository`) y casos de uso (`domain.usecases`). Esta capa no depende de la capa `data`.

- `app/src/main/java/msh/todolist/data`  
  Implementación de la capa de datos: entidades Room (`data.local`), DAOs, mappers y la implementación del repositorio (`data.repository`). Aquí vive `TodoEntity` y `AppDatabase`.

- `app/src/main/java/msh/todolist/ui`  
  UI en Jetpack Compose y ViewModels. Consume los modelos de dominio (`domain.model.Todo`) a través de UseCases.

- `app/src/main/java/msh/todolist/di`  
  Módulos de inyección (Hilt): bindings para repositorios, base de datos y dispatchers.

- `app/src/main/res`  
  Recursos (drawables, layouts, mipmaps, strings, etc.).

---

## Principios y decisiones arquitectónicas

- Clean Architecture: la capa `domain` declara contratos y use cases; la capa `data` implementa esos contratos y hace mapping entre entidades de persistencia (`TodoEntity`) y modelos de dominio (`Todo`).
- SOLID: las responsabilidades están separadas (DAO, repositorio, usecases, ViewModel, UI). La inversión de dependencias se satisface mediante interfaces (`ITodoRepository`) y Hilt para la inyección.
- Concurrencia: se inyecta un `CoroutineDispatcher` calificado (`@IoDispatcher`) para ejecutar operaciones de I/O desde los use cases y mantener testabilidad.
- Persistencia: Room con `TodoEntity`, `TodoDao` y `AppDatabase`. Los `UseCases` y `ViewModel` consumen `Flow` para reactividad.

---

## Cómo compilar y ejecutar

Requisitos previos:
- JDK 11 (o compatible con la configuración del proyecto)
- Android SDK (según `compileSdk` del proyecto, en este repo es 34)
- Android Studio recomendado

Desde Windows (cmd.exe) en la raíz del proyecto:

```cmd
:: Limpiar
.\gradlew.bat clean

:: Compilar
.\gradlew.bat :app:assembleDebug

:: Instalar en un emulador/dispositivo conectado
.\gradlew.bat :app:installDebug
```

Para correr tests unitarios:

```cmd
.\gradlew.bat test
```

Para ejecutar lint (informes de calidad):

```cmd
.\gradlew.bat :app:lintDebug
```

---

## Cómo está organizado el DI (Hilt)

- `di/RepositoryModule.kt`: enlaza la implementación concreta `msh.todolist.data.repository.TodoRepository` con el contrato `msh.todolist.domain.repository.ITodoRepository`.
- `di/DatabaseModule.kt`: provee la instancia de `AppDatabase` y `TodoDao` usando `Room.databaseBuilder`.
- `di/DispatcherModule.kt`: provee el `CoroutineDispatcher` calificado `@IoDispatcher` con `Dispatchers.IO`.

Si necesitas añadir nuevos bindings (por ejemplo, repositorios o data sources), añade un `@Module` con `@InstallIn(SingletonComponent::class)` y proporciona el binding correspondiente.

---

## UseCases disponibles

- `GetAllTodosUseCase`: devuelve un `Flow<List<Todo>>` con las tareas.
- `InsertTodoUseCase`: inserta un `Todo` (se lanza en el dispatcher IO).
- `UpdateTodoUseCase`: actualiza un `Todo`.
- `DeleteTodoUseCase`: elimina una tarea por id.

Los use cases están en `msh.todolist.domain.usecases.todo` y se inyectan en los ViewModels.

---

## UI y ViewModels

- `TodoListViewModel`: expone `StateFlow<List<Todo>>` con las tareas y métodos para añadir/actualizar/eliminar.
- Pantallas Compose (`ui/components/todolist`) consumen el ViewModel y muestran la lista, modales de añadir/editar y un snackbar de confirmación.

---

## Icono de la aplicación

El adaptive icon se compone de:
- `res/drawable/ic_launcher_foreground.xml` (vector con la lista y checks)
- `res/drawable/ic_launcher_background.xml` (shape con color de fondo)
- Adaptive launchers en `res/mipmap-anydpi-v26/ic_launcher.xml` y `ic_launcher_round.xml` referencian esos drawables.

Si quieres generar PNGs legacy (mipmap-*/) usa Android Studio → New → Image Asset o solicita que los genere automáticamente.

---

## Recomendaciones para contribuir

1. Crea una rama a partir de `main` o la rama principal:
```bash
git checkout -b feature/mi-cambio
```
2. Haz cambios pequeños y commits atómicos con mensajes claros.
3. Antes de abrir PR:
   - Ejecuta `./gradlew.bat clean assembleDebug` y `./gradlew.bat test`.
   - Asegúrate de que el código sigue la convención del proyecto y añade pruebas cuando sea posible.

---

## Problemas comunes y soluciones

- Hilt no encuentra binding: verifica que el módulo esté anotado con `@Module` y `@InstallIn(SingletonComponent::class)` y que la implementación sea `@Inject` o esté provista.
- Room problemas con migraciones: actualmente la DB está en versión 1; para cambios en entidades, añade migraciones o incrementa la versión y borra la app en desarrollo.
- Icono no se actualiza: desinstala la app del emulador/dispositivo e instala de nuevo.

---

## Próximos pasos recomendados

- Implementar undo (deshacer) para inserciones: propagar el id insertado desde `InsertTodoUseCase` y mostrar acción "Deshacer" en el snackbar.
- Añadir tests unitarios para use cases y test instrumentado para `data.repository` con una DB in-memory.
- (Opcional) separar las capas en módulos Gradle (`:domain`, `:data`, `:app`) para reforzar las dependencias en tiempo de compilación.

---


