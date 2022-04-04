class ReUpdateLive(val rn: TpRingNchar, val ncode: Int, var lTwin: TpLiveTwin):
  var i         = 0
  val real      = Array.fill[Int](SIMATCHNUMBER(MAXRING) / 8 + 2)(255)
  val state     = new TpUpdateState (lTwin, real, 0, 1, 0)
  val testMatch = new TestMatch     (rn, state)
  while(i == 0 || isUpdate)
    lTwin = testMatch.run // computes {\cal M}_{i+1} from {\cal M}_i, updates the bits of "real"
    i += 1

  private def isUpdate: Boolean =
    // runs through "live" to see which colourings still have `real' signed
    // matchings sitting on all three pairs of colour classes, and updates "live"
    // accordingly; returns 1 if nlive got smaller and stayed >0, and 0 otherwise *)
    var newNlive = 0
    if lTwin.live(0) > 1 then lTwin.live(0) = 15 

    for(i <- 0 until ncode) { if lTwin.live(i) == 15 then { newNlive += 1; lTwin.live(i) = 1 } else lTwin.live(i) = 0 }
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
    state.byte     = 1
    state.realterm = 0

    state.lTwin



