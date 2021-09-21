package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Productos;

public class EliminarProductos {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
					
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
			
		//Eliminar productos por codigo
		Productos p = em.find(Productos.class, "P0033");
			
		if (p == null) {
			System.out.println("Producto NO existe");
		} else {
			System.out.println("Producto encontrado : " + p.getDescripcion());
			System.out.println(p);
			em.getTransaction().begin();
			em.remove(p); //eliminar
			em.getTransaction().commit();
			System.out.println("Eliminacion OK");
		} 
		em.close();
	}
}
