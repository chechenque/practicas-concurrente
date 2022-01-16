package unam.ciencias.computoconcurrente;

public class TwoValueCounter extends FixedValueCounter {

  public TwoValueCounter() {
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
    int prevValue = 0;
    int tmp = value;
    prevValue = value;
    value = tmp - 1;
    return prevValue;
  }
}
