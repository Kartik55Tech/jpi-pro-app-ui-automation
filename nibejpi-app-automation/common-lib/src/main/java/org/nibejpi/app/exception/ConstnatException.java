package org.nibejpi.app.exception;

@SuppressWarnings("serial")
public class ConstnatException extends RuntimeException {

  public ConstnatException(String message) {
    super(message);
  }

  public ConstnatException(String message, Throwable t) {
    super(message, t);
  }
}
