package com.notes.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.notes.dto.NotesDTO;
import com.notes.entites.Notes;
import com.notes.entites.User;
import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NotesService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Notes addNote(NotesDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notes note = modelMapper.map(dto, Notes.class);
        note.setUser(user);

        return noteRepository.save(note);
    }

    @Override
    public List<Notes> getNotesByUser(Long userId) {
        return noteRepository.findByUserUserId(userId);
    }

    @Override
    public Notes getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public Notes updateNote(Long id, NotesDTO dto) {
        Notes note = getNoteById(id);

        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        return noteRepository.save(note);
    }

    @Override
    public String deleteNote(Long id) {
        Notes note = getNoteById(id);
        noteRepository.delete(note);
        return "Note deleted successfully";
    }
}

