package unam.ciencias.computoconcurrente;

public class FiveValueCounter extends FixedValueCounter {
  public FiveValueCounter() {
    super();
  }

  @Override
  public int getAndIncrement() {
    int prevValue = 0;
    int tmp = value;
    prevValue = value;
    value = tmp + 1;
    return prevValue;
  }

  @Override
  public int getAndDecrement() {
    return 0;
  }
}
