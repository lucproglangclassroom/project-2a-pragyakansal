package imperative.modular
import scala.util.Try
import scala.collection.mutable
import scala.collection.mutable.Queue

trait topWords extends Task[String] with Output[String]:
  override def run(input: Iterator[String], args: Array[String] = Array.empty) = 
    val lines = scala.io.Source.stdin.getLines()
    val words = {
      import scala.language.unsafeNulls
      lines.flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+")).map(l => l.toLowerCase())
    }

      val howMany = Try(args(0).toInt).getOrElse(10)
      val lastNWords = Try(args(2).toInt).getOrElse(1000)
      val minLength = Try(args(1).toInt).getOrElse(6)
      val slidingQueue = mutable.Queue [String]()

      for word <- words do
        // If the length of the word is greater or equal to the minimum length, add it to the queue
        if word.length() > minLength then
          slidingQueue += word

          // If the size of the queue exceeds than the maximum amount of words allowed in the sliding window, remove the fist word from the queue
          if slidingQueue.size > lastNWords then 
            slidingQueue.dequeue()
         

end topWords
object topWordsMain extends Main[String] with topWords



