package functional.modular

import org.scalatest.wordspec.AnyWordSpec

class TestLineCountFunctional extends AnyWordSpec:

  /** Refers to the existing immutable SUT instance. */
  val sut = LineCountFunctionalModular

  "The functional LineCounter" when {
    "given an empty iterator" should {
      "produce an empty output" in {
        // exercise SUT
        val result: Iterator[(Int, String)] = sut.run(Iterator.empty)
        // check effect on output observer
        assert(result.isEmpty)
      }
    }

    "given a nonempty iterator" should {
      "produce the correct nonempty output" in {
        // input data for this test case
        val data = Seq("hello", "world", "what", "up")
        // exercise SUT
        val result: Iterator[(Int, String)] = sut.run(data.iterator)
        // check effect on output observer
        assert(result.toSeq == (1 to data.length).zip(data))
      }
    }

    "given a nonempty iterator" should {
      "exhibit the correct interactive behavior" in {
        // input data for this test case
        val input = Iterator("hello", "world", "what", "up")
        // exercise SUT
        val trace = Tracing.runWithTracing(sut.run)(input)
        // check correctness of resulting interactions
        import Tracing.TraceEvent.{ InputEvent as i, OutputEvent as o }
        assert(trace == Seq(
          i("hello"), o((1, "hello")),
          i("world"), o((2, "world")),
          i("what"), o((3, "what")),
          i("up"), o((4, "up"))))
      }
    }
  }

end TestLineCountFunctional
