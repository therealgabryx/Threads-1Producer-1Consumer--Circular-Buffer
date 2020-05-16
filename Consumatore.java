import java.util.Random;

class Consumatore extends Thread {
  Semaforo pieno, vuoto, mutexC; // mutexP: Mutex betw. consumers

  Random att = new Random(); // rnd waiting time

  public Consumatore(Semaforo s1, Semaforo s2, Semaforo mC) {
    pieno = s1;
    vuoto = s2;
    mutexC = mC;
  }

  public void run() {
    int attesa, dato, temp_togli;

    while (true) {
      attesa = att.nextInt(100) * 25;

      pieno.P();
      mutexC.P();
      temp_togli = Main.togli;
      Main.togli = (Main.togli + 1) % Main.DIM_BUFFER;
      mutexC.V();
      dato = Main.Buffer[temp_togli];
      Main.Buffer[temp_togli] = 0;
      System.out.println("- CONS : dato letto (" + dato + ") dalla cella (" + temp_togli + ")");
      vuoto.V();

      try {
        Thread.sleep(attesa);
      } catch (Exception e) {
      }
    }
  }
}