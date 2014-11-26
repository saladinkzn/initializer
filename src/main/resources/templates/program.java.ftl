<#if (packageName!)?has_content>
package ${packageName};
</#if>

public class ${className} {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}