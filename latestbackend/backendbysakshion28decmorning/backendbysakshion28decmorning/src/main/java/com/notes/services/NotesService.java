package com.notes.services;

import com.notes.dto.NotesDTO;
import com.notes.entities.Notes;

import java.util.List;

public interface NotesService {

    Notes addNote(NotesDTO dto);

    List<Notes> getNotesByUser(Long userId);

    Notes getNoteById(Long id);

    Notes updateNote(Long id, NotesDTO dto);

    String deleteNote(Long id);
}
