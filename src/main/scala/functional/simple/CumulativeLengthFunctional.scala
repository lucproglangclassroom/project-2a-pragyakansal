package functional.simple

/** Reads lines and prints cumulative length of all lines so far along with most recent line itself. */
object CumulativeLengthFunctional:
  def main(args: Array[String]): Unit =
    val lines = scala.io.Source.stdin.getLines()

    def accumulateCount(acc: (String, Int), next: String): (String, Int) =
      (next, acc._2 + next.length)
    val results = lines.scanLeft("dummy", 0)(accumulateCount).drop(1)

    results
      // terminate on I/O error such as SIGPIPE
      .takeWhile: _ => 
        !scala.sys.process.stdout.checkError()
      .foreach: r => 
        println(r)
end CumulativeLengthFunctional
