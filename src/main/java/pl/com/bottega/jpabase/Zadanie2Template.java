package pl.com.bottega.jpabase;

public class Zadanie2Template extends TaskTemplate {
  @Override
  protected void doTask() {
    executeInTx((em) -> {
      Auction auction = new Auction("test");
      auction.duration = 7;
      auction.id = 1L;
      auction.description = "opis";
      em.persist(auction);
    });

    executeInTx((em) -> {
      Auction auction = em.find(Auction.class, 1L);
      auction.title = "nowy nowy";
    });
  }

  public static void main(String[] args) {
    new Zadanie2Template().execute();
  }

}
