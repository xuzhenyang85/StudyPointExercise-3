package facade;

import entity.Persons;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class FacadePerson implements InterfaceFacadePerson
{

    private EntityManagerFactory emf;

    public FacadePerson()
    {

    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public Persons addPerson(Persons p)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Persons getPerson(Long id)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            Persons person = em.find(Persons.class, id);
            em.getTransaction().commit();
            return person;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Persons editPerson(Persons p)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            Persons person = em.find(Persons.class,p.getId());
            if(person != null){
                em.merge(p);
            }
            em.getTransaction().commit();
            return person;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Persons deletePerson(Long id)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            Persons person = em.find(Persons.class, id);
            if (person != null)
            {
                em.remove(person);
            }
            em.getTransaction().commit();
            return person;
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Persons> getPersons()
    {
        EntityManager em = emf.createEntityManager();
        List<Persons> persons = null;
        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("SELECT p FROM Persons p").getResultList();
            em.getTransaction().commit();
            return persons;
        } finally
        {
            em.close();
        }
    }

}
