package br.com.sysresto.relatorio;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.jdbc.Work;

public class ExecutorRelatorio implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private List<? extends Object> list;
	private Map<String, Object> parametros;
	
	public ExecutorRelatorio(String caminhoRelatorio, List<? extends Object> list, 
			Map<String, Object> parametros, HttpServletResponse response) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.list = list;
		this.response = response;
		this.parametros = parametros;
	}

	public void execute(Connection connection) throws SQLException {
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
	
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(list);
			
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, datasource);
			JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
			
			response.setContentType("application/pdf");
		
		} catch(Exception e) {
			throw new SQLException("Erro ao executar o relatório " + this.caminhoRelatorio, e);
		}
	}

}
