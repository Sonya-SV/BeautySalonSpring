<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" style="padding-top: 50px">
            <h2><@spring.message "schedule"/><br/></h2>
            <form action="/master/sendemail">
                <div class="form-group">
                    <table class="calendar table table-bordered" style=" margin: auto; empty-cells: show">
                        <tr>
                            <th><@spring.message "time"/>/<@spring.message "date"/></th>
                            <#list dates as date>
                                <td width="13%">${date!}</td>
                            </#list>
                        </tr>
                        <#list workTime as time>
                            <tr>
                                <td>${time!}</td>
                                 <#list dates as date>
                                    <#assign count=0/>
                                        <#list schedule as i>
                                        <#if time == i.time && date == i.date>
                                            <#if i.done == true>
                                                <td class="has-events" rowspan="1">
                                                    <div class="row-fluid practice" style="width: 99%; height: 99%;
                                                    text-decoration: line-through">
                                                        <span class="title"> ${i.procedure.name!} </span>
                                                            ${i.user.firstName!} ${i.user.lastName!}<br>
                                                    </div>
                                                </td>
                                                <#assign count=1/>
                                            </#if>
                                            <#if i.done == false>
                                                <td class="has-events" rowspan="1"
                                                    style="text-decoration: line-through">
                                                    <div class="row-fluid practice" style="width: 99%; height: 100%;">
                                                        <span class="title"> ${i.procedure.name!} </span>
                                                            ${i.user.firstName!} ${i.user.lastName!}<br>
                                                        <button type="submit" name="scheduleId" value="${i.id!}">
                                                            <@spring.message "done"/></button>
                                                    </div>
                                                </td>
                                                <#assign count=1/>
                                            </#if>
                                        </#if>
                                    </#list>
                                    <#if count<1>
                                        <td class="no-events" rowspan="1"></td>
                                    </#if>
                                </#list>
                            </tr>
                        </#list>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
</@c.page>
