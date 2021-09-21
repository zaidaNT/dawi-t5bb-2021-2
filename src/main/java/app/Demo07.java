package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Categorias;

public class Demo07 {
	public static void main(String[] args) {
		//1. Especificar la conexion de BD
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
					
		//2. Obtener DAO
		EntityManager em = fabrica.createEntityManager();
		
		TypedQuery<Categorias> query = em.createQuery("Select c from Categorias c", Categorias.class);
		List<Categorias> lstCategorias = query.getResultList();
		System.out.println("Cantidad de Categorias : " + lstCategorias.size());
		
		if (lstCategorias.size() == 0) { 
			System.out.println("Listado vacio");
		} else {
			System.out.println("-- Listado de categoria --");
			for (Categorias c : lstCategorias) {
				System.out.println(">>> " + c);
			}
		}
		em.close();
	}
}
