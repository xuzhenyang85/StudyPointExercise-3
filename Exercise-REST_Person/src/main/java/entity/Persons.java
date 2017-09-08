package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persons implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String fName;
    public String lName;
    public String phone;
    
    public Persons(){
        
    }

    public Persons(Long id, String fName, String lName, String phone)
    {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public Persons(String fName, String lName, String phone)
    {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }
    
    
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons))
        {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Person{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", phone=" + phone + '}';
    }

}
