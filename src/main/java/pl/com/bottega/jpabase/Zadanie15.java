package pl.com.bottega.jpabase;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

public class Zadanie15 extends TaskTemplate {

  public static void main(String[] args) {
    new Zadanie15().execute();
  }

  @Override
  protected void doTask() {
    executeInTx((em) -> {
      searchAuctions(em, Optional.of("new"), Optional.of(1L), 3L, 20L);
      searchAuctions(em, Optional.empty(), Optional.empty(), 3L, 20L);
      searchAuctions(em, Optional.of("new"), Optional.empty(), 3L, 20L);
      searchAuctions(em, Optional.empty(), Optional.of(1L), 3L, 20L);
    });
  }

  private List<Auction> searchAuctions(EntityManager em, Optional<String> query,
                                       Optional<Long> userId, Long pageNr, Long perPage) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
    Root<Auction> au = cq.from(Auction.class);
    cq.distinct(true);
    Predicate predicate = cb.conjunction();
    if(query.isPresent()) {
      predicate = cb.and(predicate, cb.or(
          cb.like(au.get("title"), '%' + query.get() + '%'),
          cb.like(au.get("description"), '%' + query.get() + '%')
      ));
    }
    if(userId.isPresent()) {
      Join<Auction, Bid> bid = au.join("bids");
      Join<Bid, User> user = bid.join("user");
      predicate = cb.and(predicate, cb.equal(user.get("id"), userId.get()));
    }
    cq.where(predicate);
    Query q = em.createQuery(cq);
    q.setMaxResults(Math.toIntExact(perPage));
    q.setFirstResult(Math.toIntExact((pageNr - 1) * perPage));
    return q.getResultList();
  }


}
