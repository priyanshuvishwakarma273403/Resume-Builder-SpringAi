package com.springAi.ResumeBuilder.service;

import com.springAi.ResumeBuilder.dto.AtsRequest;
import com.springAi.ResumeBuilder.dto.AtsResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AtsAnalyzerService {

    public AtsResponse analyze(AtsRequest request) {
        Set<String> jdWords = tokenize(request.getJobDescription());
        Set<String> resumeWords = tokenize(request.getResumeText());

        Set<String> matched = new HashSet<>(jdWords);
        matched.retainAll(resumeWords);

        Set<String> missing = new HashSet<>(jdWords);
        missing.removeAll(resumeWords);

        int score = jdWords.isEmpty() ? 0 : (matched.size() * 100 / jdWords.size());

        String strengths = "Matched keywords: " + matched.stream().limit(20).collect(Collectors.joining(", "));
        String improvements = "Improve measurable achievements, role alignment, and include more JD-specific keywords.";
        String missingKeywords = missing.stream().limit(20).collect(Collectors.joining(", "));

        return new AtsResponse(score, strengths, improvements, missingKeywords);
    }

    private Set<String> tokenize(String text) {
        if (text == null) return Set.of();
        return Arrays.stream(text.toLowerCase()
                        .replaceAll("[^a-z0-9 ]", " ")
                        .split("\\s+"))
                .filter(word -> word.length() > 2)
                .collect(Collectors.toSet());
    }

}
