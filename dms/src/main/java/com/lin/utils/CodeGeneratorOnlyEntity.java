package com.lin.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;

//只自动生成entity层代码
public class CodeGeneratorOnlyEntity {

    public static void main(String[] args) {

        String url = "jdbc:mysql://8.141.160.226:3306/dms?characterEncoding=utf8&useUnicode=true";
        String username = "root";
        String password = "betterlin599";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {//全局配置
                    builder.author("lyx") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:/project/eepcc/dms/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {//包配置


                    builder.parent("com.lin") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .serviceImpl("service");//设置包名
                    //pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径

                })

                .strategyConfig(builder -> {//策略配置
                    builder.addInclude("menu") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .addTablePrefix(" ") // 设置过滤表前缀
                            .serviceBuilder().formatServiceImplFileName("%sService");
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板

                .templateConfig(builder -> {//模板配置
                    builder.disable(TemplateType.SERVICE, TemplateType.CONTROLLER,TemplateType.SERVICEIMPL,TemplateType.MAPPER,TemplateType.XML);
                })
                .execute();

    }
}
