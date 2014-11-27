buildscript {
}

repositories {
    <#list repositories as repository>
    ${repository.string}
    </#list>
}

<#list plugins as plugin>
apply plugin: 'java'
</#list>

dependencies {
    <#list dependencies as dependency>
        compile("${dependency.group}:${dependency.name}:${dependency.version}")
    </#list>
}

<#list extensions![] as extension>
${extension.name} {
    <#list extension.options?keys as optionKey>
    ${optionKey} = ${extension.options[optionKey]}
    </#list>
}
</#list>

task wrapper(type:Wrapper) {
    gradleVersion = ${gradleVersion}
}