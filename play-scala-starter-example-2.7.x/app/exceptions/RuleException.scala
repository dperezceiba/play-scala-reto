package exceptions

final case class RuleException(
  private val message: String    = "",
  private val cause:   Throwable = None.orNull)
  extends RuntimeException(message, cause) 