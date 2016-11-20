package edu.sjsu.cmpe275.lab2.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {
	
	private Class<T> persistentClass;
	
	@PersistenceContext(unitName = "CMPE275LAB2")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	
	protected EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	protected void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public Class<T> getPersistentClass() {
		return this.persistentClass;
	}
	
	public void setPersistentClass(Class< T > clazzToSet){
		this.persistentClass = clazzToSet;
    }
	
	public T findById(PK id) {
		int userid=0;
		try{
			/*entityManager.getTransaction().begin();*/
			 userid= Integer.parseInt(id.toString());
			 System.out.println("In find By ID");
			if(this.getEntityManager().find(getPersistentClass(), userid) != null){
					T entity = (T) this.getEntityManager().find(getPersistentClass(), userid);
					return entity;	
			}
		}
		catch (NumberFormatException e) {
		    System.out.println("Exception while parsing the user id to string: "+e);
		  }
		catch(RollbackException e)
		{	
			System.out.println("Rollback Exception in findById");
			System.out.println(this.persistentClass.getName()+" not found!");
			return null;
		}
		catch(Exception e){
			System.out.println("Exception General ");
			e.printStackTrace();
			System.out.println(this.persistentClass.getName()+" not found!");
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.entityManager.createQuery( "from " + this.persistentClass.getName()).getResultList();
	}

	public T save(T entity) {
		try{ 
			System.out.println("I am in the save method..");
			this.getEntityManager().persist(entity);	
			return entity;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public T update(T entity) {
		T mergedEntity = this.getEntityManager().merge(entity);
		return mergedEntity;
	}
	
	public void deleteById(PK id ){
		T entity = this.findById(id);
      	this.delete(entity);
	}

	public void delete( T entity ){
		this.entityManager.remove( entity );
    }
	
	public void flush() {
		this.getEntityManager().flush();
	}
	
	public boolean checkById(PK id) {
		int userid = 0;
		try{
			/*entityManager.getTransaction().begin();*/
			try {
			    userid= Integer.parseInt(id.toString());
			  } catch (NumberFormatException e) {
			    System.out.println("Exception while parsing the user string to id: "+e);
			  }
			if(this.getEntityManager().find(getPersistentClass(), userid) != null){
					return true;	
			}else{
				System.out.println("");
				System.out.println(this.persistentClass.getName()+" not found!");
				return false;
			}
		}catch(RollbackException e)
		{
			System.out.println("In here");
			System.out.println(this.persistentClass.getName()+" not found!");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(this.persistentClass.getName()+" not found!");
			return false;
		}
	}
	
}
