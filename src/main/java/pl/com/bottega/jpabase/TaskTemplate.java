package pl.com.bottega.jpabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class TaskTemplate {

  protected EntityManagerFactory emf;

  private final String unitName;

  public TaskTemplate() {
    this("DMS");
  }

  public TaskTemplate(String unitName) {
    this.unitName = unitName;
  }

  public void execute() {
    try {
      emf = Persistence.createEntityManagerFactory(unitName);
      doTask();
    } finally {
      if (emf != null)
        emf.close();
    }
  }

  public <ReturnT> ReturnT executeInTx(Function<EntityManager, ReturnT> consumer) {
    EntityManager entityManager = emf.createEntityManager();
    try {
      entityManager.getTransaction().begin();
      ReturnT result = consumer.apply(entityManager);
      entityManager.getTransaction().commit();
      entityManager.close();
      return result;
    } catch (RuntimeException ex) {
      entityManager.getTransaction().rollback();
      throw ex;
    }
  }

  public void executeInTx(Consumer<EntityManager> consumer) {
    executeInTx((em) -> {
      consumer.accept(em);
      return null;
    });
  }

  abstract protected void doTask();

}
