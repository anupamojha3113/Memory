package com.example.memory.exception;

public class UserCreationFailedException extends RuntimeException {
  public UserCreationFailedException(String message) {
    super(message);
  }
}
