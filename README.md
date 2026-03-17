# 🚀 AI Resume & LinkedIn Builder - Backend

## 📌 Overview

This is the backend service of the AI Resume Builder application built using **Spring Boot + Spring AI**.

It integrates multiple AI providers like **Groq** and **OpenRouter** to generate:

* Professional Resume Content
* LinkedIn Headline & About
* ATS Optimized Resume
* Recruiter Outreach Messages

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot 3.4+
* Spring AI
* Groq API
* OpenRouter API
* Spring Security + JWT
* MySQL
* Maven

---

## ✨ Features

### 🤖 AI Features

* Resume Generator
* LinkedIn Profile Generator
* ATS Score Analyzer
* Job Description (JD) Tailoring
* Multi-model AI comparison (Groq vs OpenRouter)

### 📄 Export Features

* PDF Export
* DOCX Export
* Markdown Resume Download

### 🔐 Security

* JWT Authentication
* Login / Signup
* Protected APIs

### 📊 Data Features

* Resume History stored in MySQL
* User-based history tracking

---

## 🏗️ Project Structure

```
controller/
service/
config/
dto/
entity/
repository/
exception/
```

---

## 🔑 Environment Variables

Create `.env` or set system variables:

```
GROQ_API_KEY=your_groq_api_key
OPENROUTER_API_KEY=your_openrouter_api_key
JWT_SECRET=your_secret_key
```

---

## ⚙️ Configuration (application.yml)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ai_resume_builder
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update

  ai:
    openai:
      api-key: ${GROQ_API_KEY}
      base-url: https://api.groq.com/openai
```

---

## ▶️ Run Backend

```bash
mvn clean install
mvn spring-boot:run
```

Server runs on:

```
http://localhost:8080
```

---

## 📡 API Endpoints

### 🔐 Auth

```
POST /api/auth/register
POST /api/auth/login
```

### 🤖 AI

```
POST /api/resume/generate
POST /api/resume/compare
POST /api/resume/markdown
POST /api/resume/ats-score
```

### 📄 Export

```
POST /api/export/pdf
POST /api/export/docx
```

### 📊 History

```
GET /api/history
```

---

## 📌 Example Request

```json
{
  "provider": "groq",
  "fullName": "Priyanshu Vishwakarma",
  "targetRole": "Java Backend Developer",
  "skills": "Java, Spring Boot, Kafka",
  "experience": "Built backend apps",
  "projects": "AI Resume Builder",
  "education": "B.Tech",
  "jobDescription": "Looking for backend developer"
}
```

---

## 🧠 Architecture

```
Controller → Service → AI Provider (Groq/OpenRouter)
                         ↓
                     Response Parser
                         ↓
                     MySQL Storage
```

---

## 🚀 Future Enhancements

* Redis caching
* Rate limiting
* Streaming responses
* Multi-language support

---

## 📄 License

MIT License
