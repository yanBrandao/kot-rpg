package br.com.woodriver.rpg.adapter.output.document

//
//@Configuration
//@EnableSwagger2
//class SwaggerConfiguration {
//    @Value("\${service.title}")
//    lateinit var title: String
//
//    @Value("\${service.description}")
//    lateinit var description: String
//
//    @Value("\${service.version}")
//    lateinit var version: String
//
//
//    @Bean
//    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("br.com.woodriver.rpg"))
//            .paths(PathSelectors.any())
//            .build()
//            .apiInfo(metaData())
//
//    @Bean
//    fun metaData(): ApiInfo{
//        return ApiInfoBuilder()
//                .title(title)
//                .description(description)
//                .version(version)
//                .license(LICENSE)
//                .licenseUrl(LICENSE_URL)
//                .contact(Contact(MAKER_NAME, MAKER_URL, MAKER_EMAIL))
//                .build()
//
//    }
//
//    companion object{
//        const val LICENSE = ""
//        const val LICENSE_URL = ""
//        const val MAKER_NAME = "Yan Tapajós"
//        const val MAKER_URL = "https://yan-tapajos.web.app/"
//        const val MAKER_EMAIL = "ybrandao.d@gmail.com"
//    }
//}
