package pl.com.bottega.jpabase;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Auction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  public String title;

  @Column(length = 6000)
  public String description;

  public LocalDateTime start;

  public Integer duration;

  @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Bid> bids = new LinkedList<>();

  private Auction() {}

  public Auction(String title) {
    this.title = title;
  }

}
