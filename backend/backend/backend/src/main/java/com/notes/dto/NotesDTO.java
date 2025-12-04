package com.notes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotesDTO {
	 private String title;
	    private String content;
	    private Long userId;
}
