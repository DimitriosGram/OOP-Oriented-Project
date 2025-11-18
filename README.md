# LogAI – DevOps Log Analysis System

LogAI is a Spring Boot application designed to help DevOps/SRE teams analyse logs from different systems such as microservices, Kubernetes, Nginx, and CI/CD pipelines.  
The system can upload logs, retrieve them, and pass them through AI services for analysis, summaries, and fix recommendations.

## Project Structure

```
src/main/java/com/mthree/logai/

controller/          → REST API layer (Dimitrios)
dto/                 → Request/Response objects
model/               → Internal domain models
service/
    ai/              → AI integration (Member 2)
    storage/         → Log storage & parsing (Member 3)
    intelligence/    → Alert grouping & incident timeline (Member 4)
utils/               → Support utilities
```

## Implemented API Endpoints

### Logs
- POST /api/logs/upload – Upload raw log text  
- GET /api/logs/{id} – Retrieve stored log content  

### AI
- POST /api/ai/analyze – Root-cause analysis  
- POST /api/ai/summarise-incident – Log summary  
- POST /api/ai/recommend-fix – Fix recommendations  

These endpoints currently return placeholder responses until the service modules are finished.

## Team Responsibilities

**Dimitrios**  
- Set up Spring Boot structure  
- Implemented all controllers  
- Created DTOs and API contract  
- Integrated placeholder responses  

**Member 2 (AI)**  
- HuggingFace API integration  
- AI prompt design  
- Root-cause, summary, and fix logic  

**Member 3 (Log Storage)**  
- Log parsing and file handling  
- Data models for raw and structured logs  

**Member 4 (Intelligence)**  
- Alert grouping  
- Timeline generation  
- Severity and impact detection  

## How to Run

```
mvn clean install
mvn spring-boot:run
```

You can test endpoints using Postman or curl.
