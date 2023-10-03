package imperative.simple

/** Reads lines and prints line count along with line itself. */
object LineCountImperative:
  def main(args: Array[String]): Unit =
    var count = 0
    for line <- scala.io.Source.stdin.getLines() do
      count += 1
      println((count, line))
      // terminate on I/O error such as SIGPIPE
      if scala.sys.process.stdout.checkError() then
        sys.exit(1)
end LineCountImperative
