package org.nibejpi.app.exception;

@SuppressWarnings("serial")
public class PropertyFileUsageException extends ConstnatException {

  public PropertyFileUsageException(String message) {
    super(message);
  }

  public PropertyFileUsageException(String message, Throwable t) {
    super(message, t);
  }
}
