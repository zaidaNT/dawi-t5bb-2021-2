package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		//procesos... eliminar el usuario con codigo 7
		Usuario u = new Usuario();
		u.setCodigo(7);
		//u.setNombre("Nobara");
		//u.setApellido("Kaisen");
		
		//reg, act, elim -> Transacciones
		
		try {
			em.getTransaction().begin();
			em.remove(u); //eliminar
			em.getTransaction().commit();
			System.out.println("Eliminacion OK");
		} catch (Exception e) {
			System.out.println("Error : " + e.getClass().getTypeName());
		}
		em.close();
		
	}
}
