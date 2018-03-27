package pl.com.bottega.jpabase;

import java.math.BigDecimal;

public class Zadanie6 extends TaskTemplate {
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
      em.persist(user);
      em.persist(auction);
    });

    executeInTx((em) -> {
      Auction au = em.find(Auction.class, 1L);
      System.out.println(au.title);
      for(Bid bid : au.bids) {
        System.out.println(bid.amount);
      }
    });

  }

  public static void main(String[] args) {
    new Zadanie6().execute();
  }
}
