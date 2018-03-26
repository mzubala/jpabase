package pl.com.bottega.jpabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Zadanie2 {

  public static void main(String[] args) {
    EntityManagerFactory emf = null;
    try {
      emf = Persistence.createEntityManagerFactory("DMS");
      EntityManager em = emf.createEntityManager();
      Auction auction = new Auction("first");
      auction.id = 1L;
      auction.description = "auction description";
      auction.start = LocalDateTime.now();
      auction.duration = 7;
      try {
        em.getTransaction().begin();
        em.persist(auction);
        auction.title = "test";
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Auction auctionFetched = em.find(Auction.class, 1L);
        auctionFetched.title = "new title";
        em.getTransaction().commit();
        System.out.println(auctionFetched.title);
      }
      catch (RuntimeException ex) {
        em.getTransaction().rollback();
        throw ex;
      }
    }
    finally {
      if(emf != null) {
        emf.close();
      }
    }
  }

}
