package pl.com.bottega.jpabase;

public class Zadanie4 extends TaskTemplate {
  @Override
  protected void doTask() {
    Auction auction = new Auction("test");
    auction.duration = 7;

    auction.description = "opis";
    executeInTx((em) -> {
      em.merge(auction);
    });

    executeInTx((em) -> {
      Auction merged = em.merge(auction);
      merged.title = "nowy nowy";
    });
  }

  public static void main(String[] args) {
    new Zadanie4().execute();
  }

}
