object Reduce:
  def main(args: Array[String]): Unit = 
    println("Reduce.")
    // UpdateLive (check D reducible)
    val rn    = new TpRingNchar  (8, 0) // nchar = Const::SIMATCHNUMBER[ring] / 8 + 1
    val lTwin = new TpLiveTwin   (0, Array(1, 2, 3))
    val uLive = new ReUpdateLive (rn, 3, lTwin)
    println(uLive.ncode)
    uLive.lTwin.live(0) = 99
    println(uLive.lTwin.live(0))
    uLive.lTwin.nLive = 1111
    println(uLive.lTwin.nLive)



