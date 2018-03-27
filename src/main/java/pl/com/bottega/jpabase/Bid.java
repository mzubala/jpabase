package pl.com.bottega.jpabase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {

  @Id
  public Long id;

  public BigDecimal amount;

  @ManyToOne
  public Auction auction;

  @ManyToOne(fetch = FetchType.LAZY)
  public User user;

}
