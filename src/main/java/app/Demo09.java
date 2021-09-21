package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo09 {
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		
		EntityManager em = fabrica.createEntityManager();
		
		//Usuario u = em.find(Usuario.class, 10); // busca segun PK
		// -- validar usando usuario y clave --
		
		//String sql = "{call usp_validaAcceso (:xusr, :xcla)}";
		String sql = "{call usp_validaAcceso (?, ?)}";
		
		Query query3 = em.createNativeQuery(sql, Usuario.class); //objeto
		//query3.setParameter("xusr", "U002@gmail.com");
		//query3.setParameter("xcla", "10002");
		
		query3.setParameter(1, "U002@gmail.com");
		query3.setParameter(2, "10002");
		
		Usuario u = null;
		try {
			u = (Usuario) query3.getSingleResult();
			System.out.println("Usuario encontrado : " + u.getNombre());
			System.out.println(u);
		} catch (NoResultException e) {
			System.out.println("Usuario NO existe");
		}
			em.close();
	}
}
