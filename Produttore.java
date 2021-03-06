import java.util.Random;

class Produttore extends Thread {
  Semaforo pieno, vuoto, mutexP; // mutexP: Mutex betw. producers

  Random att = new Random();  // rnd waiting time 
  Random dat = new Random();  // rnd data to put into buffer

  public Produttore(Semaforo s1, Semaforo s2, Semaforo mP) {
    pieno = s1;
    vuoto = s2;
    mutexP = mP;
  }

  public void run() {
    int attesa, dato, temp_metti;

    while (true) {
      attesa = att.nextInt(100) * 25;
      dato = dat.nextInt(100);

      vuoto.P();
        mutexP.P();
          temp_metti = Main.metti;
          Main.metti = (Main.metti + 1) % Main.DIM_BUFFER;
        mutexP.V();
        Main.Buffer[temp_metti] = dato;
        System.out.println("+ PROD : dato scritto [" + dato + "] nella cella [" + temp_metti + "]");
      pieno.V();

      try {
        Thread.sleep(attesa);
      } catch (Exception e) {}
    }
  }
}