package imperative.modular

import scala.collection.mutable.Buffer

/** An Observer mixin that accumulates the received updates in a buffer one can inspect later. */
trait OutputToBuffer extends Observer:
  val buffer = Buffer.empty[Result]
  override def update(result: Result) = buffer.append(result)
end OutputToBuffer

/**
 * An Observer mixin for trace-based testing of interactive behaviors.
 * Also depends on a Task implementation whose run method it refines (decorates).
 */
trait Tracing extends Task with Observer:

  enum TraceEvent derives CanEqual:
    case InputEvent(value: Input)
    case OutputEvent(value: Result)

  import TraceEvent.*

  val trace = Buffer.empty[TraceEvent]

  /** Instruments the input such that accessing each item appends the corresponding event to the trace. */
  protected def traced(input: Iterator[Input]) = input.map { s => trace.append(InputEvent(s)) ; s }

  /** Adds an update to the trace. */
  override def update(result: Result) = trace.append(OutputEvent(result))

  /**
   * Invokes the original run method on the instrumented input.
   * `abstract override` lets us refine (decorate) a run method that is still abstract
   * as we are defining this trait but will be available later from a Task implementation such as `CountItems`.
   */
  abstract override def run(args: Seq[String])(input: Iterator[Input]) =
    super.run(args)(traced(input))

end Tracing
