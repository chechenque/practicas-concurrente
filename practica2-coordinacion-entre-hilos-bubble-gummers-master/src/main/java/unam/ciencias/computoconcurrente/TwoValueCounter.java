package unam.ciencias.computoconcurrente;

public class TwoValueCounter extends FixedValueCounter {

  public TwoValueCounter() {
    super();
  }

  @Override
  public int getAndIncrement() {
    int prevValue = 0;
    try{

      int ID = Integer.valueOf(Thread.currentThread().getName());

      if(ID == 0) Thread.sleep(100);

      
      int tmp = value;
      prevValue = value;
      value = tmp + 1;
      return prevValue;
    }catch(InterruptedException e){
        System.out.println(e);
      }
      return prevValue;
  }

  @Override
  public int getAndDecrement() {
    return 0;
  }
}
