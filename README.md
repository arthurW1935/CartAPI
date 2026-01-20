# CartAPI - DevSecOps CI/CD Pipeline

**CartAPI** is a Spring Boot-based project designed to demonstrate a production-grade DevSecOps pipeline. This repository implements a fully automated CI/CD workflow using **GitHub Actions**, **Docker**, and **Kubernetes**.

---

## CI/CD Pipeline Explanation

The workflow is split into two distinct pipelines to ensure a clear separation of concerns: **Continuous Integration (CI)** and **Continuous Deployment (CD)**.

### 1. Continuous Integration (CI) - `ci.yml`

This workflow triggers on every push to the `main` branch. It focuses on code quality, security, and artifact creation.

* **Build & Test:**
* Sets up **Java 17 (Temurin)**.
* Runs **Maven** to compile code and execute Unit Tests.
* **Fail-Fast:** If tests fail, the pipeline stops immediately.


* **Security Scans (DevSecOps):**
* **CodeQL (SAST):** Scans the raw Java source code for security vulnerabilities (e.g., SQL Injection) before compilation.
* **Trivy Image Scan:** Scans the built Docker image for OS-level vulnerabilities (CVEs) in the base image or dependencies.


* **Containerization:**
* Builds an immutable Docker image.
* **Smoke Test:** Runs the container briefly in the CI environment and curls the health endpoint to ensure the application actually starts up.


* **Registry Push:**
* Pushes the validated image to **DockerHub** only if all tests and scans pass.



### 2. Continuous Deployment (CD) - `cd.yml`

This workflow is triggered via `workflow_run`. It listens for the **successful completion** of the CI workflow.

* **Trigger Condition:** It *only* runs if the CI pipeline status is `success`. This ensures that broken code or vulnerable images are never deployed.
* **Deployment:**
* Connects to the Kubernetes cluster using a secure `KUBE_CONFIG`.
* Applies the `Deployment` and `Service` manifests.
* Forces a `rollout restart` to pull the latest image tag from DockerHub.
* Verifies the rollout status to confirm zero-downtime deployment.



---

## Secrets Configuration

To run this pipeline in your own GitHub repository, you must configure the following **GitHub Actions Secrets** (Settings > Secrets and variables > Actions):

| Secret Name | Description | Purpose |
| --- | --- | --- |
| `DOCKERHUB_USERNAME` | Your DockerHub ID | Used to tag and push the image to your registry. |
| `DOCKERHUB_TOKEN` | DockerHub Access Token | A secure alternative to your password. [Get it here](https://hub.docker.com/settings/security). |
| `KUBE_CONFIG` | Kubernetes Config File (Base64) | The raw `~/.kube/config` file content, encoded in Base64. |

### How to Generate `KUBE_CONFIG`

Run this command on your local machine (where you have access to your K8s cluster) to generate the Base64 string:

```bash
cat ~/.kube/config | base64

```

*Copy the output string and paste it into the `KUBE_CONFIG` secret.*

---

## Steps to Run in GitHub

Follow these steps to set up the pipeline in your own repository:

### 1. Fork or Clone

Clone this repository and push it to your own GitHub account.

```bash
git clone https://github.com/arthurW1935/CartAPI.git
cd CartAPI

```

### 2. Configure Secrets

Go to your repository settings on GitHub and add the three secrets listed above (`DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN`, `KUBE_CONFIG`).

### 3. Update Manifests (Optional)

If you are using a different DockerHub username, update the image reference in `k8s/deployment.yaml`:

```yaml
spec:
  containers:
    - name: cartapp
      image: <YOUR_DOCKERHUB_USERNAME>/cartapp:latest  # Update this line

```

### 4. Trigger the Pipeline

Make a change to the code (e.g., update `README.md` or `src/`) and push to the `main` branch.

```bash
git add .
git commit -m "Trigger CI pipeline"
git push origin main

```

### 5. Monitor

1. Go to the **Actions** tab in your repository.
2. Watch the **"Java CI with Maven"** workflow run first.
3. Once CI succeeds, watch the **"Deploy to Kubernetes"** workflow trigger automatically.

---

## Local Development

To run the application locally without GitHub Actions:

**Using Maven:**

```bash
mvn clean install
mvn spring-boot:run

```

**Using Docker:**

```bash
docker build -t cartapp .
docker run -p 8080:8080 cartapp

```
