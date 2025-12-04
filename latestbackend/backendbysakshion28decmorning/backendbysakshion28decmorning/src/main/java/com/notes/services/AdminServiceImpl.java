package com.notes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.notes.custom_exception.ApiException;
import com.notes.dto.ApiResponse;
import com.notes.dto.NotesResponseDTO;
import com.notes.entities.Notes;
import com.notes.entities.User;
import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	 private final UserRepository userRepository;
	    private final NoteRepository noteRepository;

	    @Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    @Override
	    public List<Notes> getAllNotes() {
	        return noteRepository.findAll();
	    }

	    @Override
	    public ApiResponse deleteUserById(Long userId) {
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ApiException("User not found!"));

	        userRepository.delete(user);
	        return new ApiResponse("User deleted successfully", "SUCCESS");
	    }

	    @Override
	    public ApiResponse deleteNoteById(Long noteId) {
	        Notes note = noteRepository.findById(noteId)
	                .orElseThrow(() -> new ApiException("Note not found!"));

	        noteRepository.delete(note);
	        return new ApiResponse("Note deleted successfully", "SUCCESS");
	    }

}
