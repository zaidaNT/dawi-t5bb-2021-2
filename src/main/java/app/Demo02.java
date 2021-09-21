package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		//procesos... actualizar un usuario
		Usuario u = new Usuario();
		u.setCodigo(7);
		u.setNombre("Nobara");
		u.setApellido("Kaisen");
		u.setUsuario("nob@gmail.com");
		u.setClave("jujutsu");
		u.setFnacim("2020/08/15");
		u.setTipo(1);
		u.setEstado(1);
		
		//reg, act, elim -> Transacciones
		
		try {
			em.getTransaction().begin();
			em.merge(u); //actualizar -> si existe cod / si no existe lo crea
			em.getTransaction().commit();
			System.out.println("Actualizacion OK");
		} catch (Exception e) {
			System.out.println("Error : " + e.getClass().getTypeName());
		}
		em.close();
		
	}
}
