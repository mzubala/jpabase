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

}
