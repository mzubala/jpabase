package pl.com.bottega.jpabase;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.List;

public class NP1Test extends TaskTemplate {
  @Override
  protected void doTask() {
    int n = 2;
    for (int i = 0; i < n; i++) {
      User user = new User();
      user.address = new Address();
      user.address.city = "Wrocław";
      user.address.user = user;
      executeInTx((em) -> {
        em.persist(user);
      });
    }

    executeInTx((em) -> {
      SessionFactory factory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
      Statistics stats = factory.getStatistics();
      stats.setStatisticsEnabled(true);
      List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
      for(User u : users) {
        System.out.println(u.address.city);
      }
      System.out.println("Ilość zapytań: " + stats.getPrepareStatementCount());
    });
  }

  public static void main(String[] args) {
    new NP1Test().execute();
  }

}
