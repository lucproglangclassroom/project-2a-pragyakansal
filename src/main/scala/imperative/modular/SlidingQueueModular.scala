package imperative.modular
import org.apache.commons.collections4.queue.CircularFifoQueue
import scala.annotation.targetName

trait SlidingQueue extends Task {
  self: Observer =>

  override type Result = CircularFifoQueue[String]

  @targetName("runWithInput")
  def run(args: Seq[String])(input: Iterator[String]): CircularFifoQueue[String] = {
    if (args.nonEmpty) {
      Console.err.println("args: " + args)
    }

    val queueSize = args.headOption.getOrElse("10").toInt
    val queue = new CircularFifoQueue[String](queueSize)

    for (item <- input) {
      queue.add(item)
      update(queue)
    }
    queue
  }
}

object SlidingQueueExample extends SlidingQueue with Main {
  type Input = String 
}


