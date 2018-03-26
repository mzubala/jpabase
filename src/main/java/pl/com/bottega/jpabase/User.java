package pl.com.bottega.jpabase;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class User {

  @Id
  public Long id;

  public String name;

  @OneToMany(mappedBy = "user")
  public Collection<Bid> bids = new LinkedList<>();

  @OneToOne
  public Address address;

}
