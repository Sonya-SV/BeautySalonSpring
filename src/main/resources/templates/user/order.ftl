<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <#if schedule ??>
        <div class="container" style="margin-top: 100px">
            <div class="row">
                <div class="col-md-5">
                    <img class="card-img" src="data:image/png;base64,${schedule.master.base64}" alt="..." height="400"
                         style=" display: block;
                                     margin: 0 auto; margin-top: 5px">
                </div>
                <div class="col-md-7" style="margin-top: 20px">
                    <form action="/user/save">
                        <#if alreadyBooked ??>
                            <div class="alert alert-danger"> ${alreadyBooked!} </div>
                            <a href="/user/booking?masterId=${schedule.master.id!}&procedureId=${schedule.procedure.id!}">
                                Choose another date
                            </a>
                        </#if>
                        <#if errorOrder ??>
                            <div class="alert alert-danger"> ${errorOrder!} </div>
                        </#if>
                        <#if timeError ??>
                            <div class="alert alert-danger"> ${timeError!} </div>
                        </#if>
                        <div class="form-row">
                            <div class="col-6 col-sm-4 form-group">
                                <label><@spring.message "first.name"/></label>
                                <input required type="text" value="<#if user??>${user.firstName!}</#if>"
                                       class="form-control" name="firstName">
                            </div>
                            <div class="col-6 col-sm-8 form-group">
                                <label><@spring.message "last.name"/></label>
                                <input required type="text" value="<#if user??>${user.lastName!}</#if>"
                                       class="form-control" name="lastName">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-sm-4 form-group">
                                <label><@spring.message "time"/></label>
                                <p class="form-control-static">${schedule.time!}</p>
                            </div>
                            <div class="col-4 col-sm-4 form-group">
                                <label><@spring.message "date"/></label>
                                <p class="form-control-static">${schedule.date!}</p>
                            </div>
                            <div class="col-4 col-sm-4 form-group">
                                <label><@spring.message "master"/></label>
                                <p class="form-control-static">${schedule.master.user.firstName!}
                                    ${schedule.master.user.lastName!}
                                </p>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-sm-4 form-group">
                                <label><@spring.message "procedure"/></label>
                                <p class="form-control-static"><#if schedule.procedure??>
                                        ${schedule.procedure.name!}</#if></p>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-sm-4 form-group">
                                <label><@spring.message "price"/></label>
                                <p class="form-control-static"><#if schedule.procedure??>
                                        ${schedule.procedure.price!}</#if>
                                    <@spring.message "uah"/></p>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-4 col-sm-4 form-group">
                                <button type="submit" class="btn btn-primary" style="margin-top:30px">
                                    <@spring.message "book"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
</@c.page>