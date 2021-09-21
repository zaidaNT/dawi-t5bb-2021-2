package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categorias;
import model.Productos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtCódigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tblSalida;
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 109, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 134, 77, 20);
		contentPane.add(txtPrecio);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 416, 143);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Descripción");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Estado");		
		scrollPane.setViewportView(tblSalida);
		
		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();
		
		TypedQuery<Categorias> query = em.createQuery("Select c from Categorias c", Categorias.class);
		List<Categorias> lstCategorias = query.getResultList();
		
		cboCategorias.addItem("Seleccione");
		
		for (Categorias c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		em.close();
	}
	
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();
		
		String sql = "Select p from Productos p";
		TypedQuery<Productos> query = em.createQuery(sql, Productos.class);
		
		List<Productos> lstProductos = query.getResultList();
		
		if (lstProductos.size() == 0) {
			JOptionPane.showMessageDialog(null, "Lista vacía");
		} else {
			for (Productos p : lstProductos) {
				Object datos[] = {p.getIdprod(), p.getDescripcion(), p.getStock(),
								  p.getPrecio(), p.getIdcategoria(), p.getEstado()};
				modelo.addRow(datos);
			}
		}
		em.close();
	}
	
	void registrar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();
		
		if (cboCategorias.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Seleccione una categoria para el producto.");
			return;
		}
		Productos p = null;
		try {
			p = new Productos(txtCódigo.getText(), txtDescripcion.getText(), Integer.parseInt(txtStock.getText()),
					Double.parseDouble(txtPrecio.getText()), cboCategorias.getSelectedIndex(), 1);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Complete los campos correctamente.");
			return;
		}
			
		/*
		 * Productos p = null;
		 * 
		 * String idprod = txtCódigo.getText(); String descripcion =
		 * txtDescripcion.getText(); int idcategoria = cboCategorias.getSelectedIndex();
		 * int stock = Integer.parseInt(txtStock.getText()); double precio =
		 * Double.parseDouble(txtPrecio.getText());
		 * 
		 * String sql = "{insert into tb_productos values (?,?,?,?,?,1)}"; TypedQuery
		 * <Productos> query4 = em.createQuery(sql, Productos.class);
		 * 
		 * query4.setParameter(1, idprod); query4.setParameter(2, descripcion);
		 * query4.setParameter(3, stock); query4.setParameter(4, precio);
		 * query4.setParameter(5, idcategoria);
		 */
		
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(this, "Registro OK");
			//System.out.println("Registro OK");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se logro registrar");
			//System.out.println("Error : " + e.getClass().getTypeName());
		}
		em.close(); 
	}
}
