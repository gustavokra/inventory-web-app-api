package com.kraemer.domain.entities.enums;

import com.kraemer.domain.utils.EnumUtil;

public enum EnumErrorCode  implements IEnum {
      // Internal errors
      CAMPO_OBRIGATORIO("001", "O campo {0} é obrigatório!", 400),
      ENTIDADE_CADASTRADA("002", "{0} já possui cadastro ativo!", 400),
      ENTIDADE_NAO_ENCONTRADA("003", "Não foi encontrado {0} com filtros {1} valor {2}", 400),
      CAMPO_INVALIDO("004", "Campo {0} inválido!", 404),
      ERRO_AO_EXCLUIR("005", "Erro ao excluir {0} com {1} valor {2}", 404),
      // External errors
      ERRO_COMUNICACAO("050", "A requisição enviada ao parceiro retornou com erro!", 502);
  
      private final String key;
  
      private final String erro;
  
      private final int httpStatus;
  
      private EnumErrorCode(String key, String error, int httpStatus) {
          this.key = key;
          this.erro = error;
          this.httpStatus = httpStatus;
      }
  
      @Override
      public String getKey() {
          return key;
      }
  
      public String getErro() {
          return erro;
      }
  
      public int getHttpStatus() {
          return httpStatus;
      }
  
      @Override
      public boolean containsInEnum(String key) {
          return parseByKey(key) != null;
      }
  
      public static EnumErrorCode parseByKey(String key) {
          return (EnumErrorCode) EnumUtil.parseByKey(EnumErrorCode.class, key);
      }
}
