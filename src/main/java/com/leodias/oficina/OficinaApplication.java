package com.leodias.oficina;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leodias.oficina.entity.Cliente;
import com.leodias.oficina.entity.Endereco;
import com.leodias.oficina.entity.Servico;
import com.leodias.oficina.entity.Telefone;
import com.leodias.oficina.entity.Veiculo;
import com.leodias.oficina.entity.Venda;
import com.leodias.oficina.repositories.ClienteRepository;
import com.leodias.oficina.repositories.EnderecoRepository;
import com.leodias.oficina.repositories.ServicoRepository;
import com.leodias.oficina.repositories.TelefoneRepository;
import com.leodias.oficina.repositories.VeiculoRepository;
import com.leodias.oficina.repositories.VendaRepository;

@SpringBootApplication
public class OficinaApplication implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		SpringApplication.run(OficinaApplication.class, args);
	}
	
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private TelefoneRepository telefoneRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private VeiculoRepository veiculoRepo;
	@Autowired
	private ServicoRepository servicoRepo;
	@Autowired
	private VendaRepository vendaRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Endereco e1 = new Endereco(null, "Rua Pacifico Gabriel da Silva", "Jardim Bela Vista", "37775000", "Santa Rita de Caldas", "Minas Gerais");
		
		Cliente c1 = new Cliente(null, "Leonardo Dias", "12345678900", sdf.parse("01/09/1981"), "Doraci Maria de Carvalho", "136", e1);
		Cliente c2 = new Cliente(null, "Patricia Silva", "98765432100", sdf.parse("25/08/1983") , "Adair Siqueira da Silva", "136", e1);
		Cliente c3 = new Cliente(null, "Lara Dias Carvalho Silva", "12356789044", sdf.parse("03/04/2012"), "Patricia Silva", "136", e1);
		
		Telefone t1 = new Telefone(null, "998415510", c1);
		Telefone t2 = new Telefone(null, "998729494", c2);
		Telefone t3 = new Telefone(null, "999258794", c3);
		Telefone t4 = new Telefone(null, "37341023", c1);
		
		c1.getTelefones().addAll(Arrays.asList(t1, t4));
		c2.getTelefones().addAll(Arrays.asList(t2));
		c3.getTelefones().addAll(Arrays.asList(t3));
		
		Veiculo v1 = new Veiculo(null, "eag8064", "Siena", "elx 1.4", "Fiat", "2010", c1);
		Veiculo v2 = new Veiculo(null, "hhh7890", "Fiesta", "Sedan 1.6", "Ford", "2011", c3);
		Veiculo v3 = new Veiculo(null, "aaa1234", "Corolla", "el 1.8 automatico", "Toyota", "2015", c2);
		
		Servico s1 = new Servico(null, "Troca de oléo", 100.00);
		Servico s2 = new Servico(null, "Troca de amortecedores dianteiros", 350.00);
		Servico s3 = new Servico(null, "Troca de amortecedores traseiros", 450.00);
		
		Venda venda1 = new Venda(null, new Date(), 0.0, s1.getPreco(), c3);
		Venda venda2 = new Venda(null, new Date(), 50.00, s3.getPreco() , c1);
		
		venda1.getServicos().addAll(Arrays.asList(s1));
		venda2.getServicos().addAll(Arrays.asList(s3));
		
		servicoRepo.saveAll(Arrays.asList(s1, s2, s3));
		enderecoRepo.saveAll(Arrays.asList(e1));
		clienteRepo.saveAll(Arrays.asList(c1, c2, c3));
		telefoneRepo.saveAll(Arrays.asList(t1, t2, t3, t4));
		veiculoRepo.saveAll(Arrays.asList(v1, v2, v3));
		vendaRepo.saveAll(Arrays.asList(venda1, venda2));
		
	}

}
