package com.notes.services;

import java.util.List;

import com.notes.dto.ApiResponse;
import com.notes.entities.Notes;
import com.notes.entities.User;

public interface AdminService {


	    List<User> getAllUsers();

	    List<Notes> getAllNotes();

	    ApiResponse deleteUserById(Long userId);

	    ApiResponse deleteNoteById(Long noteId);


}
