package pl.com.bottega.jpabase;

import java.math.BigDecimal;

public class Zadanie5 extends TaskTemplate {
  @Override
  protected void doTask() {
    User user = new User();
    user.name = "Janek";

    Address address = new Address();
    address.city = "Warszawa";
    user.address = address;
    address.user = user;

    Auction auction = new Auction("first");

    Bid b1 = new Bid();
    Bid b2 = new Bid();

    b1.amount = new BigDecimal(100);

    b2.amount = new BigDecimal(200);
    b1.user = user;
    b2.user = user;
    b1.auction = auction;
    b2.auction = auction;
    auction.bids.add(b1);
    auction.bids.add(b2);
    user.bids.add(b1);
    user.bids.add(b2);
    executeInTx((em) -> {
      em.persist(address);
      em.persist(user);
      em.persist(auction);
      em.persist(b1);
      em.persist(b2);
    });
  }

  public static void main(String[] args) {
    new Zadanie5().execute();
  }
}
