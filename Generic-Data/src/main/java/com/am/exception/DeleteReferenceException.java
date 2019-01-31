package com.am.exception;

import java.util.ArrayList;
import java.util.List;

public class DeleteReferenceException extends Exception {

	// Parameterless Constructor
	private List<String> references = new ArrayList<>();

	public DeleteReferenceException() {
		references = new ArrayList<>();
	}

	// Constructor that accepts a message
	public DeleteReferenceException(String message) {
		super(message);
		references = new ArrayList<>();
	}

	public List<String> getReferences() {
		return references;
	}

	public void addReference(String reference) {
		references.add(reference);
	}

	public void setReferences(List<String> references) {
		this.references = references;
	}

}
