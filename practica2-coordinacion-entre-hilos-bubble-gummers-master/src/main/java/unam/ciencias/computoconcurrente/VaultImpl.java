package unam.ciencias.computoconcurrente;

public class VaultImpl implements Vault {

  public VaultImpl(int password, Lock lock) {}

  @Override
  public boolean isPassword(int guess) {
    return false;
  }

  @Override
  public boolean isPasswordFound() {
    return false;
  }
}
