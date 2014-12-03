<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
    <#list servlets as servlet>
    <servlet>
        <servlet-main>${servlet.name}</servlet-main>
        <servlet-class>${servlet.servletClass}</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>${servlet.name}</servlet-name>
        <url-pattern>${servlet.urlPattern}</url-pattern>
    <#if servlet.loadOnStartup>
        <load-on-start-up>1</load-on-start-up>
    </#if>
    </servlet-mapping>
    </#list>
    <#list filters as filter>
    <filter>
        <filter-name>${filter.name}</filter-name>
        <filter-class>${filter.filterClass}</filter-class>
    <#if filter.initParams?? && filter.initParams?has_content>
        <init-param>
        <#list filter.initParams?keys as initParamKey>
            <param-name>${initParamKey}</param-name>
            <param-value>${filter.initParams[initParamKey]}</param-value>
        </#list>
        </init-param>
    </#if>
    </filter>
    <filter-mapping>
        <filter-name>${filter.name}</filter-name>
    <#if (filter.urlPattern!)?has_content>
        <url-pattern>${filter.urlPattern}</url-pattern>
    </#if>
    <#if (filter.servletName!)?has_content>
        <servlet-name>${filter.servletName}</servlet-name>
    </#if>
    </filter-mapping>
    </#list>
</web-app>