package pl.com.bottega.jpabase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Bid {

  @Id
  public Long id;

  public BigDecimal amount;

  @ManyToOne
  public Auction auction;

  @ManyToOne
  public User user;

}
