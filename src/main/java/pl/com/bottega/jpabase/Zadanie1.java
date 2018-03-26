package pl.com.bottega.jpabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Zadanie1 {
  public static void main(String[] args) {
    EntityManagerFactory emf = null;
    try {
      emf = Persistence.createEntityManagerFactory("DMS");
      EntityManager em = emf.createEntityManager();

    }
    finally {
      if(emf != null) {
        emf.close();
      }
    }
  }
}
