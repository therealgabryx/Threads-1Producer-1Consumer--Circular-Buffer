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

    Produttore prod1 = new Produttore("P#1", pieno, vuoto, mutexP);
    Produttore prod2 = new Produttore("P#2", pieno, vuoto, mutexP);
    Produttore prod3 = new Produttore("P#3", pieno, vuoto, mutexP);

    Consumatore cons1 = new Consumatore("C#1", pieno, vuoto, mutexC);
    Consumatore cons2 = new Consumatore("C#2", pieno, vuoto, mutexC);
    Consumatore cons3 = new Consumatore("C#3", pieno, vuoto, mutexC);

    prod1.start();
    prod2.start();
    prod3.start();

    cons1.start();
    cons2.start();
    cons3.start();
  }
}