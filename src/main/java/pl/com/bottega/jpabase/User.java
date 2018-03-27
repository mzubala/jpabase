package pl.com.bottega.jpabase;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class User {

  @Id
  @TableGenerator(
      name = "user_id_table",
      table = "user_id_table",
      initialValue = 1,
      pkColumnName = "id"
  )
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_id_table")
  public Long id;

  public String name;

  @OneToMany(mappedBy = "user")
  public Collection<Bid> bids = new LinkedList<>();

  @OneToOne(cascade = CascadeType.ALL)
  public Address address;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "countryCode", column = @Column(name = "work_phone_country_code")),
      @AttributeOverride(name = "number", column = @Column(name = "work_phone_number"))
  })
  public PhoneNumber workPhone;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "countryCode", column = @Column(name = "private_phone_country_code")),
      @AttributeOverride(name = "number", column = @Column(name = "private_phone_number"))
  })
  public PhoneNumber privatePhone;

  @ElementCollection
  private Collection<PhoneNumber> numbers;

  @ElementCollection
  private Collection<Integer> happyNumbers;

}
