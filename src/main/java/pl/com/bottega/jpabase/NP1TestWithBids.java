package pl.com.bottega.jpabase;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.math.BigDecimal;
import java.util.List;

public class NP1TestWithBids extends TaskTemplate {
  @Override
  protected void doTask() {
    int n = 100;
    for (int i = 0; i < n; i++) {
      Auction auction = new Auction("a" +  i);
      Bid bid = new Bid();
      bid.amount = new BigDecimal(i);
      bid.auction = auction;
      auction.bids.add(bid);
      executeInTx((em) -> {
        em.persist(auction);
      });
    }

    executeInTx((em) -> {
      SessionFactory factory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
      Statistics stats = factory.getStatistics();
      stats.setStatisticsEnabled(true);
      List<Auction> auctions = em.createQuery("SELECT a FROM Auction a").getResultList();
      for(Auction a : auctions) {
        System.out.println(a.bids.size());
      }
      System.out.println("Ilość zapytań: " + stats.getPrepareStatementCount());
    });
  }

  public static void main(String[] args) {
    new NP1TestWithBids().execute();
  }

}
