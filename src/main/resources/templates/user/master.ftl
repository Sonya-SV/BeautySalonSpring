<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <#if master??>
        <div class="container" style="margin-top: 100px">
            <div class="row">
                <div class="col-md-5">
                    <img class="card-img" src="data:image/png;base64,${master.base64!}" alt="..." height="400" style=" display: block;
                                     margin: 0 auto; margin-top: 5px">
                </div>
                <div class="col-md-7">
                    <h1 align="center" class="card-title">${master.user.firstName!} ${master.user.lastName!}</h1>
                    <hr align="center" width="300"/>
                    <p class="card-text">
                    <h2><@spring.message "work.schedule"/> ${master.timeStart!} - ${master.timeEnd!}</h2> </p>
                    <form action="/user/booking">
                        <#if procedures ??>
                            <h2>${procedures[0].category.name!}</h2>
                            <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                                <tr>
                                    <th><@spring.message "name"/></th>
                                    <th><@spring.message "price"/></th>
                                    <th><@spring.message "choose"/></th>
                                </tr>
                                <#list procedures as i>
                                    <tr>
                                        <td>${i.name!}</td>
                                        <td>${i.price!} <@spring.message "uah"/></td>
                                        <td>
                                            <button type="submit" class="btn btn-primary" style="margin-top:0px"
                                                    value="${i.id!}"
                                                    name="procedureId"> <@spring.message "choose"/></button>
                                        </td>
                                    </tr>
                                </#list>
                            </table>
                        </#if>
                        <input type="hidden" value="${master.id!}" name="masterId"/>
                    </form>
                    <#if isAdmin>
                        <form action="/master/schedule">
                            <button type="submit" value="${master.id!}" name="masterId" class="btn btn-primary"
                                    style="margin-top:0px">
                                <@spring.message "show.schedule"/></button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>

        <div class="container" style="margin-top: 60px">
            <div class="col-md-10 col-md-offset-1" style="padding-top: 50px">
                <h2 align="center"> <@spring.message "put.your.comment"/></h2>
                <div class="comments">
                    <form action="/user/comment">
                        <#if successSend ??>
                            <div class="alert alert-success"> <@spring.message "save.comment"/> </div>
                        </#if>
                        <#if error ??>
                            <div class="alert alert-danger"> <@spring.message "unsaved.comment"/> </div>
                        </#if>
                        <textarea required maxlength="280" cols="100%" name="comment"></textarea>
                        <input type="hidden" name="masterId" value="${master.id!}">
                        <button type="submit" class="btn btn-primary" style="margin-top:30px">
                            <@spring.message "send"/>
                        </button>
                    </form>
                    <#if isAdmin>
                        <#include "comments.ftl"/>
                    </#if>
                </div>
            </div>
        </div>
    </#if>
</@c.page>