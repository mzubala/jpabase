package pl.com.bottega.jpabase;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  @PrePersist
  void setCreatedAt() {
    createdAt = updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  void setUpdatedAt() {
    updatedAt = LocalDateTime.now();
  }
}
