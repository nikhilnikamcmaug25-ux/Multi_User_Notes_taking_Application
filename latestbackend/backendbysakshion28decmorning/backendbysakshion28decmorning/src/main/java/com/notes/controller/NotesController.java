package com.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dto.NotesDTO;
import com.notes.services.NotesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService noteService;

    @PostMapping("/add")
    public ResponseEntity<?> addNote(@RequestBody NotesDTO dto) {
      
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(noteService.addNote(dto));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getNotesByUser(@PathVariable Long userId) {
        
            return ResponseEntity.ok(noteService.getNotesByUser(userId));

       
      
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        
            return ResponseEntity.ok(noteService.getNoteById(id));

       
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody NotesDTO dto) {
      
            return ResponseEntity.ok(noteService.updateNote(id, dto));

     
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
    
            return ResponseEntity.ok(noteService.deleteNote(id));

        
    }
}

