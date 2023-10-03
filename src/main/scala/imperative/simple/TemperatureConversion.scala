package imperative.simple

object TemperatureConversion:
  def main(args: Array[String]): Unit =
    var line: String | Null = scala.io.StdIn.readLine()
    while line != null do
      val raw = line.nn.toInt
      val celsius = math.round(raw.toFloat / 20)
      println(celsius)
      // terminate on I/O error such as SIGPIPE
      if scala.sys.process.stdout.checkError() then
        sys.exit(1)
      line = scala.io.StdIn.readLine()
end TemperatureConversion
