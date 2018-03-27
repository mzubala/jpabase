package pl.com.bottega.jpabase;

import javax.persistence.*;

@Entity
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
  @SequenceGenerator(name="address_seq", sequenceName = "address_seq")
  public Long id;

  public String line1, line2, city, zip, country;

  @OneToOne(mappedBy = "address")
  public User user;

}
