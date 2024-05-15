package org.nibejpi.app.exception;

@SuppressWarnings("serial")
public class JsonFileUsageException extends ConstnatException {

  public JsonFileUsageException(String message) {
    super(message);
  }

  public JsonFileUsageException(String message, Throwable t) {
    super(message, t);
  }
}
