# CitiesSearch

## Setup
It's necessary to add your Google maps key on strings.xml

## Architecture
The app was developed in the base of the Clean Architecture.

### Arch (Android module)
Just boilerplate code to support the architecture.

### App (Android module)
UI implementation and Use cases(Interactors).

### Data (Java module)
Responsible for performing and providing the data.
This layer is always executed in background by a thread pool.

### Model (Java module)
POJO classes.
