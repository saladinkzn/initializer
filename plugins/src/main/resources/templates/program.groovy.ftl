<#if (packageName!)?has_content>
package ${packageName};
</#if>

class ${className} {
    static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}