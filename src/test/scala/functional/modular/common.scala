package functional.modular

import scala.collection.mutable.Buffer

/** A mini-framework for trace-based testing of interactive behavior. */
object Tracing:

  enum TraceEvent[Input, Result] derives CanEqual:
    case InputEvent(value: Input)
    case OutputEvent(value: Result)

  import TraceEvent.*
  
  /**
   * Invokes the original run method on the instrumented input,
   * instruments the output, and returns the trace.
   */
  def runWithTracing[Input, Result]
    (run: Task[Input, Result])
    (input: Iterator[Input], args: Array[String] = Array.empty)
  : Seq[TraceEvent[Input, Result]] =
    val trace = Buffer.empty[TraceEvent[Input, Result]]
    val tracedInput = input.map { s => trace.append(InputEvent(s)); s }
    run(tracedInput, args).foreach { s => trace.append(OutputEvent(s)) }
    trace.toSeq

end Tracing
