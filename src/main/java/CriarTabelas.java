import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class CriarTabelas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SysResto");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tra = manager.getTransaction();
		
		tra.begin();
		
		tra.commit();
	}

}
