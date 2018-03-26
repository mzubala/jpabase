package pl.com.bottega.jpabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class Auction {

  @Id
  public Long id;

  public String title;

  @Column(length = 6000)
  public String description;

  public LocalDateTime start;

  public Integer duration;

  @OneToMany(mappedBy = "auction")
  public Collection<Bid> bids = new LinkedList<>();

  private Auction() {}

  public Auction(String title) {
    this.title = title;
  }

}
