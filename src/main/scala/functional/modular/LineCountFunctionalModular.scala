package functional.modular

/** Reads lines and prints line count along with line itself. */
object LineCountFunctionalModular:

  def run(lines: Iterator[String], args: Array[String] = Array.empty): Iterator[(Int, String)] = {
    if args.length > 0 then
      System.err.nn.println("args: " + args.toSeq)
    val counts = Iterator.from(1)
    counts.zip(lines)
  }

  def main(args: Array[String]): Unit = runWithStdIO(run, args)

end LineCountFunctionalModular
