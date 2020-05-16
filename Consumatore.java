import java.util.Random;

class Consumatore extends Thread {
  Semaforo pieno, vuoto, mutexC; // mutexC: Mutex betw. consumers
  String nome;

  Random att = new Random(); // rnd waiting time

  public Consumatore(String nome, Semaforo s1, Semaforo s2, Semaforo mC) {
    this.nome = nome;
    pieno = s1;
    vuoto = s2;
    mutexC = mC;
  }

  public void run() {
    int attesa, dato, temp_togli;

    while (true) {
      // attesa = 250;
      attesa = att.nextInt(100) * 25;

      pieno.P();
        mutexC.P();
          temp_togli = Main.togli;
          Main.togli = (Main.togli + 1) % Main.DIM_BUFFER;
          dato = Main.Buffer[temp_togli];
          Main.Buffer[temp_togli] = 0;
          System.out.println("- CONS (" + nome + "): dato letto (" + dato + ") dalla cella (" + temp_togli + ")");
        mutexC.V();
      vuoto.V();

      try {
        Thread.sleep(attesa);
      } catch (Exception e) {
      }
    }
  }
}