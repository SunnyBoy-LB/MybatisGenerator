package com.lgb.MybatisGenerator;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.AnnotatedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.MixedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.mybatis.generator.codegen.mybatis3.model.PrimaryKeyGenerator;
import org.mybatis.generator.codegen.mybatis3.model.RecordWithBLOBsGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.internal.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class IntrospectedTableMyBatis3Impl extends org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl {
    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("_SqlMapper.xml");
        return sb.toString();
    }

    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf = new GeneratedXmlFile(document, getMyBatis3XmlMapperFileName(),
                    getMyBatis3XmlMapperPackage(), context.getSqlMapGeneratorConfiguration().getTargetProject(), false,
                    context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answer.add(gxf);
            }
        }
        return answer;
    }

    @Override
    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }
        String type = context.getJavaClientGeneratorConfiguration().getConfigurationType();
        AbstractJavaClientGenerator javaGenerator;
        if ("XMLMAPPER".equalsIgnoreCase(type)) {
            javaGenerator = new JavaMapperGenerator();
        } else if ("MIXEDMAPPER".equalsIgnoreCase(type)) {
            javaGenerator = new MixedClientGenerator();
        } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) {
            javaGenerator = new AnnotatedClientGenerator();
        } else if ("MAPPER".equalsIgnoreCase(type)) {
            javaGenerator = new JavaMapperGenerator();
        } else {
            javaGenerator = (AbstractJavaClientGenerator) ObjectFactory.createInternalObject(type);
        }

        return javaGenerator;
    }

    @Override
    protected void calculateJavaClientAttributes() {
        if (this.context.getJavaClientGeneratorConfiguration() == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(calculateJavaClientImplementationPackage());
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("DAOImpl");
        setDAOImplementationType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("DAO");
        setDAOInterfaceType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("DAO");
        setMyBatis3JavaMapperType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("SqlProvider");
        setMyBatis3SqlProviderType(sb.toString());
    }

    @Override
    protected void calculateModelAttributes() {
        String pakkage = calculateJavaModelPackage();

        StringBuilder sb = new StringBuilder();
        sb.append(pakkage);
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("Key");
        setPrimaryKeyType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        setBaseRecordType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("WithBLOBs");
        setRecordWithBLOBsType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("Example");
        setExampleType(sb.toString());
    }

    @Override
    protected void calculateJavaModelGenerators(final List<String> warnings, final ProgressCallback progressCallback) {
        if (getRules().generateExampleClass()) {
            AbstractJavaGenerator javaGenerator = new ExampleGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
            this.javaModelGenerators.add(javaGenerator);
        }
        if (getRules().generatePrimaryKeyClass()) {
            AbstractJavaGenerator javaGenerator = new PrimaryKeyGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);

            this.javaModelGenerators.add(javaGenerator);
        }

        if (getRules().generateBaseRecordClass()) {
            AbstractJavaGenerator javaGenerator = new BaseRecordGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);

            this.javaModelGenerators.add(javaGenerator);
        }

        if (getRules().generateRecordWithBLOBsClass()) {
            AbstractJavaGenerator javaGenerator = new RecordWithBLOBsGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);

            this.javaModelGenerators.add(javaGenerator);
        }
    }

    @Override
    protected void calculateXmlMapperGenerator(final AbstractJavaClientGenerator javaClientGenerator,
            final List<String> warnings, final ProgressCallback progressCallback) {
        if (javaClientGenerator == null) {
            if (this.context.getSqlMapGeneratorConfiguration() != null) {
                this.xmlMapperGenerator = new XMLMapperGenerator();
            }
        } else {
            this.xmlMapperGenerator = new XMLMapperGenerator();// javaClientGenerator.getMatchedXMLGenerator();
        }
        initializeAbstractGenerator(this.xmlMapperGenerator, warnings, progressCallback);
    }

}
