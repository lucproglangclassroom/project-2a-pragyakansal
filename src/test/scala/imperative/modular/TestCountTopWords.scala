package imperative.modular

import org.scalatest.wordspec.AnyWordSpec

import scala.collection.MapView

class TestCountTopWords extends AnyWordSpec:

  /** Creates a (mutable!) SUT instance. */
  def createSUT() = new CountItems with OutputToBuffer:
    override type Input = String

  "An imperative LineCounter" when:
    "given an empty iterator" should:
      "produce an empty output" in:
        // create SUT instance for this test case
        val sut = createSUT()
        // exercise SUT
        sut.run(Seq("10", "6", "1000"))(Iterator.empty)
        // check effect on output observer
        assert(sut.buffer.isEmpty)

    "given a nonempty iterator" should:
      "produce the correct nonempty output" in:
        // input data for this test case
        val data = Seq("aa", "bb", "cc", "aa", "bb")
        // create SUT instance for this test case
        val sut = createSUT()
        // exercise SUT
        sut.run(Seq("3", "2", "5"))(data.iterator)
        // check effect on output observer
        val expectedOutput = "aa: 2 bb: 2 cc: 1"
        import scala.language.unsafeNulls
        val wordsStr = sut.buffer.mkString(" ").toLowerCase()
        println(wordsStr)
        val words = wordsStr.split("\\s+").map(_.split(",")(1)).map(_.dropRight(1))
        val wordFreq = words.groupBy(identity).view.mapValues(_.size)
        val topWords = wordFreq.toList.sortBy { case (_, freq) => -freq }
        val actualOutput = topWords.map { case (word, freq) => s"$word: $freq" }.mkString(" ")
        println(actualOutput)
        assert(actualOutput == expectedOutput)

end TestCountTopWords