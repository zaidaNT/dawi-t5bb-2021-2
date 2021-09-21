package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Productos;

public class ListarProductos {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
							
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		// listado de parametros -> listado de productos x precio
		String sql = "Select p from Productos p where p.idcategoria = :xidcategoria";
		TypedQuery<Productos> query = em.createQuery(sql, Productos.class);
		query.setParameter("xidcategoria", 1);
		
		List<Productos> lstProductos = query.getResultList();
		
		System.out.println("Cantidad de productos : " + lstProductos.size());
		
		if (lstProductos.size() == 0) {
			System.out.println("Listado vacio");
		} else {
			System.out.println("-- Listado de categoria --");
			for (Productos p : lstProductos) {
				System.out.println(">>> " + p);
			}
		}
		em.close();
	}
}
