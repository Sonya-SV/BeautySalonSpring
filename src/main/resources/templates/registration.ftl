<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="login-page">
        <div class="col-md-offset-4 col-md-3" style="padding-top: 100px">
            <div class="login-block">
                <img src="https://www.domzamkad.ru/images/no-avatar.png" width="200" alt="Scanfcode">
                <h1><@spring.message "put.your.data"/></h1>

                <form method="post" action="/registration">
                    <#if errorMessage??>
                        <div class="alert alert-danger">
                            ${errorMessage!}
                        </div>
                    </#if>
                    <#if emailError??>
                        <div class="alert alert-danger">
                            ${emailError!}
                        </div>
                    </#if>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                            <input required type="email" class="form-control" name="email"
                                   placeholder="<@spring.message "email"/>">
                        </div>
                    </div>
                    <hr class="hr-xs">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-check"></i></span>
                            <input type="text" class="form-control" name="firstName"
                                   placeholder="<@spring.message "first.name"/>">
                        </div>
                    </div>
                    <hr class="hr-xs">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-check"></i></span>
                            <input type="text" class="form-control" name="lastName"
                                   placeholder="<@spring.message "last.name"/>">
                        </div>
                    </div>
                    <hr class="hr-xs">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                            <input required type="password" class="form-control" name="password"
                                   placeholder="<@spring.message "password"/>">
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary btn-block" type="submit"><@spring.message "registration"/></button>
                </form>
            </div>
            <div class="login-links">
                <p class="text-center">
                    <@spring.message "already.have.account"/>
                    <a class="txt-brand" href="/login">
                        <font color=#29aafe><@spring.message "login"/></font>
                    </a>
                </p>
            </div>
        </div>
    </div>
</@c.page>