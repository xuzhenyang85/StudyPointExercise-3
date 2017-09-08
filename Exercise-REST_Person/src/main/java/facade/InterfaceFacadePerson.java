package facade;

import entity.Persons;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface InterfaceFacadePerson
{

    public void addEntityManagerFactory(EntityManagerFactory emf);

    public Persons addPerson(Persons p);

    public Persons deletePerson(Long id);

    public Persons getPerson(Long id);

    public List<Persons> getPersons();

    public Persons editPerson(Persons p);
}
