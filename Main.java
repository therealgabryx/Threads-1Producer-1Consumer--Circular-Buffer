class Main {
  static final int DIM_BUFFER = 5;
  protected static int[] Buffer = new int[DIM_BUFFER];
  protected static int metti = 0;
  protected static int togli = 0;

  public static void main(String[] args) {
    Semaforo pieno = new Semaforo(0);
    Semaforo vuoto = new Semaforo(DIM_BUFFER);
    Semaforo mutexP = new Semaforo(1);
    Semaforo mutexC = new Semaforo(1);

    Produttore prod = new Produttore(pieno, vuoto, mutexP);
    Consumatore cons = new Consumatore(pieno, vuoto, mutexC);

    prod.start();
    cons.start();
  }
}