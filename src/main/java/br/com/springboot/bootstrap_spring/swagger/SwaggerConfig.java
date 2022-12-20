package br.com.springboot.bootstrap_spring.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket producerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.springboot.bootstrap_spring.controllers"))
                .paths(PathSelectors.any())
                .build()
                .globalResponses(HttpMethod.GET, responseMessageForGET())
                .useDefaultResponseMessages(false)
                .apiInfo(metaInfo());

    }

    private List<Response> responseMessageForGET() {
        return new ArrayList<Response>() {
            {
                add(new ResponseBuilder()
                        .code("500")
                        .description("500 message")
                        .build());
                add(new ResponseBuilder()
                        .code("403")
                        .description("Forbidden!")
                        .build());
            }
        };
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "API REST CRUD Completo Spring boot + JQuery + Bootstrap",
                "Treinamento de CRUD completo com Bootstrap e JQuery",
                "3.0",
                "Terms of Service",
                new Contact("Alisson Ferreira", "https://alissonferreiraevangelista.github.io/",
                        "alisson.22559@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",new ArrayList<>());

        return apiInfo;
    }
}
