# TodoList App

A modern Android todo list application built with **Clean Architecture**, **Room**, **Hilt**, and **Jetpack Compose**. This app demonstrates best practices in Android development including proper separation of concerns, dependency injection, and reactive programming patterns.

## 📱 What does this app do?

TodoList is a task management application that allows users to:

- **Create tasks**: Add new todo items with custom titles and descriptions
- **Mark as complete**: Toggle tasks between completed and pending states
- **Edit tasks**: Update existing todo items with new information
- **Delete tasks**: Remove unwanted tasks from the list
- **Persistent storage**: All data is saved locally using Room database
- **Real-time updates**: UI automatically updates when data changes using Flow and StateFlow
- **Material Design**: Modern UI following Material Design 3 guidelines

### Key Features

✅ **Add new todos** with title and optional description  
✅ **Mark todos as completed** with checkbox interaction  
✅ **Edit existing todos** through dedicated edit screen  
✅ **Delete todos** with confirmation  
✅ **Offline-first** - works without internet connection  
✅ **Real-time sync** between database and UI  
✅ **Clean, intuitive interface** built with Jetpack Compose  

---

## 🏗️ Architecture Overview

This project follows **Clean Architecture** principles with three distinct layers:

```
📁 UI Layer (Presentation)
   ├── Jetpack Compose screens
   ├── ViewModels with StateFlow
   └── UI state management

📁 Domain Layer (Business Logic)
   ├── Use Cases (business rules)
   ├── Repository interfaces
   └── Domain models (Todo)

📁 Data Layer (Infrastructure)
   ├── Room database implementation
   ├── Data entities (TodoEntity)
   └── Repository implementation
```

### Why Clean Architecture?

- **Separation of Concerns**: Each layer has a single responsibility
- **Testability**: Easy to unit test business logic independently
- **Maintainability**: Changes in one layer don't affect others
- **Scalability**: Easy to add new features and data sources

---

## 🛠️ Tech Stack

| Component | Technology | Purpose |
|-----------|------------|---------|
| **UI** | Jetpack Compose | Modern declarative UI framework |
| **Architecture** | MVVM + Clean Architecture | Separation of concerns |
| **Database** | Room | Local SQLite database with type safety |
| **Dependency Injection** | Hilt | Compile-time dependency injection |
| **Async** | Kotlin Coroutines + Flow | Reactive programming |
| **Language** | Kotlin | Modern Android development language |

---

## 📂 Project Structure

```
app/src/main/java/msh/todolist/
├── 🎯 domain/                 # Business logic layer
│   ├── model/                 # Domain models (Todo)
│   ├── repository/            # Repository contracts  
│   └── usecases/             # Business use cases
│       └── todo/             # Todo-specific use cases
├── 💾 data/                  # Data access layer  
│   ├── local/                # Room database components
│   │   ├── TodoEntity.kt     # Database entity
│   │   ├── TodoDao.kt        # Data access object
│   │   └── AppDatabase.kt    # Room database
│   └── repository/           # Repository implementations
├── 🎨 ui/                    # Presentation layer
│   ├── components/           # Reusable UI components
│   ├── screens/              # Screen composables
│   └── viewmodels/           # ViewModels
└── 🔧 di/                    # Dependency injection modules
    ├── DatabaseModule.kt     # Database bindings
    ├── RepositoryModule.kt   # Repository bindings  
    └── DispatcherModule.kt   # Coroutine dispatchers
```

---

## 🚀 Getting Started

### Prerequisites

- **JDK 11** or higher
- **Android SDK** (API level 34)
- **Android Studio** (recommended IDE)

### Building the Project

From the project root directory (Windows cmd.exe):

```cmd
:: Clean the project
.\gradlew.bat clean

:: Build debug APK
.\gradlew.bat :app:assembleDebug

:: Install on connected device/emulator
.\gradlew.bat :app:installDebug
```

### Running Tests

```cmd
:: Run unit tests
.\gradlew.bat test

:: Run lint checks
.\gradlew.bat :app:lintDebug

:: Run all checks
.\gradlew.bat check
```

---

## 🧩 Dependency Injection (Hilt)

The app uses **Hilt** for dependency injection with the following modules:

