class ReUpdateLive(val rn: TpRingNchar, val ncode: Int, var lTwin: TpLiveTwin):
  var i         = 0
  val real      = Array.fill[Int](SIMATCHNUMBER(MAXRING) / 8 + 2)(255)
  val state     = new TpUpdateState (lTwin, real, 0, 1, 0)
  val testMatch = new TestMatch     (rn, state)
  while (i == 0 || isUpdate)
    lTwin = testMatch.run // computes {\cal M}_{i+1} from {\cal M}_i, updates the bits of "real"
    i += 1

  private def isUpdate: Boolean =
    // runs through "live" to see which colourings still have `real' signed
    // matchings sitting on all three pairs of colour classes, and updates "live"
    // accordingly; returns 1 if nlive got smaller and stayed >0, and 0 otherwise *)
    var newNlive = 0
    if lTwin.live(0) > 1 then lTwin.live(0) = 15 

    for (i <- 0 until ncode) { if lTwin.live(i) == 15 then { newNlive += 1; lTwin.live(i) = 1 } else lTwin.live(i) = 0 }
    printf("             %4d", newNlive) // left

    if newNlive < lTwin.nLive && newNlive > 0 then
      lTwin.nLive = newNlive
      true
    else
      if newNlive == 0 then
        println("\n\n\n                  ***  D-reducible  ***")
      else
        println("\n\n\n                ***  Not D-reducible  ***")
      lTwin.nLive = newNlive
      false


class TestMatch(val rn: TpRingNchar, var state: TpUpdateState):
  def run: TpLiveTwin =
    println("TestMatch. " + state.real(0).toString)
    state.nReal    = 0
    state.byte     = 1 // -128~127
    state.realterm = 0

    // This generates all balanced signed matchings, and for each one, tests
    // whether all associated colourings belong to "live". It writes the answers
    // in the bits of the characters of "real". *)
    // "nreal" will be the number of balanced signed matchings such that all
    // associated colourings belong to "live"; ie the total number of nonzero
    // bits in the entries of "real" *)
    // First, it generates the matchings not incident with the last ring edge
    val matchW   = Array.fill[Array[Array[Int]]](16) ( Array.fill[Array[Int]](16) ( Array.fill[Int](4)(0) ) )
    val interval = Array.fill[Int](10)(0)
    val weight   = Array.fill[Array[Int]](10)( Array.fill[Int](4)(0) )

    for (a <- 2 to rn.ring; b <- 1 to a - 1)
      matchW(a)(b)(0) = (POWER(a) + POWER(b)) * 2
      matchW(a)(b)(1) = (POWER(a) - POWER(b)) * 2
      matchW(a)(b)(2) =  POWER(a) + POWER(b)
      matchW(a)(b)(3) =  POWER(a) - POWER(b)

    for (a <- 2 to rn.ring - 1; b <- 1 to a - 1)
      var n = 0
      weight(1) = matchW(a)(b)
      if b >= 3 then
        n = 1
        interval(1) = 1
        interval(2) = b - 1
      if a >= b + 3 then
        n += 1
        interval(2 * n - 1) = b + 1
        interval(2 * n)     = a - 1
      // augment n, interval, 1, weight, match_w, nreal, ring, 0, 0, bit, realterm, nchar, real, @live2

    // now, the matchings using an edge incident with "ring"
    for (a <- 2 to rn.ring; b <- 1 to a - 1)
      matchW(a)(b)(0) =  POWER(a) + POWER(b)
      matchW(a)(b)(1) =  POWER(a) - POWER(b)
      matchW(a)(b)(2) = -POWER(a) - POWER(b)
      matchW(a)(b)(3) = -POWER(a) - 2 * POWER(b)

    for (b <- 1 to rn.ring - 1)
      var n = 0
      weight(1) = matchW(rn.ring)(b)
      if b >= 3 then
        n = 1
        interval(1) = 1
        interval(2) = b - 1
      if rn.ring >= b + 3 then
        n += 1
        interval(2 * n - 1) = b + 1
        interval(2 * n)     = rn.ring - 1
      val pow = (POWER(rn.ring + 1) - 1) / 2
      // augment n, interval, 1, weight, match_w, nreal, ring, pow, 1, bit, realterm, nchar, real, @live2

    printf("                       %d\n", state.nReal) // right
    state.lTwin



