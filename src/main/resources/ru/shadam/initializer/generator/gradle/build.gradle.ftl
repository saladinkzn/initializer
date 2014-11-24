<#-- @ftlvariable name="dependencies" type="java.util.List<ru.shadam.initializer.dto.Dependency>" -->
<#-- @ftlvariable name="javaVersion" type="java.lang.String" -->
<#-- @ftlvariable name="gradleVersion" type="java.lang.String" -->

repositories {
    jcenter()
}

group = 'ru.shadam.example'
version = '1.0-SNAPSHOT'

apply plugin: 'java'

compileJava {
    sourceCompatibility = '${javaVersion!'1.7'}'
    targetCompatibility = '${javaVersion!'1.7'}'
}

dependencies {
    <#list (dependencies![]) as dependency>
        compile '${dependency.group}:${dependency.name}:${dependency.version}'
    </#list>
}

task wrapper(type: Wrapper) {
    gradleVersion = ${gradleVersion!'2.2'}
}