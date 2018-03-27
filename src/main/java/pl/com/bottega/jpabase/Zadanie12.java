package pl.com.bottega.jpabase;

public class Zadanie12 extends TaskTemplate {
  @Override
  protected void doTask() {
    Auction a = executeInTx((em) -> {
      Auction au = new Auction("t1");
      em.persist(au);
      return au;
    });
    try {
      Thread.sleep(1000 * 2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    executeInTx((em) -> {
      a.title =  "t2";
      em.merge(a);
    });
  }

  public static void main(String[] args) {
    new Zadanie12().execute();
  }

}
