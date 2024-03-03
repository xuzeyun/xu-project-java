package com.xeg.xms;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GeneratorCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/xms?characterEncoding=utf-8&userSSL=false", "root", "sql123456")
                .globalConfig(builder -> {
                    builder.author("xuzeyun")               // 设置作者
                            .disableOpenDir()               // 禁止打开输出目录
                            .outputDir("C:\\Users\\xzy\\pro\\xms\\src\\main\\java");    // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xeg")               // 设置父包名
                            .moduleName("xms")              // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\xzy\\pro\\xms\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user")
                            .addTablePrefix("sys_") // 设置过滤表前缀

                    // 1 entity
                    .entityBuilder()
                    .enableFileOverride()
                    // .enableLombok()
                    // .enableChainModel()
                    // .naming(NamingStrategy.underline_to_camel)
                    // .columnNaming(NamingStrategy.underline_to_camel)
                    // .idType(IdType.AUTO)
                    // .formatFileName("%s")
                    //
                    //
                    //
                    // // 2 controller
                    .controllerBuilder()
                    .enableFileOverride()
                    // .enableRestStyle()
                    //
                    // // 3 service
                    .serviceBuilder()
                    .enableFileOverride()
                    // .formatServiceFileName("%sService")
                    // .formatServiceImplFileName("%sServiceImp")
                    //
                    // // 4 mapper
                    .mapperBuilder()
                    .enableFileOverride();
                    // .enableMapperAnnotation()
                    // .enableBaseResultMap()          // 启用 BaseResultMap 生成
                    // .enableBaseColumnList()         // 启用 BaseColumnList
                    // .formatMapperFileName("%sMapper")
                    // .formatXmlFileName("%sMapper");
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