### DatabaseModule
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    // Provides Room database instance
    // Provides TodoDao
}
```

### RepositoryModule  
```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // Binds TodoRepository implementation to ITodoRepository interface
}
```

### DispatcherModule
```kotlin
@Module
@InstallIn(SingletonComponent::class) 
object DispatcherModule {
    // Provides IO dispatcher for database operations
}
```

---

## 📋 Use Cases

The domain layer contains the following use cases for todo operations:

| Use Case | Purpose | Returns |
|----------|---------|---------|
| `GetAllTodosUseCase` | Fetch all todos | `Flow<List<Todo>>` |
| `InsertTodoUseCase` | Add new todo | `Unit` |  
| `UpdateTodoUseCase` | Update existing todo | `Unit` |
| `DeleteTodoUseCase` | Delete todo by ID | `Unit` |

### Example Usage in ViewModel:

```kotlin
class TodoListViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    // ... other use cases
) : ViewModel() {
    
    val todos: StateFlow<List<Todo>> = getAllTodosUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        
    fun addTodo(title: String, description: String) {
        viewModelScope.launch {
            insertTodoUseCase(Todo(title = title, description = description))
        }
    }
}
```

---

## 🎨 UI Components

### TodoListScreen
- **Purpose**: Main screen displaying list of todos
- **Features**: Add new todo, mark as complete, edit, delete
- **State**: Observes `StateFlow<List<Todo>>` from ViewModel

### AddEditTodoScreen  
- **Purpose**: Screen for adding new todos or editing existing ones
- **Features**: Text inputs for title/description, save/cancel actions
- **Navigation**: Navigates back to list on save/cancel

### TodoItem
- **Purpose**: Individual todo item component  
- **Features**: Checkbox, title/description display, edit/delete actions
- **Interactions**: Toggle completion, trigger edit/delete

---

## 💾 Data Layer Details

### TodoEntity (Room Entity)
```kotlin
@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val createdAt: Long
)
```

### Todo (Domain Model)
```kotlin  
data class Todo(
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
```

### Data Mapping
The repository layer handles mapping between `TodoEntity` (data) and `Todo` (domain):
- **From Entity to Domain**: Database → Business logic
- **From Domain to Entity**: Business logic → Database

---

## 🎯 App Icon

The app uses **Adaptive Icons** with:
- `ic_launcher_foreground.xml`: Vector drawable with todo list icon
- `ic_launcher_background.xml`: Solid color background  
- Supports various device icon shapes (circle, square, rounded square)

---

## 🔧 Development Workflow

### Adding New Features

1. **Create feature branch**:
   ```bash
   git checkout -b feature/new-feature-name
   ```

2. **Follow Clean Architecture layers**:
   - Add domain models/use cases if needed
   - Implement data layer changes  
   - Create/update UI components
   - Add dependency injection bindings

3. **Test your changes**:
   ```cmd
   .\gradlew.bat test
   .\gradlew.bat :app:lintDebug
   ```

4. **Commit and push**:
   ```bash
   git commit -m "feat: add new feature description"
   git push origin feature/new-feature-name
   ```

### Code Style Guidelines

- Follow **Kotlin coding conventions**
- Use **meaningful variable/function names**  
- Add **KDoc comments** for public APIs
- Keep functions **small and focused**
- Prefer **composition over inheritance**

---

## 🐛 Common Issues & Solutions

### Hilt Issues
**Problem**: `@Inject` constructor not found  
**Solution**: Ensure the class has `@Inject` constructor and proper module bindings

### Room Issues  
**Problem**: Database migration errors  
**Solution**: For development, uninstall app or increment database version

### Build Issues
**Problem**: Gradle sync failures  
**Solution**: Clean project and invalidate caches in Android Studio

---

## 🚀 Future Enhancements

### Planned Features
- [ ] **Undo functionality** for delete operations
- [ ] **Categories/Tags** for organizing todos  
- [ ] **Due dates** with notifications
- [ ] **Search and filter** capabilities
- [ ] **Export/Import** todo lists
- [ ] **Dark theme** support
- [ ] **Widgets** for home screen

### Technical Improvements  
- [ ] **Multi-module architecture** (`:domain`, `:data`, `:app`)
- [ ] **Comprehensive unit tests** for all layers
- [ ] **UI tests** with Compose testing framework
- [ ] **CI/CD pipeline** with GitHub Actions
- [ ] **Performance monitoring** with Firebase Performance

---

## 📄 License

This project is for educational purposes and demonstrates modern Android development practices.

---

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch  
3. Make your changes following the code style
4. Add tests for new functionality
5. Submit a pull request with clear description

For questions or suggestions, please open an issue on GitHub.

---

**Built with ❤️ using Android modern development stack**
