package imperative.modular
import imperative.modular.Task
import scala.util.Try
import scala.collection.mutable
import scala.collection.mutable.Queue
import scala.annotation.targetName
import scala.io.Source

trait topWords extends Task {
  private def processLines(howMany: Int, minLength: Int, lastNWords: Int, lines: Iterator[String | Null]): Unit = {
    val slidingQueue = mutable.Queue[String]()
    import scala.language.unsafeNulls

    for (line <- lines) {
      val trimmedLine = Option(line).getOrElse("").trim
      if (trimmedLine == "EOF") {
        println("Reached EOF. Exiting.")
        return
      }
      val words: Array[String] = trimmedLine.split("(?U)[^\\p{Alpha}0-9']+").map(x => Option(x).getOrElse("").toLowerCase())
      for (word <- words) {
        val wordLength = word.length()
        if (wordLength >= minLength) {
          slidingQueue += word
        }
        if (slidingQueue.size > lastNWords) {
          val removedWord = slidingQueue.dequeue()
          val wordFreq = slidingQueue.groupBy(identity).view.mapValues(_.size)
          val topWords = wordFreq.toList.sortBy { case (_, freq) => -freq }.take(howMany)
          val stats = topWords.map { case (word, freq) => s"$word: $freq" }.mkString(" ")
          println(stats)
        }
      }
    }
  }

  @targetName("runTopWords")
  def run(args: Seq[String])(input: Iterator[String | Null]): Unit = {
    val howMany = Try(args(0).toInt).getOrElse(10)
    val minLength = Try(args(1).toInt).getOrElse(6)
    val lastNWords = Try(args(2).toInt).getOrElse(1000)
    processLines(howMany, minLength, lastNWords, input)
  }
}

object topWordsMain extends Main with topWords {
  override def main(args: Array[String]): Unit = {
    if (args.length == 1 && args(0).endsWith(".txt")) {
      val input = Source.fromFile(args(0)).getLines()
      run(Seq("10", "6", "1000"))(input)
    } else {
        val howMany = Try(args(0).toInt).getOrElse(10)
        val minLength = Try(args(1).toInt).getOrElse(6)
        val lastNWords = Try(args(2).toInt).getOrElse(1000)

        // Read input from standard input
        val input = scala.io.Source.stdin.getLines()
        run(Seq(howMany.toString, minLength.toString, lastNWords.toString))(input)
    }    
  }
} 



