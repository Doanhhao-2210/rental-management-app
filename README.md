# Rental Management App - Team H

## Project Week 4 - Model-View-Controller Architecture

The application is refactored into a simple MVC structure while preserving the five existing domain classes and their Week 3 validation rules.

### Architecture

- **Model** (`model/`): `User`, `PropertyOwner`, `Property`, `Tenant`, and `RentalContract`. These classes hold application data and business rules. They do not read console input or print menus.
- **Controller** (`controller/RentalController.java`): exposes `createProperty`, `rentProperty`, and `terminateContract`. It delegates to the existing model methods instead of duplicating validation.
- **View** (`view/ConsoleView.java`): displays the menu, reads user input, calls controller methods, and displays success or error messages. It does not directly create contracts or change availability.
- **Entry point** (`Main.java`): creates the model objects, controller, and view, then launches the menu.

Expected request flow: User -> ConsoleView -> RentalController -> Model. The result returns through the controller to the view.

### Run locally (Java 17+)

```bash
javac -d out model/*.java controller/*.java view/*.java *.java
java -cp out ValidationTests
java -cp out MvcIntegrationTests
java -cp out Main
```

### Demonstrated behavior

- Create a property: availability is `true`.
- Rent the property: availability becomes `false`.
- Terminate the contract: availability returns to `true`.
- Invalid rental rates, invalid dates, and renting an unavailable property are rejected by the Model. The View displays the error and continues running without corrupting stored state.

GitHub Actions compiles the application and runs both validation and MVC integration tests on pushes and pull requests targeting `main`.
