package benchmark

import imperative.modular.{ AccumulateLength, Observer }
import org.openjdk.jmh.annotations.Benchmark

class BenchmarkCumulativeLengthImperative extends Inputs:

  def sut = new AccumulateLength with Observer:
    var length = 0
    override def update(result: Result) = length += 1

  @Benchmark
  def run1_000() = sut.run(Seq.empty)(input1_000)

  @Benchmark
  def run10_000() = sut.run(Seq.empty)(input10_000)

  @Benchmark
  def run100_000() = sut.run(Seq.empty)(input100_000)

  @Benchmark
  def run1_000_000() = sut.run(Seq.empty)(input1_000_000)

end BenchmarkCumulativeLengthImperative
