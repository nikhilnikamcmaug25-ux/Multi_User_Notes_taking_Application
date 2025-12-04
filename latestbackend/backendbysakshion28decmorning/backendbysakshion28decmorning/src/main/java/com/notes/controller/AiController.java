package com.notes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:3000")
public class AiController {
    
    @Value("${gemini.api.key}")
    private String geminiApiKey;
    
    private final WebClient webClient = WebClient.create();
    
    @PostMapping("/summarize")
    public ResponseEntity<?> summarizeNote(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        String summary = callGeminiAPI("Summarize this note in 2-3 sentences: " + content);
        return ResponseEntity.ok(Map.of("summary", summary));
    }
    
    @PostMapping("/categorize")
    public ResponseEntity<?> categorizeNote(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        String category = callGeminiAPI("Categorize this note into one of these categories only: Personal, Work, Study, Ideas, Important, Other. Return only the category name: " + content);
        return ResponseEntity.ok(Map.of("category", category.trim()));
    }
    
    private String callGeminiAPI(String prompt) {
        try {
            if (geminiApiKey == null || geminiApiKey.isEmpty()) {
                return getSmartFallback(prompt);
            }
            
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + geminiApiKey;
            
            String jsonBody = String.format("""
                {
                    "contents": [{
                        "parts": [{
                            "text": "%s"
                        }]
                    }]
                }
                """, prompt.replace("\"", "\\\""));
            
            Map<String, Object> response = webClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
            
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            
            return (String) parts.get(0).get("text");
            
        } catch (Exception e) {
            return getSmartFallback(prompt);
        }
    }
    
    private String getSmartFallback(String prompt) {
        String content = prompt.toLowerCase();
        
        if (prompt.contains("Categorize")) {
            
            if (content.contains("work") || content.contains("project") || content.contains("meeting") || content.contains("office")) {
                return "Work";
            } else if (content.contains("study") || content.contains("exam") || content.contains("homework") || content.contains("learn")) {
                return "Study";
            } else if (content.contains("idea") || content.contains("think") || content.contains("concept")) {
                return "Ideas";
            } else if (content.contains("important") || content.contains("urgent") || content.contains("deadline")) {
                return "Important";
            } else {
                return "Personal";
            }
        } else {
            // Smart summarization
            String noteContent = prompt.substring(prompt.indexOf(":") + 1).trim();
            if (noteContent.length() > 100) {
                return noteContent.substring(0, 97) + "...";
            } else {
                return "Summary: " + noteContent;
            }
        }
    }
}
