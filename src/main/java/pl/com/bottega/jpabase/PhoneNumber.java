package pl.com.bottega.jpabase;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

  public String countryCode, number;

  public PhoneNumber() {}

  public PhoneNumber(String countryCode, String number) {
    this.countryCode = countryCode;
    this.number = number;
  }

}
