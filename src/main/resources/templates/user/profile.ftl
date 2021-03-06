<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="container" style="margin-top: 60px">
        <div class="row">
            <div class="col-md-8 col-md-offset-2" style="padding-top: 50px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="display: inline-block"><@spring.message "edit.profile"/></h3>
                    </div>
                    <div class="panel-body">
                        <form action="/user/profile" method="post">
                            <#if successSave??>
                                <div class="alert alert-success"> ${successSave!} </div>
                            </#if>
                            <div class="form-group">
                                <label><@spring.message "email"/>: </label>
                                <p class="form-control-static"> <#if user??>${user.email!}</#if></p>
                            </div>
                            <div class="form-group">
                                <label><@spring.message "first.name"/> </label>
                                <input type="text" class="form-control" name="firstName"
                                       value="<#if user??>${user.firstName!}</#if>" required>
                            </div>
                            <div class="form-group">
                                <label><@spring.message "last.name"/></label>
                                <input type="text" class="form-control" name="lastName"
                                       value="<#if user??>${user.lastName!}</#if>" required>
                            </div>
                            <div class="form-group">
                                <label><@spring.message "password"/></label>
                                <input required type="password" class="form-control" name="password">
                                <#if passwordErrorDiffer??>
                                    <div class="text-danger"> ${passwordErrorDiffer!} </div>
                                </#if>
                            </div>
                            <div class="form-group">
                                <label><@spring.message "password"/></label>
                                <input required type="password" class="form-control" name="password2">
                                <#if passwordErrorDiffer??>
                                    <div class="text-danger"> ${passwordErrorDiffer!} </div>
                                </#if>
                            </div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-success" style="margin-top:30px">
                                <@spring.message "save"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>