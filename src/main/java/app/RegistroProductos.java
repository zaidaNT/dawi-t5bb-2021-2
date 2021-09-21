package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Productos;

public class RegistroProductos {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
				
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Registrar productos
		Productos p = new Productos();
		p.setIdprod("P0032");
		p.setDescripcion("Desodorante Rexona");
		p.setStock(20);
		p.setPrecio(8.50);
		p.setIdcategoria(5);
		p.setEstado(1);
		
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Registro OK");
		} catch (Exception e) {
			System.out.println("Error : " + e.getClass().getTypeName());
		}
		em.close();
	}
}
