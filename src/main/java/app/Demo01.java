package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		//procesos... registrar un nuevo usuario
		Usuario u = new Usuario();
		u.setCodigo(7);
		u.setNombre("Nobara");
		u.setApellido("Jujutsu");
		u.setUsuario("nobara@gmail.com");
		u.setClave("jjk");
		u.setFnacim("2001/08/15");
		u.setTipo(1);
		u.setEstado(1);
		
		//reg, act, elim -> Transacciones
		
		try {
			em.getTransaction().begin();
			em.persist(u); //registrar
			em.getTransaction().commit();
			System.out.println("Registro OK");
		} catch (Exception e) {
			System.out.println("Error : " + e.getClass().getTypeName());
		}
		em.close();
		
	}
}
