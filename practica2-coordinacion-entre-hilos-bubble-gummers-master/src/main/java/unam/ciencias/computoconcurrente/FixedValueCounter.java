package unam.ciencias.computoconcurrente;

public abstract class FixedValueCounter implements Counter {
  protected volatile int value;
  protected volatile int iteration;
  protected int rounds;

  public FixedValueCounter() {}

  // This values should be different for each thread
  public void setIteration(int iteration) {
    this.iteration = iteration;
  }

  public void setRounds(int rounds) {
    this.rounds = rounds;
  }

  @Override
  public int getValue() {
    return this.value;
  }
}
