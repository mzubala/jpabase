package pl.com.bottega.jpabase;

import java.math.BigDecimal;

public class Zadanie9 extends TaskTemplate {
  @Override
  protected void doTask() {
    User user = new User();
    user.name = "Janek";

    Address address = new Address();
    address.city = "Warszawa";
    user.address = address;
    address.user = user;
    PhoneNumber p = new PhoneNumber("+48", "666");
    user.workPhone = p;
    user.privatePhone = p;

    executeInTx((em) -> {
      em.persist(user);
    });

  }

  public static void main(String[] args) {
    new Zadanie9().execute();
  }
}
