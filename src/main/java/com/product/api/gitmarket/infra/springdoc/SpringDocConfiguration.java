package com.product.api.gitmarket.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("GitMarket API")
                        .description(descricaoDaApi())
                        .contact(new Contact()
                                .name("Time Backend GitMarket")
                                .email("oliveira.joaov@proton.me"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://gitmarket.com/api/licenca")));
    }

    private String descricaoDaApi() {
        return """
                API RESTful desenvolvida para a plataforma de e-commerce **GitMarket**.
                
                Esta documentação fornece acesso a todos os recursos públicos e protegidos da aplicação.
                
                ### 🚀 Funcionalidades Principais
                * **Gestão de Produtos:** Criação, listagem e atualização de inventário.
                * **Categorização:** Organização de produtos por departamentos.
                * **Controle de Acesso:** Registro e autenticação de usuários via Tokens JWT.
                
                ### 🔒 Autenticação
                A maioria dos endpoints é protegida. Para testar:
                1.  Crie um usuário ou faça login no endpoint `/api/auth/login`.
                2.  Copie o **Token JWT** gerado.
                3.  Clique no botão **Authorize** (cadeado) acima e cole o token (ex: `Bearer seu_token`).
                """;
    }
}