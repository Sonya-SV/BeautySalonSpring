<#include "sec.ftl">
<div class="header">
    <div class="navbar-fixed-top">
        <div class="navbar navbar-default">
            <a class="navbar-brand" href="/">Beauty</a>
            <ul class="nav navbar-nav">
                <#if isAdmin>
                    <li><a href="/admin/userlist"><@spring.message "user.list"/></a></li>
                </#if>
                <#if usr??>
                <li><a href="/user/masterlist"><@spring.message "masters"/></a></li>
                <li><a href="/user/categorylist"><@spring.message "categories"/></a></li>
                    <li><a href="/user/profile"><@spring.message "profile"/></a></li>
                </#if>
                <#if isMaster>
                    <li><a href="/master/schedule"><@spring.message "schedule"/></a></li>
                </#if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <#if name=="GUEST">
                <li><a href="/login"><span
                                class="glyphicon glyphicon-log-in"></span><@spring.message "login"/></a></li>
                <li><a href="/registration"><span
                                class="glyphicon glyphicon-user"></span><@spring.message "registration"/></a></li>
                <#else >
                    <li><a><span class="glyphicon glyphicon-user"></span> ${usr.email!}</a></li>
                <li><a href="/logout"><span
                                class="glyphicon glyphicon-log-out"></span> <@spring.message "logout"/> </a>
                </li>
                </#if>
                <li><a href="?lang=ua">
                        <img alt="Українська" height="42"
                             src="https://cdn3.iconfinder.com/data/icons/finalflags/256/Ukraine-Flag.png" width="42">
                    </a></li>
                <li><a href="?lang=en">
                        <img alt="Англійська" height="42"
                             src="http://abali.ru/wp-content/uploads/2010/12/united-kingdom-flag.png" width="42">
                    </a></li>
            </ul>
        </div>
    </div>
</div>
