
public class PingPong {
  public static void main(String[] args) {
      Thread ping = new Thread(new MyRunnable(), "ping");
      Thread pong = new Thread(new MyRunnable(), "pong");
      ping.start();
      try {
        ping.join(2000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      pong.start();

      try {
          ping.join();
          pong.join();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

}
