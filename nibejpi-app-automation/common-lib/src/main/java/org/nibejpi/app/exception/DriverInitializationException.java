package org.nibejpi.app.exception;

@SuppressWarnings("serial")
public class DriverInitializationException extends ConstnatException {

  public DriverInitializationException(String message) {
    super(message);
  }

  public DriverInitializationException(String message, Throwable t) {
    super(message, t);
  }
}