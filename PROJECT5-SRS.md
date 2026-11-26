# Group Project 5 — Software Requirements Specification
Team H — Rental Management App

Requirements are based on the Week 4 MVC models and ValidationTests.java. These are user-centered requirements, not a new implementation.

## US-01 — User updates profile (User)
As a registered user, I want to update my name and email address, so that my contact information stays accurate.

- AC1 (valid): Given user ID 2 with name Anna Nguyen and email anna@example.com, when the user updates the profile to Minh Tran and minh@example.com, then the full name and email reflect the new values and user ID remains 2.
- AC2 (invalid): Given that profile, when the user submits email "bad-email", then the update is rejected and the existing name and email remain unchanged.
- AC3 (invalid): Given user ID 0 and otherwise valid profile details, when a user record is created, then creation is rejected.

## US-02 — Owner creates a rental property (PropertyOwner)
As a property owner, I want to register a property with a description and monthly rent, so that it can be offered for rental.

- AC1 (valid): Given an owner with no properties, when the owner creates property ID 101, description "Apartment A101" and rental rate 5000, then OwnedProperties contains the new property and its availability is true.
- AC2 (invalid): Given an owner with one property, when the owner attempts to create property ID 102 with rental rate 0, then the request is rejected and OwnedProperties still contains only the original property.

## US-03 — Owner updates property details (Property)
As a property owner, I want to update a property's description and rental rate, so that its rental information remains accurate.

- AC1 (valid): Given property 101 with description "Apartment A101" and rental rate 5000, when the owner updates the description to "Furnished Apartment A101" and rate to 5500, then the stored description and rate reflect the new values.
- AC2 (invalid): Given property 101 with rate 5500, when the owner attempts to set the rate to -50, then the update is rejected and the rate remains 5500.
- AC3 (invalid): Given property 101 with description "Furnished Apartment A101", when the owner submits a blank description, then the update is rejected and the description remains unchanged.

## US-04 — Tenant rents an available property (Tenant)
As a tenant, I want to rent an available property for a specified period, so that I can secure accommodation and keep a record of my rental.

- AC1 (valid): Given tenant ID 2 with no contracts and available property 101, when the tenant rents it under contract ID 1001 from 2026-12-01 to 2027-11-30, then an active contract is added to RentalContracts and the property becomes unavailable.
- AC2 (invalid): Given property 101 is already rented and the tenant has one contract, when the tenant requests another contract ID 1002 for that property, then the request is rejected, the contract list remains unchanged and the property remains unavailable.

## US-05 — Manage rental contract lifecycle (RentalContract)
As a property owner, I want rental contracts to record agreed rental terms and be terminable, so that rental obligations and property availability remain accurate.

- AC1 (valid): Given available property 200 with rental rate 1000 and tenant ID 3, when contract ID 1001 is created for 2026-12-01 to 2027-11-30, then it is active, records rent amount 1000 and marks the property unavailable.
- AC2 (invalid): Given an available property, when a contract is requested with start date 2027-11-30 and end date 2026-12-01, then creation is rejected and the property remains available.
- AC3 (termination): Given active contract 1001 and its unavailable property, when the contract is terminated, then the contract becomes inactive and the property becomes available.
- AC4 (agreed rent): Given an active contract with rent amount 1000, when the property's rental rate is updated to 1200, then the existing contract's rent amount remains 1000.

## Trello synchronization checklist
- [ ] Review the title and description of every existing Trello card.
- [ ] Update the five existing domain cards using the corresponding stories and acceptance criteria above; avoid duplicate cards.
- [ ] Review other existing cards, including MVC tasks, and preserve their implementation history.
- [ ] Confirm actual assigned members with the team and update each card.
- [ ] Verify Kanban status against actual progress; move only completed work to Done.

The Trello board has not been verified or updated from GitHub. Assignment and status must be confirmed in Trello by the team.
