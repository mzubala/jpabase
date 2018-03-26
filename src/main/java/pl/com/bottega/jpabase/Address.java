package pl.com.bottega.jpabase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

  @Id
  public Long id;

  public String line1, line2, city, zip, country;

  @OneToOne(mappedBy = "address")
  public User user;

}
