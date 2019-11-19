<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="col-md-6 col-md-offset-3" style="padding-top: 10%">
        <div class="panel panel-default">
            <div class=" panel-heading">
                <h3 class="panel-title" style="display: inline-block"> <@spring.message "edit.profile"/></h3>
            </div>
            <div class="panel-body">
                <form action="/admin/save" method="post" style="margin: auto">
                    <div class="form-group">
                        <label ><@spring.message "email"/>:   </label>
                        <p class="form-control-static"> ${user.email}</p>
                    </div>
                    <#if roles ??>
                    <#list roles as role>
                        <div>
                            <label><input type="radio"
                                          name="${role}" >${role}
<#--                                ${user.roles?seq_contains(role)?string("checked", "")}-->
                            </label>
                        </div>
                    </#list>
                    </#if>
                    <input type="hidden" value="${user.id}" name="userId">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit"><@spring.message "save"/></button>
                </form>
            </div>
        </div>
    </div>
</@c.page>