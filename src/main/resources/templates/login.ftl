<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="panel panel-default">
        <div class="login-page">
            <div class="col-md-offset-4 col-md-3" style="padding-top: 100px">
                <div class="login-block">
                    <img src="https://www.domzamkad.ru/images/no-avatar.png" width="200" alt="Scanfcode">
                    <h1><@spring.message "put.your.data"/></h1>

                    <form action="/login" method="post">
                        <#if userError ??>
                            <div class="alert alert-danger">
                                ${userError!}
                            </div>
                        </#if>
                        <#if error??>
                            <h3 style="color: red"> Invalid</h3>
                        </#if>
                        <#if logout??>
                            <h3 style="color: green"> User logout</h3>
                        </#if>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                                <input type="email" class="form-control" name="email" placeholder="<@spring.message "email"/>">
                            </div>
                        </div>
                        <hr class="hr-xs">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                                <input type="password" class="form-control" name="password" placeholder="<@spring.message "password"/>">
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-primary btn-block" type="submit"><@spring.message "login"/></button>
                    </form>
                </div>
                <div class="login-links">
                    <p class="text-center"><@spring.message "do.not.have.account"/>
                        <a class="txt-brand" href="/registration">
                            <font color=#29aafe><@spring.message "registration"/></font>
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</@c.page>