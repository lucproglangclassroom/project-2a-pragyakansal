package benchmark

trait Inputs:

  def input1_000 = Iterator.fill(1000)("hello")

  def input10_000 = Iterator.fill(10000)("hello")

  def input100_000 = Iterator.fill(100000)("hello")

  def input1_000_000 = Iterator.fill(1000000)("hello")

end Inputs
