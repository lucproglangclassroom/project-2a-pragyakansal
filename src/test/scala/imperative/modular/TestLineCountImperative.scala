package imperative.modular

import org.scalatest.wordspec.AnyWordSpec

class TestLineCountImperative extends AnyWordSpec:

  /** Creates a (mutable!) SUT instance. */
  def createSUT() = new CountItems with OutputToBuffer:
    override type Input = String

  "An imperative LineCounter" when:
    "given an empty iterator" should:
      "produce an empty output" in:
        // create SUT instance for this test case
        val sut = createSUT()
        // exercise SUT
        sut.run(Seq.empty)(Iterator.empty)
        // check effect on output observer
        assert(sut.buffer.isEmpty)

    "given a nonempty iterator" should:
      "produce the correct nonempty output" in:
        // input data for this test case
        val data = Seq("hello", "world", "what", "up")
        // create SUT instance for this test case
        val sut = createSUT()
        // exercise SUT
        sut.run(Seq.empty)(data.iterator)
        // check effect on output observer
        assert(sut.buffer == (1 to data.length).zip(data))

  "given a nonempty iterator" should:
    "exhibit the correct interactive behavior" in:
      // input data for this test case
      val input = Iterator("hello", "world", "what", "up")
      // create SUT instance for this test case
      val sut = new CountItems with Tracing:
        override type Input = String
      // exercise SUT
      sut.run(Seq.empty)(input)
      // check correctness of resulting interactions
      import sut.TraceEvent.{InputEvent as i, OutputEvent as o}
      assert(sut.trace == Seq(
        i("hello"), o((1, "hello")),
        i("world"), o((2, "world")),
        i("what"), o((3, "what")),
        i("up"), o((4, "up"))))

end TestLineCountImperative
