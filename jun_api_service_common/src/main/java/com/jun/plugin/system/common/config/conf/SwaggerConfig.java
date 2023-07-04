//@Configuration
//@EnableSwagger2WebMvc
//public class SwaggerConfig {
//
//    private List<Parameter> getParameters() {
//        Parameter parameter = new ParameterBuilder()
//                .name("Authorization")
//                .description("token令牌")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//
//        List<Parameter> parameters = CollectionUtil.newArrayList();
//        parameters.add(parameter);
//        return parameters;
//    }
//
//    @Bean
//    public Docket defaultApi() {
//        List<Parameter> parameters = getParameters();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(defaultApiInfo())
//                .groupName("默认接口")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(CommonConstant.DEFAULT_PACKAGE_NAME))
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(parameters);
//    }
//
//    private ApiInfo defaultApiInfo() {
//        return new ApiInfoBuilder()
//                .title("Snowy Doc")
//                .description("Snowy Doc文档")
//                .termsOfServiceUrl("https://www.xiaonuo.vip")
//                .contact(new Contact("xuyuxiang, yubaoshan, dongxiayu", "https://www.xiaonuo.vip", ""))
//                .version("1.0")
//                .build();
//    }
//
//    /**
//     * 想分组请放开注释
//     */
//
//    // @Bean
//    // public Docket groupRestApi() {
//    //     List<Parameter> parameters = getParameters();
//    //     return new Docket(DocumentationType.SWAGGER_2)
//    //             .apiInfo(groupApiInfo())
//    //             .groupName("自定义")
//    //             .select()
//    //             //TODO 这里改为自己的包名
//    //             .apis(RequestHandlerSelectors.basePackage("com.example.XXX"))
//    //             .paths(PathSelectors.any())
//    //             .build()
//    //             .globalOperationParameters(parameters);
//    // }
//    //
//    // private ApiInfo groupApiInfo() {
//    //     return new ApiInfoBuilder()
//    //             .title("自定义")
//    //             .description("自定义API")
//    //             .termsOfServiceUrl("http://www.example.com/")
//    //             .version("1.0")
//    //             .build();
//    // }
//
//}
