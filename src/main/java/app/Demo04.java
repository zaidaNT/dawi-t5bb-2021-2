package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		//procesos... obtener la info del usuario con codigo 7
		Usuario u = em.find(Usuario.class, 10);
		
		if (u == null) {
			System.out.println("Usuario NO existe");
		} else {
			System.out.println("Usuario encontrado : " + u.getNombre());
			System.out.println(u);
		}
		
		em.close();
		
		// para consultas y listados no es necesario las transacciones = try
	}
}
