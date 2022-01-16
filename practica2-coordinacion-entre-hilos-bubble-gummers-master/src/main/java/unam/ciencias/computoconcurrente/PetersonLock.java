package unam.ciencias.computoconcurrente;

/**
 * This particular implementation of a lock uses the Peterson's Algorithm which only work for two
 * concurrent threads at most.
 */
public class PetersonLock implements Lock {
  volatile int victima;
  boolean[] bandera = new boolean[2];

  public PetersonLock() {}

  @Override
  public void lock() {
    int ID = Integer.valueOf(Thread.currentThread().getName()) % 2; // Obtenemos ID de nuestro hilo
    bandera[ID] = true; // Estamo interesados
    victima = ID; // Dejamos pasar
    while (bandera[1 - ID] && victima == ID)
      ; // Esperamos mientras el otro este interrados y sea victima
  }

  @Override
  public void unlock() {
    int ID = Integer.valueOf(Thread.currentThread().getName()) % 2;
    bandera[ID] = false; // Ya no quiero nada.
  }
}
