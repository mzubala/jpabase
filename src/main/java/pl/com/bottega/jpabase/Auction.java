package pl.com.bottega.jpabase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Auction {

  @Id
  public Long id;

  public String title;

  @Column(length = 6000)
  public String description;

  public LocalDateTime start;

  public Integer duration;

  @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
  @OrderColumn
  public List<Bid> bids = new LinkedList<>();

  private Auction() {}

  public Auction(String title) {
    this.title = title;
  }

}
