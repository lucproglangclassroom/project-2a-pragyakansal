package imperative.modular

import java.io.PrintStream

/** A dependency (plug-in contract) on an update method (Observer). */
trait Observer:
  type Result
  def update(result: Result): Unit
end Observer

/** Prints its second argument to a given stream. */
def outputToPrintStream[Result](out: PrintStream)(result: Result): Unit =
  out.println(result)
  // terminate on I/O error such as SIGPIPE
  if out.checkError() then
    sys.exit(1)

/**
 * A dependency (plug-in contract) on a run method that processes an input stream.
 * Typically reports results to an Observer provided as a mixin.
 */
trait Task:
  type Input
  def run(args: Seq[String])(input: Iterator[Input]): Unit
end Task

/**
 * An Observer mixin for running a Task within a suitable main method.
 * The Task receives input from stdin, and its Observer updates go to stdout.
 */
trait Main extends Observer:
  self: Task =>
  override type Input = String
  override def update(result: Result) = outputToPrintStream(scala.sys.process.stdout)(result)
  def main(args: Array[String]): Unit =
    val lines = scala.io.Source.stdin.getLines()
    run(args.toIndexedSeq)(lines)
end Main
