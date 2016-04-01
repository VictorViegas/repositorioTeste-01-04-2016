package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.sceweb.servico.FabricaDeConexoes;

public class EmpresaDAO {

	public int adiciona(Empresa empresa){
		PreparedStatement ps;
		int codigoRetorno = 0;
		
		try (Connection con = new FabricaDeConexoes().getConnection()){
			ps = con.prepareStatement("INSERT INTO empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) VALUES (?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}
	
	public int exclui(Empresa empresa){
		PreparedStatement ps;
		int codigoRetorno = 0;
		
		try (Connection con = new FabricaDeConexoes().getConnection()){
			ps = con.prepareStatement("DELETE FROM empresa WHERE cnpj like ?");
			ps.setString(1, empresa.getCnpj());
			codigoRetorno = ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}
	
}
