package com.jonasoliveira.lojaweb;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonasoliveira.lojaweb.domain.Categoria;
import com.jonasoliveira.lojaweb.domain.Cidade;
import com.jonasoliveira.lojaweb.domain.Cliente;
import com.jonasoliveira.lojaweb.domain.Endereco;
import com.jonasoliveira.lojaweb.domain.Estado;
import com.jonasoliveira.lojaweb.domain.Pagamento;
import com.jonasoliveira.lojaweb.domain.PagamentoComBoleto;
import com.jonasoliveira.lojaweb.domain.PagamentoComCartao;
import com.jonasoliveira.lojaweb.domain.Pedido;
import com.jonasoliveira.lojaweb.domain.Produto;
import com.jonasoliveira.lojaweb.domain.enums.EstadoPagamento;
import com.jonasoliveira.lojaweb.domain.enums.TipoCliente;
import com.jonasoliveira.lojaweb.repositories.CategoriaRepository;
import com.jonasoliveira.lojaweb.repositories.CidadeRepository;
import com.jonasoliveira.lojaweb.repositories.ClienteRepository;
import com.jonasoliveira.lojaweb.repositories.EnderecoRepository;
import com.jonasoliveira.lojaweb.repositories.EstadoRepository;
import com.jonasoliveira.lojaweb.repositories.PagamentoRepository;
import com.jonasoliveira.lojaweb.repositories.PedidoRepository;
import com.jonasoliveira.lojaweb.repositories.ProdutoRepository;

@SpringBootApplication
public class LojawebApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoReposiory;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojawebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/* Instanciando categorias e produtos*/
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		/* Instanciando cidades e estados*/
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlâdia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		/* Instanciando Clientes e endereços*/
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27368954","93865748"));
		
		Endereco e1 = new Endereco(null, "Rua Flores do campo", "300", "Apto 303", "Jardim Belo", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Atlântica", "200", "Sala 800", "Centro", "38220812", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoReposiory.saveAll(Arrays.asList(e1,e2));
		
		/* Instanciando Pedidos*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("24/04/2020 17:44"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/04/2019 20:44"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 00:20"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
