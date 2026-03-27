# spring-boot-api

Spring Boot 4.0.4 / Java 25 REST API with PostgreSQL persistence, deployed to GKE via ArgoCD.

## Repository layout

```
.
├── Dockerfile          # Multi-stage build (eclipse-temurin:25 → busybox)
├── pom.xml             # Maven config (Spring Boot 4.0.4, JPA, Lombok, PostgreSQL)
├── src/                # Application source code
│   ├── main/java/com/example/demo/
│   │   ├── controller/ # REST endpoints (/api/v1, /service)
│   │   ├── service/    # Business logic
│   │   ├── repository/ # Spring Data JPA
│   │   ├── model/      # JPA entities + DTOs
│   │   └── mapper/     # Entity ↔ DTO conversion
│   └── main/resources/
│       └── application.yaml
└── mvnw / mvnw.cmd     # Maven wrapper
```

## API endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/v1/create` | Create a test record |
| GET | `/api/v1/get` | List all records |
| GET | `/service/health` | Health check |
| GET | `/service/shutdown` | Graceful shutdown (used by K8s preStop hook) |

## Related repositories

| Repository | Purpose |
|------------|---------|
| [helm-charts](https://github.com/Arshelin/helm-charts) | Helm chart, per-environment values (dev/prod) |
| [terraform-iac](https://github.com/Arshelin/terraform-iac) | GCP infrastructure, Cloud Build pipelines (inline), ArgoCD config |

## CI/CD

Pipelines are defined **inline in Terraform** (`terraform-iac/modules/cloud-build/main.tf`), not in this repo.

- **Dev**: push to `main` → build image → tag `latest` + `SHORT_SHA` → ArgoCD Image Updater auto-syncs
- **Prod**: push to `release/X.Y.Z` → build → vuln scan → tag app repo → update helm-charts `values/prod.yaml` → ArgoCD syncs

## Local development

```bash
./mvnw spring-boot:run
```

Requires PostgreSQL and env vars: `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_URL`.
