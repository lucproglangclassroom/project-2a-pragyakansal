package functional.modular

/**
 * Provides a reusable method for invoking a task with stdin and stdout.
 * Depends on a suitable task (run method) that transforms an input stream to an output stream.
 */

/** A transformation of an input stream to a result stream. */
type Task[-Input, +Result] = (Iterator[Input], Array[String]) => Iterator[Result]

def runWithStdIO[Result](run: Task[String, Result], args: Array[String]): Unit =
  val lines = scala.io.Source.stdin.getLines()
  val result = run(lines, args)
  result
    // terminate on I/O error such as SIGPIPE
    .takeWhile: _ =>
      !scala.sys.process.stdout.checkError()
    .foreach: r =>
      println(r)
