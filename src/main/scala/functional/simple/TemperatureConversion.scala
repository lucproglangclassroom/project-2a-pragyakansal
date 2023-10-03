package functional.simple

object TemperatureConversion:
  def main(args: Array[String]): Unit =
    val lines = scala.io.Source.stdin.getLines()

    val values = lines.map(_.toDouble)
    val results = values.map(r => math.round(r / 20))

    results takeWhile { _ =>
      // terminate on I/O error such as SIGPIPE
      !scala.sys.process.stdout.checkError()
    } foreach { r =>
      println(r)
    }
end TemperatureConversion
