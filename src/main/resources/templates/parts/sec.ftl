<#assign known = Session.SPRING_SECURITY_CONTEXT?? >

<#if known>
    <#assign
    usr = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = usr.getUsername()
    isAdmin = usr.isAdmin()
    isMaster = usr.isMaster()>
<#else>
    <#assign
    name = "GUEST"
    isAdmin = false
    isMaster = false>
</#if>