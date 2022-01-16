package unam.ciencias.computoconcurrente;

public class FilterAlgorithmLock implements Lock {
  private int totalThreads;

  volatile int[] nivel;
  volatile int[] victima;

  public FilterAlgorithmLock(int totalThreads) {
    this.totalThreads = totalThreads;

    nivel = new int[totalThreads];
    victima = new int[totalThreads];

    for (int i = 0; i < totalThreads; ++i) {
      nivel[i] = 0;
      victima[i] = 0;
    }
  }

  public int getTotalThreads() {
    return totalThreads;
  }

  public void setTotalThreads(int totalThreads) {
    this.totalThreads = totalThreads;
  }

  @Override
  public void lock() {
    int ID = Integer.valueOf(Thread.currentThread().getName());

    for (int L = 1; L < victima.length; ++L) {
      nivel[ID] = L;
      victima[L] = ID;

      for (int k = 0; k < totalThreads; ++k) {
        while ((k != ID) && nivel[k] >= L && victima[L] == ID) ;
      }
    }
  }

  @Override
  public void unlock() {
    int ID = Integer.valueOf(Thread.currentThread().getName());
    nivel[ID] = 0;
  }
}
