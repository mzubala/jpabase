package pl.com.bottega.jpabase;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Zadanie13 extends TaskTemplate {

  public static void main(String[] args) {
    new Zadanie13().execute();
  }

  @Override
  protected void doTask() {
    executeInTx((em) -> {
      searchAuctions(em, Optional.of("new"), Optional.of(1L));
      searchAuctions(em, Optional.empty(), Optional.empty());
      searchAuctions(em, Optional.of("new"), Optional.empty());
      searchAuctions(em, Optional.empty(), Optional.of(1L));
    });
  }

  private List<Auction> searchAuctions(EntityManager em, Optional<String> query, Optional<Long> userId) {
    List<String> jpqlComponents = new LinkedList<>();
    jpqlComponents.add("SELECT DISTINCT(a) FROM Auction a");
    List<String> where = new LinkedList<>();
    query.ifPresent((q) -> where.add("(a.title LIKE :like OR a.description LIKE :like)"));
    userId.ifPresent((id) -> {
      jpqlComponents.add("JOIN a.bids b JOIN b.user u ");
      where.add("u.id = :userId");
    });
    if (where.size() > 0) {
      jpqlComponents.add("WHERE");
      jpqlComponents.add(String.join(" AND ", where));
    }
    Query q = em.createQuery(String.join(" ", jpqlComponents));
    query.ifPresent((phrase) -> q.setParameter("like", "%" + phrase + "%"));
    userId.ifPresent((id) -> q.setParameter("userId", id));
    return q.getResultList();
  }


}
