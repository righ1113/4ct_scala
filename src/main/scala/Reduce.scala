object Reduce:
  def main(args: Array[String]): Unit = 
    println("Hello world!")
    println(msg)

    val a = new ReUpdateLive(3)
    println(a.ncode)
    a.live(0) = 99
    println(a.live(0))
    a.n_live = 1111
    println(a.n_live)
  def msg = "I was compiled by Scala 3. :)"



