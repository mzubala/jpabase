package pl.com.bottega.jpabase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq")
  @SequenceGenerator(name = "bid_seq", sequenceName = "bid_seq")
  public Long id;

  public BigDecimal amount;

  @ManyToOne
  public Auction auction;

  @ManyToOne(fetch = FetchType.LAZY)
  public User user;

}
