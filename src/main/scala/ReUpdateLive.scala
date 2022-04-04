class ReUpdateLive(val rn: TpRingNchar, val ncode: Int, var lTwin: TpLiveTwin):
  var i         = 0
  val real      = Array.fill[Int](SIMATCHNUMBER(MAXRING) / 8 + 2)(255)
  val state     = new TpUpdateState (lTwin, real, 0, 1, 0)
  val testMatch = new TestMatch     (rn, state)
  while(i == 0 || isUpdate)
    lTwin = testMatch.run // computes {\cal M}_{i+1} from {\cal M}_i, updates the bits of "real"
    i += 1

  def isUpdate = false


class TestMatch(val rn: TpRingNchar, var state: TpUpdateState):
  def run: TpLiveTwin =
    println("TestMatch. " + state.real(0).toString)
    state.lTwin



