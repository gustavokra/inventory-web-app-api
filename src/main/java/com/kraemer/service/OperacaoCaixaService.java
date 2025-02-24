package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.OperacaoCaixaBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.mappers.OperacaoCaixaMapper;
import com.kraemer.domain.usecases.operacaocaixa.AtualizarOperacaoCaixa;
import com.kraemer.domain.usecases.operacaocaixa.CriarOperacaoCaixa;
import com.kraemer.domain.usecases.operacaocaixa.EncontrarTodasOperacoesCaixa;
import com.kraemer.domain.usecases.operacaocaixa.ExcluirOperacaoCaixa;
import com.kraemer.domain.vo.QueryFieldInfoVO;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperacaoCaixaService extends AbstractService {
    
    public OperacaoCaixaDTO criar(OperacaoCaixaDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);
        
        var criarOperacaoCaixa = new CriarOperacaoCaixa(repository);
        
        return criarOperacaoCaixa.execute(dto);
    }
    
    public OperacaoCaixaDTO atualizar(OperacaoCaixaDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);
        
        var atualizarOperacaoCaixa = new AtualizarOperacaoCaixa(repository);
        
        return atualizarOperacaoCaixa.execute(dto, id);
    }
    
    public List<OperacaoCaixaDTO> buscarTodos(EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);

        var encontrarTodasOperacoesCaixa = new EncontrarTodasOperacoesCaixa(repository);
        
        return encontrarTodasOperacoesCaixa.execute();
    }
    
    public void excluir(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);

        var excluirOperacaoCaixa = new ExcluirOperacaoCaixa(repository);

        excluirOperacaoCaixa.execute(id);
    }

} 