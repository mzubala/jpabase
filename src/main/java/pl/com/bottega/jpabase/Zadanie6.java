package pl.com.bottega.jpabase;

import java.math.BigDecimal;

public class Zadanie6 extends TaskTemplate {
  @Override
  protected void doTask() {
    User user = new User();
    user.name = "Janek";
    user.id = 1L;
    Address address = new Address();
    address.city = "Warszawa";
    user.address = address;
    address.user = user;
    address.id = 1L;
    Auction auction = new Auction("first");
    auction.id = 1L;
    Bid b1 = new Bid();
    Bid b2 = new Bid();
    b1.id = 1L;
    b1.amount = new BigDecimal(100);
    b2.id = 2L;
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
