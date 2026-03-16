ai-resume-builder/
│
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/example/airesume/
│       ├── AiResumeBuilderApplication.java
│       ├── config/
│       │   ├── GroqConfig.java
│       │   ├── OpenRouterConfig.java
│       │   ├── SecurityConfig.java
│       │   └── JwtFilter.java
│       ├── controller/
│       │   ├── AuthController.java
│       │   ├── ResumeController.java
│       │   ├── ExportController.java
│       │   └── HistoryController.java
│       ├── dto/
│       │   ├── AuthRequest.java
│       │   ├── AuthResponse.java
│       │   ├── ResumeRequest.java
│       │   ├── ResumeResponse.java
│       │   ├── SectionResponse.java
│       │   ├── CompareResponse.java
│       │   ├── AtsRequest.java
│       │   └── AtsResponse.java
│       ├── entity/
│       │   ├── User.java
│       │   └── ResumeHistory.java
│       ├── repository/
│       │   ├── UserRepository.java
│       │   └── ResumeHistoryRepository.java
│       ├── service/
│       │   ├── ResumeAiService.java
│       │   ├── PromptBuilderService.java
│       │   ├── CompareAiService.java
│       │   ├── ExportService.java
│       │   ├── AtsAnalyzerService.java
│       │   ├── JwtService.java
│       │   ├── CustomUserDetailsService.java
│       │   └── AuthService.java
│       └── exception/
│           └── GlobalExceptionHandler.java
│
└── frontend/
    └── src/
        ├── App.jsx
        ├── context/
        │   ├── ThemeContext.jsx
        │   └── AuthContext.jsx
        ├── pages/
        │   ├── LoginPage.jsx
        │   ├── RegisterPage.jsx
        │   └── DashboardPage.jsx
        ├── components/
        │   ├── ThemeToggle.jsx
        │   ├── TemplateSelector.jsx
        │   ├── ComparePanel.jsx
        │   ├── HistoryPanel.jsx
        │   ├── AtsPanel.jsx
        │   ├── CopyButton.jsx
        │   └── DownloadButtons.jsx
        └── api/
            ├── authApi.js
            └── resumeApi.js
