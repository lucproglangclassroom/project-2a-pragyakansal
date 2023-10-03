package imperative.modular

/**
 * A Task implementation for reading lines and printing the cumulative length
 * of all lines so far along with the most recent line itself.
 * Still depends on a suitable Observer mixin.
 */
trait AccumulateLength extends Task:
  self: Observer =>
  override type Input = String
  override type Result = (String, Int)
  def run(args: Seq[String])(input: Iterator[Input]): Unit =
    if args.nonEmpty then
      sys.process.stderr.println("args: " + args.toSeq)
    var line = "dummy"
    var length = 0
    for next <- input do
      line = next
      length = length + next.length
      update((line, length))
end AccumulateLength

/**
 * A concrete, executable application combining Task and Observer implementations.
 * The input type already matches that of Main.
 */
object CumulativeLengthImperativeModular extends AccumulateLength, Main