package pl.com.bottega.jpabase;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Auction extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  public String title;

  @Column(length = 6000)
  public String description;

  public LocalDateTime start;

  public Integer duration;

  @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
  @BatchSize(size = 13)
  public List<Bid> bids = new LinkedList<>();

  @Enumerated(EnumType.STRING)
  public AuctionStatus auctionStatus = AuctionStatus.WAITING;

  private Auction() {}

  public Auction(String title) {
    this.title = title;
  }

}
