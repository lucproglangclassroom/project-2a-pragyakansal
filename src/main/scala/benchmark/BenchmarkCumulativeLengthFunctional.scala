package benchmark

import functional.modular.CumulativeLengthFunctionalModular
import org.openjdk.jmh.annotations.Benchmark

class BenchmarkCumulativeLengthFunctional extends Inputs:

  def sut = CumulativeLengthFunctionalModular

  @Benchmark
  def run1_000() = sut.run(input1_000).foreach { _ => }

  @Benchmark
  def run10_000() = sut.run(input10_000).foreach { _ => }

  @Benchmark
  def run100_000() = sut.run(input100_000).foreach { _ => }

  @Benchmark
  def run1_000_000() = sut.run(input1_000_000).foreach { _ => }

end BenchmarkCumulativeLengthFunctional
