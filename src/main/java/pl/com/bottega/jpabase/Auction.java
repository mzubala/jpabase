package pl.com.bottega.jpabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Auction {

  @Id
  public Long id;

  public String title;

  @Column(length = 6000)
  public String description;

  public LocalDateTime start;

  public Integer duration;

  private Auction() {}

  public Auction(String title) {
    this.title = title;
  }

}
