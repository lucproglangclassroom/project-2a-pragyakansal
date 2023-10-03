package imperative.modular

/**
 * A Task implementation for reading items and reporting an item count along with each item.
 * Still depends on a suitable Observer mixin.
 * In addition, the result type depends on the still unspecified input type.
 */
trait CountItems extends Task:
  self: Observer =>
  override type Result = (Int, Input)
  def run(args: Seq[String])(input: Iterator[Input]): Unit =
    if args.nonEmpty then
      sys.process.stderr.println("args: " + args)
    var count = 0
    for item <- input do
      count += 1
      update((count, item))
end CountItems

/**
 * An concrete, executable application combining Task and Observer implementations.
 * Main specifies the input type as `String` and thereby sets the result type to `(Int, String)`.
 */
object LineCountImperativeModular extends CountItems, Main
