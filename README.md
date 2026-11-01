# Rental Management App - Team H

## Project Week 3 - Reliable Engineering

This Java application contains five core classes: `User`, `PropertyOwner`,
`Property`, `Tenant`, and `RentalContract`. Week 3 adds input validation while
keeping existing data unchanged when an operation fails.

Validation uses regular expressions for names and email, numeric checks for IDs
and rental rates, and `LocalDate` comparisons for contract dates. Java's typed
`double` parameters prevent callers from passing strings or booleans as rental
rates. Validation failures throw exceptions before objects or lists are changed.

### Run locally (Java 17+)

```bash
javac *.java
java ValidationTests
java Main
```

The automated GitHub Actions workflow runs the validation checks and example
program on pull requests into `main` and pushes to `main`.

### Week 3 checklist

- [x] Week 2 classes merged into `main`
- [x] New feature branch created from updated `main`
- [x] Input validation and regression checks added
- [x] Automated checks configured for pull requests
- [ ] Pull request reviewed and merged into `main`
- [ ] Kanban board updated to reflect the merged work
